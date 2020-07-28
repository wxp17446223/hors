package cn.hors.controller;

import cn.hors.bean.Doctor;
import cn.hors.bean.Userinfo;
import cn.hors.service.UserinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private UserinfoService userservice;

    @GetMapping("/user")
    public String findAllUser(Model model, Userinfo user){
        model.addAttribute("users", userservice.findAll(user));
        return "user/index";
    }
}
