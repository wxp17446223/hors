package cn.hors.controller.backstage;

import cn.hors.bean.Account;
import cn.hors.bean.UserInfo;
import cn.hors.service.AccountService;
import cn.hors.service.UserInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/patient")
public class PatientController implements BaseController{
    @Resource
    private AccountService accountService;
    @Resource
    private UserInfoService userinfoService;
    @GetMapping
    @PreAuthorize("hasAuthority('/patient/r')")
    public String home(){
        return getModelName()+ "/index";
    }

    @GetMapping(headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    @PreAuthorize("hasAuthority('/patient/r')")
    public Map<String,Object> findAll(Account account, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int limit){
        List<Account> pAccounts = accountService.find(account);
        PageInfo<Account> pageInfo = new PageInfo<>(pAccounts);
        Map<String,Object> map = new HashMap<>();
        map.put("data",pageInfo.getList());
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("msg","查询成功");
        return map;
    }


    /**
     * 新增和修改患者
     * @param id 患者id
     * @param map 结果存储
     * @return
     */
    @GetMapping({"/edit","/edit/{id}"})
    @PreAuthorize("hasAuthority('/patient/edit/r')")
    public String editor(@PathVariable(required = false) Integer id, Model map){
        Account account = null;
        UserInfo userinfo = null;
        if(id != null && id !=0){
            account  = accountService.findById(id);
        }
        map.addAttribute("patient",account);
        return getModelName()+"/edit";
    }


    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/patient/u')")
    public Map<String,Object> save(Account account){
        Map<String,Object> map = new HashMap<>();
        if(account.getAccountId() != null && account.getAccountId() !=0){
            if (accountService.update(account)) {
                UserInfo userInfo = account.getUserInfo();
                if(userinfoService.update(userInfo)) {
                    System.out.println(2);
                    map.put("code", 0);
                    map.put("msg", "修改成功");
                }else{
                    map.put("code",1);
                    map.put("msg","修改失败");
                }
            }else{
                map.put("code",1);
                map.put("msg","修改失败");
            }
        }else{
            if(account.getUserInfo().getRegisterTime()==null||account.getUserInfo().getRegisterTime().equals("")){
                map.put("code",1);
                map.put("msg","请输入日期");
                return map;
            }
            if (accountService.insert(account)){
                if(userinfoService.insert(account.getUserInfo())) {
                    map.put("code", 0);
                    map.put("msg", "新增成功");
                }else{
                    map.put("code",1);
                    map.put("msg","新增失败");
                }
            }else{
                map.put("code",1);
                map.put("msg","新增失败");
            }
        }
        return map;
    }

    /**
     * 删除患者资源
     * @param ids
     * @return
     */
    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/patient/d')")
    public Map<String,Object> del(@RequestParam("id") Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        if(accountService.deleteByIds(ids)&&userinfoService.deleteByIds(ids)){
            map.put("code",0);
            map.put("msg","删除成功");
        }else{
            map.put("code",1);
            map.put("msg","删除失败");
        }
        return map;
    }





    @Override
    public String getModelName() {
        return "backstage/patient";
    }
}
