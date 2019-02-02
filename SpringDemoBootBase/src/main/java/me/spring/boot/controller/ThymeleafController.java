package me.spring.boot.controller;

import me.spring.boot.biz.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/demo/thymeleaf")
public class ThymeleafController {

    @RequestMapping("/base")
    public String baseThymeleafPage(Model pageModel, HttpSession session) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(18);
        userInfo.setName("张三");
        pageModel.addAttribute("user", userInfo);
        pageModel.addAttribute(userInfo);
        pageModel.addAttribute("welcomeTemplateKey", "欢迎 \\{0\\} 参观我们公司！");
        pageModel.addAttribute("nowTime", new Date());

        session.setAttribute("sessionKey", "sessionValue");
        return "demo/user";
    }

}
