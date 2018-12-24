package me.spring.boot.controller;

import me.spring.boot.config.BookSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baiyu
 * <p>
 * 第一个测试控制器
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Value("${book.author}")
    private String bookAuthor;

    @Value("${book.name}")
    private String bookName;

    @Autowired
    private BookSetting bookSetting;

    @GetMapping("/1")
    public String hello() {
        return "Do you read the book '" + bookName + "' writed by " + bookAuthor;
    }

    @GetMapping("/2")
    public String hello2() {
        return "Do you read the book '" + bookSetting.getName() + "' writed by " + bookSetting.getAuthor();
    }
}
