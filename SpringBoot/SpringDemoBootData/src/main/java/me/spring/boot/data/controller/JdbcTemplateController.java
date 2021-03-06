package me.spring.boot.data.controller;

import me.spring.boot.data.biz.JdbcUserInfo;
import me.spring.boot.data.biz.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.beans.Introspector;
import java.util.List;
import java.util.Map;

/**
 * @author baiyu
 * <p>
 * JdbcTempalte学习
 */
@Controller
@RequestMapping("/data/jdbc")
public class JdbcTemplateController {

    private Logger logger = LoggerFactory.getLogger(JdbcTemplateController.class.getSimpleName());

    @Resource
    private JdbcTemplate jdbcTemplate;


    /**
     * {@link BeanPropertyRowMapper}对于对象可以映射,但是有规范约束，
     * 对象实体类的字段必须与数据库字段名称一直或者驼峰式命名，否则无法字段映射
     * <p>
     * https://www.cnblogs.com/uu5666/p/8601983.html {@link Introspector}.java实现对象的属性事件方法的处理，传递数据信息
     *
     * @return 返回数据集合
     */
    @GetMapping(value = "/query/bean", produces = "application/json;charset=utf8")
    @ResponseBody
    public List<Product> listProduct() {
        String sql = "select pro_id, pro_name, pro_description, price from al_product";
        // public <T> List<T> queryForList(String sql, Class<T> elementType)
        // 仅仅支持单个列 Class 支持String Boolean... 参考:JdbcUtils.getResultSetValue
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    /**
     * 自定义实现RowMapper接口,将对应字段设置到实体类中
     *
     * @return 用户信息
     * @see JdbcUserInfo.JdbcUserInfoRowMapper
     */
    @GetMapping("/query/custom")
    @ResponseBody
    public List<JdbcUserInfo> listUserInfo() {
        String sql = "select * from al_user_info";
        return jdbcTemplate.query(sql, new JdbcUserInfo.JdbcUserInfoRowMapper());
    }

    @PostMapping("/save")
    public String save(Product product) {
        if (product.getProId() == 0) {
            String insertSql = "insert into al_product (pro_name, pro_description, price) values(?, ?, ?)";
            int code = jdbcTemplate.update(insertSql, product.getPro_name(), product.getProDescription(), product.getPrice());
            logger.info("save a product result : " + code);
        } else {
            String updateSql = "update al_product set pro_name = ?, pro_description = ?, price = ? where pro_id = ?";
            int code = jdbcTemplate.update(updateSql, product.getPro_name(), product.getProDescription(), product.getPrice(), product.getProId());
            logger.info("update a product result : " + code);
        }
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        String deleteSql = "delete from al_product where pro_id = ?";
        int code = jdbcTemplate.update(deleteSql, id);
        logger.info("save a product result : " + code);
        return "redirect:/product/list";
    }

}
