package cn.hors.controller;

import cn.hors.bean.Account;
import cn.hors.bean.Doctor;
import cn.hors.bean.Userinfo;
import cn.hors.service.AccountService;
import cn.hors.service.UserinfoService;
import cn.hutool.core.convert.Convert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    int IDD;

    /**
     * 获得个人数据
     * @param model
     * @param accountId 账号id
     * @return
     */
    @GetMapping
    public String home(ModelMap model,Integer accountId){
        accountId=1;
        Userinfo users=userservice.findByAccId(accountId);
        model.addAttribute("users",users);
        return "/user/index";
    }

    @GetMapping({"/editor","/editor/{id}"})
    public String editor(@PathVariable(required = false)Integer id,Model model){
        System.out.println(id);
        if (id!=null){
            Userinfo user = this.userservice.findById(id);
            model.addAttribute("user",user);
        }
        return getModelName()+"/editor";
    }

    @PutMapping
    @ResponseBody
    public Map<String,Object> save(Userinfo user){
        Map<String,Object> results = new HashMap<>();
        if (user.getUserId()!=null){
            if (userservice.update(user)){
                results.put("code",0);
                results.put("msg","修改成功");
            }else {
                results.put("code",1);
                results.put("msg","修改失败");
            }
        }else {
            if(userservice.insert(user)){
                results.put("code",0);
                results.put("msg","新增成功");
            }else {
                results.put("code",1);
                results.put("msg","新增失败");
            }
        }
        return results;
    }

    public String getModelName() {
        return "user";
    }
}
