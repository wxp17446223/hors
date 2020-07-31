package cn.hors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(){
        return "index";
    }


    /**
     * 登录用account和password验证并得到account_id
     * 存入一个session
     * 再利用account_id得到用户信息
     * 注册。。。
     */
    public String login(){return null;}
    public String register(){return null;}
}
