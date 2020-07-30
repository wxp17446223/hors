package cn.hors.controller;

import cn.hors.bean.Account;
import cn.hors.bean.Doctor;
import cn.hors.bean.Userinfo;
import cn.hors.service.AccountService;
import cn.hors.service.UserinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserinfoService userservice;

//    @GetMapping
//    public String home(ModelMap model,Integer id){
//        List<Userinfo> users= (List<Userinfo>) userservice.findById(id);
//        model.addAttribute("users",users);
//        return "index";
//    }
    @GetMapping
    public String home(ModelMap model,String name){
        model.addAttribute("name",name);
        return "/user/index";
    }
}
