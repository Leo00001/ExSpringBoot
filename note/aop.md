# Spring中使用Aop

Aop面向切面，Spring中使用aop需要增加如下依赖

```
       <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring-boot.version}</version>
        </dependency>
       <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>${aspectj.version}</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjrt</artifactId>
            <version>${aspectj.version}</version>
        </dependency>
```

接着按着如下步骤配置：

1. 通过注解@pointcut()创建切点，切点可以是自己配置的注解，也可以是通过[Pointcut Expression](https://www.cnblogs.com/rainy-shurun/p/5195439.html)配置
这里使用自定义注解方式介绍。

2. 创建自定义的切点拦截注解

```
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAction {

    String name();
}
```
3. 在对应的方法上使用注解

```
@Service
public class DemoAnnotationService {

    @LogAction(name = "login function")
    public void login() {
        System.out.println("You request login");
    }


    public void getUserInfo() {
        System.out.println("This is get User info function");
    }
}
```
4. 配置切面执行前后的业务

```
@Aspect
@Component
public class LogAspect {


    @Pointcut("@annotation(com.baiyuas.boot.aop.annotation.LogAction)")
    public void annotationPointCut() {
    }

    /**
     * 匹配使用了@LogAction注解的方法
     *
     * @param joinPoint
     */
    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("This is login after");
    }

    /**
     * 匹配DemoAnnotationService中的所有方法
     * 注： × 号和包名之间有个空格
     * @param joinPoint
     */
    @Before("execution(* com.baiyuas.boot.service.DemoAnnotationService.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("This is login before");
    }
}
```
这里配置的`@After`切入的是通过自定义的注解`@LogAction`标注的方法。而`@Before`会切入是
`DemoAnnotationService`中所有的方法。当然你可以在`LogAspect`中配置多个`@After`指定不同的切入规则

5. 最后如果要使切面生效，需要在Spring配置中启用

```
@Configuration
@ComponentScan("com.baiyuas.boot")
@EnableAspectJAutoProxy
public class AopConfig {
}
```

以上就是Spring Aop的基本使用