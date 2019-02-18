   
# 使用thymeleaf

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
 
## 设置属性 

### th:attr
通过`th:attr`可以给标签设置属性例如

    <p th:attr="style='color: #FF8877'">这里使用了th:attr修改字颜色</p>
    等同于 <p th:style="'color: #FF8877'">这里使用了th:attr修改字颜色</p>

修改多个属性则使用`,`分隔

 如果要给特定属性设置值，thymeleaf还有很多特定属性的标记，例如th:background,th:action等
 [更多属性](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-value-to-specific-attributes)
 
 _ps:_
 
 这里有个小知识点，自己以前也没有注意.对于background属性,只有body标签可以使用其他标签无效果,而且background 属性规定规定文档的背景图像。
 
 ```
 <body background="#FF0000"> 无效果
 <body background="https://www.badu.com/sss/ss.jpg"> 显示图片北京
 <p background="https://www.badu.com/sss/ss.jpg"> 无效果
 ```
另外HTML5 已不再支持 <body> background 属性,建议使用 CSS 替代。
因此th:background不可率使用

 ### 追加属性值
 
 如果你想保留原标签的Css属性，同时增加新的样式可以使用如下标签
 
 - th:attrappend和th:attrprepend附加（后缀）或前置（前缀）到现有属性值
 - th:classappend和th:classprepend
 - th:styleappend和th:styleprepend

示例

    <a href="#" class="btn" th:classappend="'btn-primary'">按钮，使用了th:classappend</a>

## 迭代 th:each

遍历集合使用th:each标签,对于迭代需要说明的是迭代状态(th:each="item, itemStat : ${list}")
这里itemStat包含一下数据

* 当前迭代索引，从0开始。这是index属性。
* 当前迭代索引，从1开始。这是count属性。
* 迭代变量中元素的总量。这是size属性。
* 每次迭代的变量。这是current元素。
* 当前迭代是偶数还是奇数。这些是even/odd布尔属性。
* 当前迭代是否是第一个。这是first布尔属性。
* 当前迭代是否是最后一次。这是last布尔属性。

**示例**

```
<table class="table table-bordered">
    <thead>
        <tr>
            <th>姓名</th>
            <th>年龄</th>
        </tr>
    </thead>
    <tbody>
        <tr th:each="user, userStat : ${users}" th:class="${userStat.odd}? 'odd'">
            <td th:text="${user.name}"></td>
            <td th:text="${user.age}"></td>
        </tr>
    </tbody>
</table>

<div th:each="user, userStat : ${users}">
    <span th:text="|Index: ${userStat.index}, Size: ${userStat.size}, Current: ${userStat.current}|"></span>
</div>
```

## 条件判断

### th:if

条件判断该th:if属性不仅会评估布尔条件。它的功能稍微超出了它，它将按照true以下规则评估指定的表达式：

- 如果value不为null：
- 如果value是布尔值，则为true。
- 如果value是数字且不为零
- 如果value是一个字符且不为零
- 如果value是String并且不是“false”，“off”或“no”
- 如果value不是布尔值，数字，字符或字符串。
- （如果value为null，则th：if将计算为false

**示例**

```
<div th:if="${users.size() &gt; 3}">
    <p>用户数目大于3</p>
    <p th:text="${user.bankCardList}?''">用户null</p>
</div>
```

### th:switch / th:case

分支判断

**示例**

```
<div th:switch="${user.role}">
  <p th:case="'admin'">User is an administrator</p>
  <p th:case="#{roles.manager}">User is a manager</p>
</div>
```


## 模板布局

对于页面中的通用布局可以整理为模板布局，在需要的地方引入

### 模板片段

定义一个片段使用th:fragment,例如：

```
新建一个模板module.html
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<body>
<div th:fragment="header>
    <h1>Hello</h1>
</div>
</body>
</html>

```

使用片段也比较简单通过使用th:insert,th:replace来放在想要插入或者替换的位置，例如：

```
<div th:replace="~{module :: header}">
</div>

渲染结果：
<div>
    <h1>Hello</h1>
</div>

```

**引入片段的语法说明**

引入模板片段可以使用一下方式

*  th:insert="~{模板 :: 片段名称}"
*  th:insert="~{:: 片段名称}"
*  th:insert="~{模板}"

当然这里的~{}也可以省略

_ps:_

如果目标模板和模板在同级目录下可以直接引用模板~{模板 :: 片段},如果不在统一目录下，例如如下目录：

```
--template
    --common
        --module.html
    home.html
```
当home.html引用module.html模板中的片段时候就需要指定目录，默认模板路径在根目录下,需要如下的引用方式

    <div th:replace="~{../templates/common/header}"></div>
    
