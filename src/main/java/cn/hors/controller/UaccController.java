package cn.hors.controller;

import cn.hors.bean.Account;
import cn.hors.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/uacc")
public class UaccController {

    @Resource
    private AccountService accountService;


    @PostMapping
    @ResponseBody
    public Map<String,Object> saveAcc(Account account){
        Map<String,Object> results = new HashMap<>();
        account.setRoleId(8);
        account.setPassword(DigestUtils.md5DigestAsHex(account.getPassword().getBytes()));
        if (account.getAccountId()!=null){
            if (accountService.updateByPrimaryKey(account)>0){
                results.put("code",0);
                results.put("msg","修改成功");
            }else {
                results.put("code",1);
                results.put("msg","修改失败");
            }
        }else {
            if(accountService.insertSelective(account)>0){
                results.put("code",0);
                results.put("msg","新增成功");
            }else {
                results.put("code",1);
                results.put("msg","新增失败");
            }
        }
        return results;
    }
}
