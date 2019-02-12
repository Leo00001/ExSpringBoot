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

[Thymeleaf 使用](../note/ThymeleafNote.md)