package cn.hors.controller;

import cn.hors.bean.Userinfo;
import cn.hors.service.UserinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.HashMap;
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
    @GetMapping({"/index","/index/{accountId}"})
    public String home(ModelMap model,@PathVariable(required = false)Integer accountId){
        Userinfo users=userservice.findByAccId(accountId);
        model.addAttribute("users",users);
        return  getModelName()+ "indextjh";
    }

    @GetMapping({"/editor","/editor/{id}"})
    public String editor(@PathVariable(required = false)Integer id,Model model){
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


    @GetMapping("/five")
    public String five(){
        return getModelName()+"/five";
    }
    public String getModelName() {
        return "user";
    }
}
