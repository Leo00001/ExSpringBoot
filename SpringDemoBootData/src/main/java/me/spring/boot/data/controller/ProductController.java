package me.spring.boot.data.controller;

import me.spring.boot.data.biz.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author baiyu
 * <p>
 * 产品查询
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private JdbcTemplate jdbcTemplate;


    @GetMapping(value = "/all", produces = "application/json;charset=utf8")
    @ResponseBody
    public List<Product> list() {
        String sql = "select pro_name, pro_description from al_product";

        // public <T> List<T> queryForList(String sql, Class<T> elementType)
        // 仅仅支持单个列 Class 支持String Boolean... 参考:JdbcUtils.getResultSetValue

        //BeanPropertyRowMapper对于对象可以映射,但是有规范约束，对象实体类的字段必须与数据库字段名称一直或者驼峰式命名，否则无法字段映射
        // 自定义实现RowMapper接口,将对应字段设置到实体类中
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }
}
