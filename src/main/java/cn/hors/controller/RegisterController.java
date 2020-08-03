package cn.hors.controller;

import cn.hors.bean.Account;
import cn.hors.bean.Userinfo;
import cn.hors.service.AccountService;
import cn.hors.service.UserinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private AccountService accountServices;

    @Resource
    private UserinfoService userinfoService;


    @PutMapping
    @ResponseBody
    public Map<String,Object> save(Userinfo user,Account account){
        Map<String,Object> results = new HashMap<>();
        if (accountServices.insert(account)>0){
            user.setAccountId(account.getAccountId());
            if(userinfoService.insert(user)){
                results.put("code",0);
                results.put("msg","注册成功");
            }else {
                results.put("code",1);
                results.put("msg","注册失败");
            }
        }else {
            results.put("code",1);
            results.put("msg","注册失败");
        }
        return results;
    }
}
