package cn.hors.controller;

import cn.hors.bean.Account;
import cn.hors.bean.Order;
import cn.hors.bean.PAccount;
import cn.hors.bean.Userinfo;
import cn.hors.service.AccountService;
import cn.hors.service.OrderService;
import cn.hors.service.UserinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserinfoService userservice;
    @Resource
    private AccountService accountService;
    @Resource
    private OrderService orderService;

    int IDD;

    /**
     * 获得个人数据
     * @param model
     * @param accountId 账号id
     * @return
     */
    @GetMapping({"/info","/info/{accountId}"})
    public String info(ModelMap model,@PathVariable(required = false)Integer accountId){
        Userinfo users=userservice.findByAccId(accountId);
        model.addAttribute("users",users);
        return  getModelName()+ "/info";
    }

    /**
     * 修改界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping({"/editor","/editor/{id}"})
    public String editor(@PathVariable(required = false)Integer id,Model model){
        if (id!=null){
            Userinfo user = this.userservice.findById(id);
            model.addAttribute("user",user);
        }
        return getModelName()+"/editor";
    }




    /**
     * 修改密码
     * @param accountId
     * @param model
     * @return
     */
    @GetMapping({"/uacc","/uacc/{accountId}"})
    public String uacc(@PathVariable(required = false)Integer accountId,Model model){
        if (accountId!=null){
            Account account = this.accountService.selectByPrimaryKey(accountId);
            model.addAttribute("account",account);
        }
        return getModelName()+"/uacc";
    }


    /**
     * 保存修改数据
     * @param user
     * @return
     */
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

    @PostMapping("/userfileUpload")
    public String fileUpload(@RequestPart("file") MultipartFile file, Userinfo user, SessionStatus status) throws FileNotFoundException {
        String serverpath= ResourceUtils.getURL("classpath:static").getPath().replace("%20"," ").replace('/', '\\');
        String realPath=serverpath.substring(1,serverpath.indexOf("\\target"))+"\\src\\main\\resources\\static\\hors\\images";//从路径字符串中取出工程路径
        System.out.println("realPath = " + realPath);
        Path path = Paths.get(realPath);
        try {
            if(!Files.exists(path)){
                Files.createDirectories(path);
            }
            //得到上传文件的实际名称
            String fileName = file.getOriginalFilename();
            user.setPicture(fileName);
            System.out.println(fileName);
            //String fileName = file.getOriginalFilename();
            //path.resolve(fileName) 得到完整的file路径
            file.transferTo(path.resolve(fileName));
            userservice.updatePic(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        status.setComplete();
        return "/user/editor";
    }


    /**
     * 页面转跳
     * @param model
     * @param accountId
     * @return
     */
    @GetMapping({"/index","/index/{accountId}"})
    public String home(ModelMap model,@PathVariable(required = false)Integer accountId){
        Userinfo users=userservice.findByAccId(accountId);
        model.addAttribute("users",users);
        return  getModelName()+ "/index";
    }


    /**
     * 预约信息
     * @param userId
     * @param model
     * @return
     */
    @GetMapping({"/orderUser","/orderUser/{userId}"})
    public String orderUser(@PathVariable(required = false)Integer userId,Model model){
        System.out.println(userId);
        if (userId!=null){
            Order orders = this.orderService.findByUseId(userId);
            model.addAttribute("orders",orders);
        }
        return getModelName()+"/orderUser";
    }
    public String getModelName() {
        return "user";
    }
}
