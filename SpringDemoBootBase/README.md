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

## [Thymeleaf 使用](../note/ThymeleafNote.md)

## Web相关配置

旧版本的SpringBoot都是通过继承WebMvcAutoConfigurationAdapter类来复写父类方法来做到修改一些配置信息。
但是在最新版本中方式被废弃了，如果我们想修改一些SpringBoot默认的配置重写一个Config就可以。

示例：

```
@Configuration
public class MvcConfig {

        @Bean
        public InternalResourceViewResolver defaultViewResolver() {
        // 配置一些自己的需求
            InternalResourceViewResolver resolver = new InternalResourceViewResolver();
            resolver.setPrefix(this.mvcProperties.getView().getPrefix());
            resolver.setSuffix(this.mvcProperties.getView().getSuffix());
            return resolver;
        }
}
```

什么可以这么配置呢，我们可以查看WebMvcAutoConfiguration.java的源码可以看到

```
@Bean
@ConditionalOnMissingBean // 注1
public InternalResourceViewResolver defaultViewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix(this.mvcProperties.getView().getPrefix());
    resolver.setSuffix(this.mvcProperties.getView().getSuffix());
    return resolver;
}
```
这注释1的地方配置有`@ConditionalOnMissingBean`注解，这个注解的意思就是
仅当 BeanFactory 中不包含指定的 bean class 和/或 name 时条件匹配。通俗讲就是
如果你没有提供一个自定义的Bean，系统就会使用这个默认的，所以我们只需要提供一个自定义的
在SpringBoot启动时候就会使用自定义的Bean注入替换默认的Bean.所以如果你想修改一些默认的配置
只需要你查看源码然后复制出来编写你的逻辑就可以了

**如果你想配合更加丰富的功能，那么你就需要实现WebMvcConfigurer接口，复写需要的方法**

我们可以`DelegatingWebMvcConfiguration`类会将实现了WebMvcConfig接口的类注入

```
@Configuration
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {

	private final WebMvcConfigurerComposite configurers = new WebMvcConfigurerComposite();


	@Autowired(required = false)
	public void setConfigurers(List<WebMvcConfigurer> configurers) {
		if (!CollectionUtils.isEmpty(configurers)) {
			this.configurers.addWebMvcConfigurers(configurers);
		}
	}
	
	....
```
关于更多Web配置参看[Spring MVC 基本使用示例](../SpringMVC/README.md)

## Servlet容器配置

