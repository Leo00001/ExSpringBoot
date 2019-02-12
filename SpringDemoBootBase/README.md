# Spring Boot

## 快速搭建



## 外部配置

```
@Component
@PropertySource(value = "classpath:config/book.yaml", encoding = "UTF-8")
@ConfigurationProperties(prefix = "book2")
public class BookSetting {

    private String author;

    private String name;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

```

这里需要添加依赖否则编译器会显示错误`SpringBoot  Configuration Annotation Processor not found in classpath`

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```
## 日志配置

SpringBoot默认使用logback日志需要在application.properties中配置

```
logging.level.root=WARN
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate=ERROR
```
更多参考[日志配置](https://docs.spring.io/spring-boot/docs/2.1.1.RELEASE/reference/htmlsingle/#boot-features-custom-log-levels)

**坑1**
: Failed to bind properties under 'logging.level' to java.util.Map<java.lang.String, java.lang.String>

需要使用logging.level.root配置

**坑2**
java.lang.NoSuchMethodError: ch.qos.logback.classic.LoggerContext.removeObje

logback-classic版本低，推荐使用
```
<logback.version>1.2.3</logback.version>
<slf4j.version>1.7.25</slf4j.version>
```

## 配置Profile

如果针对不同环境使用不同配置可以通过Spring配置文件中`spring-profile.active`来设置

例如,我们可以在`resource`目录下创建生产环境和开发环境的配置文件

    application-prod.properties
    application-dev.properties

之后在application.properties中配置需要加载那个环境

    spring-profile.active=prod
    
## 使用thymeleaf

需要在Spring中研究Thymeleaf

1. 如何自定义标签 [参考链接](https://blog.csdn.net/yilei_forwork/article/details/80833150)
2. 如何实现自定义转换服务,SpringBoot默认支持,通过`${{}}`可以将日期显示字符串调用了toString()方法



### SpringBoot集成
使用thymeleaf需要引入文件
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```
对thymeleaf配置

```
# thymeleaf 配置
spring:
  thymeleaf:
    # 开发模式配置false,避免了修改了模板哈要重新启动服务器
    cache: false
    check-template: true
    check-template-location: true
    enabled: true
    encoding: UTF-8
    mode: HTML5
    # 定制模板路径，注意最后免有个/不可以缺少，但是这样配置后直接访问模板文件可以访问
    # 所以还是使用默认路径classpath:/templates/这样浏览器无法直接访问，必须通过Controller
    # prefix: classpath:/static/templates/
    suffix: .html
```
在resource目录下新建templates文件夹，创建模板文件user.html

```
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Thymeleaf示例</title>
    <link rel="stylesheet" th:href="@{~/assets/app/css/common.css}"> <!-- 1 -->
</head>
<body>

<div>
    <h3>用户名称</h3>
    <span th:text="${user.name}"></span>
</div>

</body>
</html>

```
1 位置这里说明下`~`符号将资源文件在根路径下即 `https://www.ss.com/assets/...`

### 语法

* 简单表达：

   * 变量表达式： ${...}
   * 选择变量表达式： *{...}
   * 消息表达式： #{...}   
   * 链接网址表达式： @{...}
   * 片段表达式： ~{...}

* 文本操作

   * 字符串拼接 + 例如：th:text="'姓名'+${user.name}"
   * 字符串替换 例如|The name is ${name}|

* 运算

```
+ -  * / %
```
* 布尔运算：

   * 二元运算符：and，or
   * 布尔否定（一元运算符）： !，not
   
* 比较和平等：
   
   * 比较：>，<，>=，<=（gt，lt，ge，le）
   * 平等运营商：==，!=（eq，ne）
   
* 有条件的运营商：
   
   * IF-THEN： (if) ? (then)
   * IF-THEN-ELSE： (if) ? (then) : (else)
   * 默认： (value) ?: (defaultvalue)

### 国际化

