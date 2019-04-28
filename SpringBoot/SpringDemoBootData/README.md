
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

### 引入jpa

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### 基本使用

新建接口继承JpaRepository<T, ID>,就可以实现简单的增删改程

```
public interface AddressRepository extends JpaRepository<Address, Long> {

}
```
JpaRepository<T, ID>接口自带基本的增删改查方法

```
	List<T> findAll();
	List<T> findAll(Sort sort);
	void deleteAllInBatch();
    ......
```

在需要使用的地方注入即可

```
@Controller
@RequestMapping("/data/jpa")
public class JpaController {


    @Resource
    private AddressRepository repository;

    @GetMapping("/query/list")
    public String list(Model model) {
        List<Address> list = repository.findAll();
        model.addAttribute("list", list);
        return "/address/list";
    }


    @PostMapping("/save")
    public String save(Address address) {
        repository.save(address);
        return "redirect:address/list";
    }
}

```


### Jpa支持通过多种查询方式

1. 方法名称来进行查询，如下：

```
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAddressByNameAndCity(String name, String city);
}
```
字段对应实体类的属性字段。下面是查询关键字对应Sql说明


| 名称  | 示例 | sql中示例 |
|-------|:---:|-------:|
| And  | findAddressByNameAndCity | where name =? and city = ?     | 
| Between | findAddressByIdBetween  | where id between ? and ?      | 
| LessThan  | findAddressByIdLessThan   | where name < ? |
| GreaterThan  | findAddressByIdGreaterThan   | where id > ? |
| Equal  | findAddressByNameEqual   | where name = ? |
| After  | findAddressByStartDateAfter   | where start_date > ? |
| Before  | findAddressByStartDateBefore   | where start_date < ? |
| IsNull  | findAddressByIdIsNull   | where name is null |
| Like  | findAddressByNameLike   | where name like ? |
| StartingWith  | findAddressByNameStartingWith   | where name like ? 参数后面加% |
| First  | findFirst2ByCityLike   | where city like ? limit ? |
| 。。。  | ?   | undefined |

其他英文名称和sql含义差不多就不一一列举

2. 通过@Query注解


@Query有几个属性说明下

	String value() default "";
	String countQuery() default "";
	boolean nativeQuery() default false;

- value查询的sql,默认为JPQL
- countQuery 用户分页查询中总数的获取
- nativeQuery 是否原生Sql 默认为false如果是指为true value值应该使用原生sql

**基于参数索引方式查询**

```
      /**
        * 查询
        * /    
      @Query(value = "select o from Address o where o.detail like ?1%") // 参数索引
      // @Query(value = "select o from Address o where o.detail like ：detail") 
      // 使用命名参数，参数前可通过@Param注解修改名称 例如：List<Address> findByDetailStartingWith(@Param("detail") String a);
      // 还有一点特别说明，这里Spring会校验所有参数，所以如果使用命名参数方式，则所有参数都需要使用@Param注解，否则报错
      List<Address> findByDetailStartingWith(String detail);
```

**基于命名参数查询**

使用命名参数，参数前可通过@Param注解设置名称
还有一点特别说明，这里Spring会校验所有参数，所以如果使用命名参数方式，则所有参数都需要使用@Param注解，否则报错

```
      @Query(value = "select o from Address o where o.detail like ：detail") 
      List<Address> findByDetailStartingWith(@Param("detail") String detail);
```

**通过@Query修改数据**

修改数据需要配合`@Modifying`注解使用，如下示例

```
      /**
        * 更新
        * /          
      @Modifying
      @Transactional
      @Query("update Address o set o.city = :city where o.id = :id")
      int setCity(@Param("id") long key, @Param("city") String city);
```

3. 通过@NameQuery查询

```
@Entity
@Table(name = "al_address", indexes = {@Index(columnList = "id")})
@NamedQuery(name = "Address.findByProvince", query = "SELECT p FROM Address p WHERE p.province = ?1")
public class Address {
    ......
}

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByProvince(String province);
}

```

这样在调用findByProvince的方法会去执行@NameQuery定义的sql

4. 通过准则查询

使用准则查询，需要啊实现`JpaSpecificationExecutor`接口，看下该接口方法

```
/**
	Optional<T> findOne(@Nullable Specification<T> spec);

	List<T> findAll(@Nullable Specification<T> spec);

	Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);

	List<T> findAll(@Nullable Specification<T> spec, Sort sort);

	long count(@Nullable Specification<T> spec);
```

所以使用findX方法需要传入`Specification`参数，该接口需要实现如下方法

    Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder);
    
toPredicate含有参个参数 

- root 可以获取实体类的属性
- query 查询条件
- criteriaBuilder 查询条件

```
public class ObjSpecs {

    /**
     * 查询石家庄的地址
     *
     * @return
     */
    public static Specification<Address> findAddressFromSjz() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("city"), "石家庄");
    }
}

查询结果集
repository.findAll(ObjSpecs.findAddressFromSjz());
```

5. 分页查询

```
    /**
     * 分页查询
     * @param city
     * @return
     */
    Page<Address> findByCity(String city, Pageable pageable);
```
实现比较简单，返回一个Page对象就可以，传入要查询的内容和需要查询的页码`Pageable.of(page, size)`;

### 自定义Repository