Servlet的配置很多，比如端口，回话超时时间等，更多的配置参考*SpringBoot官方文档*[附录](https://docs.spring.io/spring-boot/docs/2.1.3.RELEASE/reference/htmlsingle/#appendix)

### fastjson 替换默认的jackson

添加fastjson引用到pom.xml中
```
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.56</version>
</dependency>
```

然后将fastjson消息解析添加到配置中


```
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //fastjson 替换jackson
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
          //2.定义一个配置，设置编码方式，和格式化的形式
       FastJsonConfig fastJsonConfig = new FastJsonConfig();
       //3.设置成了PrettyFormat格式
       fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
               SerializerFeature.WriteNullBooleanAsFalse,
               SerializerFeature.WriteNullListAsEmpty,
               SerializerFeature.WriteNullStringAsEmpty,
               SerializerFeature.WriteMapNullValue);
       //4.处理中文乱码问题
       List<MediaType> fastMediaTypes =  new ArrayList<>();
       fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
       fastConverter.setSupportedMediaTypes(fastMediaTypes);

       //5.将fastJsonConfig加到消息转换器中
       fastConverter.setFastJsonConfig(fastJsonConfig);
       // 将fastjson 优先级最高。或者移除jackson 的应用，这样消息转换列表中就只有fastjson
       converters.add(0, fastConverter);
    }
```

### 自定义错误页面

_方式一_

在定义错误页面需要实现EmbeddedServletContainerCustomizer(SpringBoot1.x)、WebServerFactoryCustomizer<WebServerFactoryCustomizer<ConfigurableWebServerFactory>>(SpringBoot2.x)

更多参考[官方文档](https://docs.spring.io/spring-boot/docs/2.1.3.RELEASE/reference/htmlsingle/#boot-features-programmatic-embedded-container-customization)

```
@Component
public class MeServletConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {


    @Override
    public void customize(ConfigurableWebServerFactory factory) {

        //配置错误页面
        Set<ErrorPage> errorPages = new HashSet<>();
        errorPages.add(new ErrorPage(HttpStatus.NOT_FOUND, "error/404.html"));

        factory.setErrorPages(errorPages);
    }
}
```

这样配置，404页面配置在`resource/static/error/404.html`

_方式二_

对于SpringMVC项目可以配置实现`ErrorViewResolver`接口处理异常

**示例**

```
@Bean
public ErrorViewResolver errorViewResolver() {

    return new CustomErrorPageViewResolver();
}

private static class CustomErrorPageViewResolver implements ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        return new ModelAndView("error/404r");
    }
}
```


_方式三_

SpringMVC对于默认的模板(thymeleaf, freeMarker)，会默认定向到templates/error/目录下，只要
在该目录下创建对应的错误状态页面就可以

**示例**
```
src/
 +- main/
     +- java/
     |   + <source code>
     +- resources/
         +- public/
             +- error/
             |   +- 404.html
             +- <other public assets>
```

### 替换Tomcat

默认SpringBoot使用Tomcat作为服务器容器，如果想要替换则修改pom.xml中spring-boot-starter-web引用

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<!-- 加入Jett容器 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jett</artifactId>
</dependency>
```

### https配置

配置ssl可以通过application.yaml配置容器的ssl。如下：

```
server.ssl.ciphers= # Supported SSL ciphers.
server.ssl.client-auth= # Client authentication mode.
server.ssl.enabled=true # Whether to enable SSL support.
server.ssl.enabled-protocols= # Enabled SSL protocols.
server.ssl.key-alias= # Alias that identifies the key in the key store.
server.ssl.key-password= # Password used to access the key in the key store.
server.ssl.key-store= # Path to the key store that holds the SSL certificate (typically a jks file).
server.ssl.key-store-password= # Password used to access the key store.
server.ssl.key-store-provider= # Provider for the key store.
server.ssl.key-store-type= # Type of the key store.
server.ssl.protocol=TLS # SSL protocol to use.
server.ssl.trust-store= # Trust store that holds SSL certificates.
server.ssl.trust-store-password= # Password used to access the trust store.
server.ssl.trust-store-provider= # Provider for the trust store.
server.ssl.trust-store-type= # Type of the trust store.
```

我们可以看到配置中含有key-store和trust-store，这两个
一个是进行ssl单向认证，另外一个是双向认证的配置。[更多配置](https://tonysun3544.iteye.com/blog/2265448)

生成证书
```
keytool -genkey -alias server -keyalg RSA -keystore server.jks -validity 3650
输入密钥库口令:  
再次输入新口令: 
您的名字与姓氏是什么?
  [Unknown]:  Leo Liu
您的组织单位名称是什么?
  [Unknown]:  Baiyu
您的组织名称是什么?
  [Unknown]:  Baiyu
您所在的城市或区域名称是什么?
  [Unknown]:  ShiJiazhuang            
您所在的省/市/自治区名称是什么?
  [Unknown]:  Hebei
该单位的双字母国家/地区代码是什么?
  [Unknown]:  86
CN=Leo Liu, OU=Baiyu, O=Baiyu, L=ShiJiazhuang, ST=Hebei, C=86是否正确?
  [否]:  是 

输入 <server> 的密钥口令
        (如果和密钥库口令相同, 按回车):  

Warning:
JKS 密钥库使用专用格式。建议使用 "keytool -importkeystore -srckeystore server.jks -destkeystore server.jks -deststoretype pkcs12" 迁移到行业标准格式 PKCS12。
```

**http转向https**

开启https后，可以通过配置TomcatServletWebServerFactory(2.x)和EmbeddedServletContainerFactory(1.x)来实现

```
@Configuration
public class ServerConfig {

    // SpringBoot 2.x
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory() {

            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        factory.addAdditionalTomcatConnectors(httpConnector());

        return factory;
    }
    
    //SpringBoot 1.x写法
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        return tomcat;
    }
    

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(8088);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(httpsPort);
        return connector;
    }
}
```


### Favicon配置

服务器网页上的图标，可以通过application.yaml设置不显示

```
spring:
    mvc:
        favicon:
          enabled: false
```

替换则直接将图标favicon.ico放在resource目录下即可

## 数据访问

                                  