package me.spring.boot.controller;

import me.spring.boot.biz.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/demo/thymeleaf")
public class ThymeleafController {

    @RequestMapping("/base")
    public String baseThymeleafPage(Model pageModel, HttpSession session) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(18);
        userInfo.setName("张三");

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setAge(18);
        userInfo1.setName("张三userInfo1");

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setAge(18);
        userInfo2.setName("张三userInfo2");

        UserInfo userInfo3 = new UserInfo();
        userInfo3.setAge(18);
        userInfo3.setName("张三userInfo3");

        List<UserInfo> users = new ArrayList<>();
        users.add(userInfo);
        users.add(userInfo1);
        users.add(userInfo2);
        users.add(userInfo3);
        pageModel.addAttribute("users", users);

        pageModel.addAttribute("user", userInfo);
        pageModel.addAttribute(userInfo);
        pageModel.addAttribute("welcomeTemplateKey", "欢迎 \\{0\\} 参观我们公司！");
        pageModel.addAttribute("nowTime", new Date());

        session.setAttribute("sessionKey", "sessionValue");
        return "demo/user";
    }

    @RequestMapping("search/{user}")
    public String search(Model model, @PathVariable("user") String user, @RequestParam("name") String name) {
        String text = "This user " + user + " name is " + name + "";
        model.addAttribute("text", text);
        return "demo/other";
    }

}