配置国际化首先创建国际化配置文件
在resource文件夹下创建
- messages.properties：代表默认显示的语种
- messages_zh_CN.properties：切换为页面显示中文
- messages_en_US.properties:切换为页面显示英文。

然后在SpringBoot配置国际化需要在application.yaml中配置
```
spring:
  # 配置国际化 可以包括多个，以逗号分隔。每一个ResourceBundle
  messages:
    basename: i18n/messages
```

这样在页面模板中就可以直接使用配置文件中的文字，使用方法如下
在thymeleaf中使用#{}指定国际化消息

```
<div>
    <h3>国际化配置</h3>
    <label> 全局配置</label>
    <p th:text="#{page.content}">统一配置</p>
</div>
```

### **th:text** PK **th:utext**

刚看到这两个时候以为都是输出文本，学习后才知道不同

`th:text`原样输出内容例如 `th:text="#{welcome}"` 输出：欢迎来到<b>北京</b>
`th:utext`输出非转义的内容 `th:text="#{welcome}"`输出：欢迎来到**北京**


_ps: welcome=user.welcome=欢迎来到<b>北京</b>_

在`th:text`中 

- 字符串使用单引号
- 运算符可以直接运算
- 在需要的地方使用()
- 如果想要使用标签默认的text使用_替代 例如<p th:text="${user.bankCardList}?'':_">用户不为null</p>
如果没有`:_`则不会显示任何内容

### 国际化配置中使用占位

例如我们可以在国际化消息配置文件中使用
`str.welcome=欢迎{0}莅临知道`

在模板中这样使用`<p th:utext="#{str.welcome(${user.nam e})}"></p>`

根据官方文档还可以使用变量[https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#messages](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#messages)
但是测试失败，不知道哪里配置有问题

### [表达式基本对象](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#appendix-a-expression-basic-objects)

我们可以在表达式中使用下列基本对象

* #ctx：上下文对象。
* #vars: 上下文变量。
* #locale：上下文区域设置。
* #request:(仅限Web Contexts）HttpServletRequest对象。
* #response:(仅限Web Contexts）HttpServletResponse对象。
* #session:(仅限Web Contexts）HttpSession对象。
* #servletContext:(仅限Web Contexts）ServletContext对象。

[表达式中的工具类使用](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#appendix-b-expression-utility-objects)

### 选择表达式

选择表达式对应*{}的使用方法，这里需要使用到th:object标签属性的语法
我通过th:object选定变量对象后，在子标签中可以通过*{}直接使用对象的属性值

示例：

```
<div>
    <h2>th:object与*{}联合使用</h2>
    <div th:object="${user}">
        <p th:text="|姓名: *{name}|"></p>
        <p th:text="|年龄: *{age}|"></p>
    </div>
</div>
```

### 链接 th:href使用

Thymeleaf 配置链接使用`th:href`,语法是使用`@{}`陪配置

- 方式1

`<a href="https://www.baidu.com" th:href="@{https://www.baiduc.com/search(name=${user.name})}">链接1</a>`

等同于`<a href="@{https://www.baiduc.com/search?name=Jack}">链接1</a>`

- 方式2

`<a href="https://www.baidu.com" th:href="@{/search/{name}/(name=${user.name})}">链接1</a>`

等同于`<a href="@{/search/Jack}">链接1</a>`
    
- 方式3

`<a href="https://www.baidu.com" th:href="@{search(name=${user.name})}">链接1</a>`

等同于`<a href="@{/search?name=Jack}">链接1</a>`

示例

```
    <h2>th:href</h2>
    <div>
        <a href="https://www.baidu.com" th:href="@{search/{user}(name=${user.name}, user='陌生人')}">用户信息</a>
    </div>
```

对于服务器根相对路径则使用`@{~/xxx}`

### 运算符别名

    运算符存在的文本别名：gt（>），lt（<），ge（>=），le（<=），not（!）。还eq（==），neq/ ne（!=

Thymeleaf 中可以使用运算符`+` `-` `*` `/` `%` `==` `!=`  `?:`
 
### 