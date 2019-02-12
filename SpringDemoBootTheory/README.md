# SpringBoot 自定义配置示例

本module展示了自定义配置的使用，在使用SpringBoot时候，很多配置都在`application.properties`中设置
例如配置日志

```
// yaml文件 application.yaml
logging:
  level:
    root: info
    
// application.properties
logging.level.root=info
```
如果我们向自己定义一些配置该如何座呢，这个示例展示了如果自定义配置。

添加依赖

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-autoconfigure</artifactId>
    <version>${spring-boot.version}</version>
</dependency>
```

自定义属性配置，下面的content可以通过`application.properties`中配置的`hello.content`来获取

```
@ConfigurationProperties(prefix = "hello")
public class HelloServiceProperties {


    private String content = "world";

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
```
新建一个判断依据类，上面的content的内容将会设置里面

```
public class HelloService {

    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String sayHello() {
        return "hello " + content;
    }
}
```
最后创建一个自动配置类

```
@Configuration
@EnableConfigurationProperties(HelloServiceProperties.class)
@ConditionalOnClass(HelloService.class)
@ConditionalOnProperty(prefix = "hello", value = "enabled", matchIfMissing = true)
public class HelloServiceAutoConfiguration {

    @Autowired
    private HelloServiceProperties properties;

    @Bean
    @ConditionalOnMissingBean(HelloService.class) //条件注解如果没有HelloService类则获取
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        helloService.setContent(properties.getContent());
        return helloService;
    }
}
```

至此示例完成，通过**mvn**可以将该工程打包安装到本地或者私服的**maven**仓库中，在其他项目中就可以直接使用`HelloService`,
`sayHello()`方法返回的就是通过`application.properties`中配置的hello.content。如果没有配置则返回默认的`hello world`