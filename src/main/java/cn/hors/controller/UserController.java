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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 个人中心转跳
     */

    @Resource
    private UserinfoService userservice;
    @Resource
    private ServletContext application;

    /**
     * 获得个人数据
     * @param model
     * @param id 账号id
     * @return
     */
    @GetMapping
    public String home(ModelMap model, HttpSession session,Integer id ){
        id=1;
//        Userinfo users=userservice.findById(id);
//        model.addAttribute("users",users);
        model.addAttribute("name","request value");
        session.setAttribute("name","session value");
        application.setAttribute("name","application value");
        return "/user/index";
    }

    public String save(){
        return null;
    }
}
