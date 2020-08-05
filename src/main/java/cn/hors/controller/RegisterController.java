package cn.hors.controller;

import cn.hors.bean.Account;
import cn.hors.bean.UserInfo;
import cn.hors.service.AccountService;
import cn.hors.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
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
    private UserInfoService userinfoService;


    @PutMapping
    @ResponseBody
    public Map<String,Object>  save(UserInfo user, Account account){
        Map<String,Object> results = new HashMap<>();
        account.setPassword(DigestUtils.md5DigestAsHex(account.getPassword().getBytes()));
            if (accountServices.insertSelective(account) > 0) {
                user.setAccountId(account.getAccountId());
                if (userinfoService.insertSelective(user)>0) {
                    results.put("code", 0);
                    results.put("msg", "注册成功");
                } else {
                    results.put("code", 1);
                    results.put("msg", "注册失败");
                }
            } else {
                results.put("code", 1);
                results.put("msg", "注册失败");
            }
            return results;

    }
}
