
## Rxjs 简单使用

[官网](https://cn.rx.js.org)

Rxjs 5.x与6.x以上区别比较大，6.x以后使用管道来使用操作符号

在Intelllij中使用Rxjs需要配置使用Es6 File->Setting->Languages && Frameworks->Javascript选择Es6

``` 
    // 引入js
    <script src="https://cdn.bootcss.com/rxjs/6.3.3/rxjs.umd.js"></script>
    <script>
            // 引入方法
            const {fromEvent} = rxjs;
            const {throttleTime, map} = rxjs.operators;
            const {create} = rxjs.Observable;
            
            fromEvent(window, "load").subscribe(event => console.log(event));
             
            // 操作符
            fromEvent(docuemnt.querySelector(), "click")
                .pipe(
                    // 防抖处理
                    throttleTime(1000)
                )
                .subscribe(() => console.log("receive"));
                 
    </script>
```

## JdbcTemplate

JdbcTemplate 集成比较简单，在SpringBoot中已经进行了自动配置，只需要你在pom.xml中引入jdbc的引用

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

我这里使用的数据库是Mysql.配置Mysql驱动。之后在需要使用的地方注入JdbcTemplate对象就可以。

### 基本用法

`JdbcTemplate`内部提供CURD的基本方法，通过Sql方法完成。

1. 查询

查询提供了获取单个对象，集合，Map等结果查询

* Map结果集

方法：Map<String, Object> queryForMap(String sql) 

示例：

```
@Controller
public class JdbcTemplateController {

    @Resource
    private JdbcTempalte jdbc;

    @GetMappering("/query/single")
    @ResponseBody
    public Map<String, Object> querySingle() {
        String sql = "select * from person where id = xx";
        return jdbc.query(sql);
    } 
    ....
}
```

2. 删除


3. 更新

4. 增加

### 对象映射

### 源码分析


## Jpa

引入jpa

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```