**th:insert 与 th:replace**

从字面可以理解一个是插入, 一个是替换.效果如下：

```
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<body>

<div th:fragment="header">
    <img th:src="@{~/assets/app/image/logo.png}" th:alt-title="Logo">
</div>

<div th:insert=":: header"></div>
<div th:replace=":: header"></div>
</body>
</html>

渲染后效果
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<body>

<div>
    <img th:src="@{~/assets/app/image/logo.png}" th:alt-title="Logo">
</div>

//insert效果
<div>
    <div>
        <img th:src="@{~/assets/app/image/logo.png}" th:alt-title="Logo">
    </div>
</div>
// replace效果
<div>
    <img th:src="@{~/assets/app/image/logo.png}" th:alt-title="Logo">
</div>
</body>
</html>

```
   
**th:block的使用**

有时候我们引入的片段不向包含片段最外层的标签，或者我们吸纳该将`<head>`中的一些`<link>`标签
放在模板中，这时候就需要`th:block`来帮助我们完成.配合th:insert或者th:replace就可以完成.

**示例**

模板片段
```
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<head>
    <th:block th:fragment="common-link">
        <link href="https://cdn.bootcss.com/twitter-bootstrap/4.2.1/css/bootstrap.css" rel="stylesheet">
    </th:block>
</head>
...
</html>
```

引入片段

```
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<head>
    <link th:replace="../templates/common/module :: common-link">
</head>
...

```
     
### 模板片段参数

片段可以像函数一样指定参数，通过th:insert或者th:replace 来传入参数

**示例：**

模板片段
```
<div th:fragment="list(datas)">
    <div th:each="item : ${data}">
        <p th:text="${item.name}"></p>
    </div>
</div>

```

引用模板片段
```
<div th:class="title-3">
    <h3>带参数的模板</h3>
    <div th:replace="../templates/common/module :: traversalList(${users})"></div>
</div>
```

### 模板片段高级使用

对于这个模板片段的高级使用，我这样理解的，就是将目标页面中的标签作为模板替换模板中指定的块区域.

_模板片段需要这样定义_：
```
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<head th:fragment="header(title, links, headJs, headStyle)">
    <meta charset="UTF-8">
    <title th:replace="${title}">Spring示例程序</title>
    <link type="text/css" href="https://cdn.bootcss.com/twitter-bootstrap/4.2.1/css/bootstrap.css" rel="stylesheet">

    <th:block th:replace="${links}" />


    <th:block th:replace="${headJs}"/>

    <th:block th:replace="${headStyle}"/>
</head>


</html>
```
这个模板片段类似上面的参数模板片段，而这些参数也可以理解果模板片段，我们通过th:replace来应用模板

_使用模板片段_：
```
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<head th:replace="../templates/common/common-head :: header(~{::title}, ~{::link}, _, ~{::style})">
    <title>Thymeleaf示例</title>
    <!--<link th:replace="../templates/common/module :: common-link">-->
    <!-- ~是域名下根路径，都则回事访问地址下的路径 -->
    <link rel="stylesheet" th:href="@{~/assets/app/css/common.css}">

    <style>
        .middle-font {
            font-size: 1.2rem;
            color: darkgreen;
            font-family: "WenQuanYi Micro Hei", monospace;
        }
    </style>
</head>
...
```
应用该模板时候参数语法就类似应用模板片段的方式~{::替换的参数}，这里我们需要替换title,link和style.
这样渲染以后的效果如下：
```
<head>
    <meta charset="UTF-8">
    <title>Thymeleaf示例</title>
    <link type="text/css" href="https://cdn.bootcss.com/twitter-bootstrap/4.2.1/css/bootstrap.css" rel="stylesheet">

    <link rel="stylesheet" href="/assets/app/css/common.css">

    <style>
        .middle-font {
            font-size: 1.2rem;
            color: darkgreen;
            font-family: "WenQuanYi Micro Hei", monospace;
        }
    </style>
</head>
```
这里可以看到目标页面中的`title,link,style`替换了模板片段中的`title,link,style`,假使模板中没有定义
`style`参数，那么渲染以后的页面也不会有style,即时目标页面含有`style`

如果在目标页面没有需要传入的参数，如上示例中`headJs`参数，我们可以传入`空片段(~{})`或者使用`无操作标识(_)`

_ps:
1.模板片段的插入或替换还可以根据条件来决定是否应用模板片段
2.我们还以应用整个html作为通用模板(包含了页眉页脚)，应用到目标页面_



### 模板删除th:remove

`th:remove`标签主要是用在模拟页面展示的时候，在正式渲染的时候不展示模拟数据，

**示例**

