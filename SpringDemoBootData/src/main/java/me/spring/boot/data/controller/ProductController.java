package me.spring.boot.data.controller;

import me.spring.boot.data.biz.Product;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyu
 * <p>
 * 产品查询
 */
@Controller
@MessageMapping("/product")
public class ProductController {


    @GetMapping("/list")
    @ResponseBody
    public List<Product> list() {

        List<Product> list = new ArrayList<>();

        list.add(new Product("足球", "中国足球世界杯专用品牌"));
        list.add(new Product("足球2", "中国足球世界杯专用品牌2"));
        return list;
    }
}
