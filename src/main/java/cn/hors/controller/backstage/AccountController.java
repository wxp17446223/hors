package cn.hors.controller.backstage;

import cn.hors.bean.PAccount;
import cn.hors.bean.PRole;
import cn.hors.consts.SystemConst;
import cn.hors.service.PAccountService;
import cn.hors.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/account")
@SessionAttributes({SystemConst.LOGIN_STATUS, SystemConst.MENUS})
public class AccountController implements BaseController{
    @Resource
    private PAccountService service;
    @Resource
    private RoleService roleService;

    @GetMapping("/a")
    public String h(){
        return "a";
    }

    @GetMapping
    @PreAuthorize("hasAuthority('/account/r')")
    public String home(){
        return getModelName()+ "indextjh";
    }
    @GetMapping("/l")
    public String login(){
//        throw new RuntimeException();
        return "backstage/logins";
    }
    @GetMapping(headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    @PreAuthorize("hasAuthority('/account/r')")
    public Map<String,Object> findAll(PAccount account, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int limit){
        PageHelper.startPage(page,limit);
        List<PAccount> pAccounts = service.find(account);
        PageInfo<PAccount> pageInfo = new PageInfo<>(pAccounts);
        Map<String,Object> map = new HashMap<>();
        map.put("data",pageInfo.getList());
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("msg","查询成功");
        return map;
    }

    @GetMapping({"/edit","/edit/{id}"})
    @PreAuthorize("hasAuthority('/account/c')")
    public String editor(@PathVariable(required = false) Integer id, Model map){
        if(id != null && id !=0){
            PAccount account = new PAccount();
            account.setId(id);
            List<PAccount> pAccounts = service.find(account);
            map.addAttribute("account",pAccounts.get(0));
        }
        return getModelName()+"/editor";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('/account/u')")
    @ResponseBody
    public Map<String,Object> save(PAccount account,@RequestParam("roleIds[]") Integer[] roleIds){
        Map<String,Object> map = new HashMap<>();
        if(account.getId() != null && account.getId() !=0){
            if (service.updateAccount(account,roleIds)) {
                map.put("code",0);
                map.put("msg","修改成功");
            }else{
                map.put("code",1);
                map.put("msg","修改失败");
            }
        }else{
            if (service.insertAccount(account,roleIds)) {
                map.put("code",0);
                map.put("msg","新增成功");
            }else{
                map.put("code",1);
                map.put("msg","新增失败");
            }
        }
        return map;
    }
    @GetMapping({"/role/{accountId}","/role"})
    @PreAuthorize("hasAuthority('/account/role/r')")
    @ResponseBody
    public List<Map<String,Object>> findRole(@PathVariable(required = false) Integer accountId){
        List<PRole> pRoles = roleService.find(null);
        Set<Integer> roleIds = null;
        if(accountId != null) {
            PAccount account = new PAccount();
            account.setId(accountId);
            PAccount accounts = service.find(account).get(0);
            List<PRole> roles = accounts.getRoles();
            //将角色id取出，并构建成set集合
            roleIds = roles.stream().map(role -> role.getId()).collect(Collectors.toSet());
        }
        List<Map<String,Object>> roleList = new ArrayList<>();
        for (PRole pRole : pRoles) {
            Map<String,Object> role = new HashMap<>();
            role.put("name",pRole.getName());
            role.put("value",pRole.getId());
            role.put("selected",roleIds != null?roleIds.contains(pRole.getId()):false);
            roleList.add(role);
        }
        return roleList;
    }

    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/account/d')")
    public Map<String,Object> del(@RequestParam("id") Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        if(service.deleteAccountById(ids)){
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
        return "backstage/account";
    }
}