```
<table>
    <tr>
        <th>Title1</th>
        <th>Title2</th>
    </tr>
    <tr th:each="item : ${list}">
        <td th:text="${item.name}"></td>
        <td th:text="${item.age}"></td>
    </tr>
    <tr th:remove="all">
        <td>站三</td>
        <td>19</td>
    </tr>
    <tr th:remove="all">
            <td>站三</td>
            <td>19</td>
     </tr>
</table>

```

这样如果你直接打开该html会显示(源代码)

```
<table>
    <tr>
        <th>Title1</th>
        <th>Title2</th>
    </tr>
    <tr>
        <td>站三</td>
        <td>19</td>
    </tr>
    <tr>
        <td>站三</td>
        <td>19</td>
     </tr>
</table>
```

而渲染后

```
<table>
    <tr>
        <th>Title1</th>
        <th>Title2</th>
    </tr>
    <tr>
        <td>User`</td>
        <td>10</td>
    </tr>
    <tr>
        <td>User2</td>
        <td>9</td>
     </tr>
</table>
```

**th:remove使用的值**

- all包含该标签以及所有子元素
- body：不要删除包含标记，但删除其所有子标记。
- tag：删除包含标记，但不删除其子项。
- all-but-first：删除除第一个之外的所有包含标记的子项。
- none： 没做什么。此值对于动态评估很有用。

## 局部变量

我们可以使用`th:with`临时创建局部变量，并在子元素中使用该变量

**示例**

```
<div th:class="title-3" th:with="twoUser=${users[1]}" >
    <h3>th:with创建局部变量</h3>
    <p th:text="${twoUser.name}"></p>
</div>
```
[更多语法](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#local-variables)

当一个标签使用了多个th:*,这就涉及到执行的优先级,thymeleaf有自己的优先级顺序
参考[Thymeleaf属性优先级](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#attribute-precedence)

## 代码注释

* 原型不显示，但是会被thymeleaf解析的注释语法

```
<!--/*/
<div th:class="title-3" th:with="twoUser=${users[1]}">
    <h3>th:with创建局部变量</h3>
    <p th:text="${twoUser.name}"></p>
</div>
/*/-->
```

* 不会被thymeleaf解析但是会在原型上显示的语法

```
<!--/*-->
<div th:class="title-3" th:with="twoUser=${users[1]}">
    <h3>th:with创建局部变量</h3>
    <p th:text="${twoUser.name}"></p>
</div>
<!--*/-->
```

## 内联

内联的语法是使用`[[...]]`来处理，在上面我们看到的Thymeleaf使用都是应用在标签的属性上.
而内联可以让我们在很多地方都可以使用Thymeleaf

### 表达式内联

看下示例：

```
//user.welcome=欢迎来到<b>北京</b>
<div th:class="title-3">
    <h3>表达式内联</h3>
    <p>时间：[[${#dates.format(nowTime, 'yyyy-MM-dd')}]]</p>
    <p>转义：[(#{user.welcome})]</p>
    <p>非转义：[[#{user.welcome}]]</p>
</div>
```
我们可以将上下文中的内容应用与标签的`text`中,注意到还有`[(...)]`的使用，这个与`[[...]]`
差异在是否进行转义.转义以后北京显示效果就是加粗的，非转义的则显示`<b>北京</b>`

### Js中使用内联

使用示例

```
<div th:class="title-3" id="js-inline">
    <h3>javascript内联</h3>
</div>

<script th:inline="javascript">

    window.onload = function () {
        let userJson = [[${user}]] //1
        let userJsonArray = [[${users}]] //2
    
        let jsInlineDiv = document.getElementById("js-inline");
        let jsInlineP = document.createElement("p");
        jsInlineP.innerHTML = "[(#{user.welcome})]";
        jsInlineDiv.appendChild(jsInlineP);
    }

</script>
```
在实践中发现`[()]`在js中没有起到转义效果,所以我这里就将内容作为innerHTML

**序列化**

在js中使用Thymeleaf内联会自动将对象集合转换为json对象或者数组如上示例的`userJson`和`userJsonArray`
这样就更方便我们处理数据。

### Css中使用内联

目前感觉用处不大，使用方法类似js的内联

示例

```
pageModel.addAttribute("inlineCss", "color-font");
pageModel.addAttribute("inlineCssColor", "rgb(200,89,22)");

<style th:inline="css">
    .[[${inlineCss}]] {
        color: [(${inlineCssColor})];
    }
</style>
```
这里有个问题，就是配置的`inlineCssColor,rgb(200,89,22)`在页面渲染的时候`(),`
都会增加一个转义字符，类似这样:`rgb\(200\,89\,22\)`。这样就无法正常显示样式。
所以目前看没有什么更多使用的方式

## 

























