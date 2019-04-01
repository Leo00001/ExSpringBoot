package me.spring.boot.data.controller;

import me.spring.boot.data.biz.Address;
import me.spring.boot.data.repo.AddressRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author baiyu
 * <p>
 * Jpa使用
 */
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
