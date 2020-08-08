package cn.hors.controller;

import cn.hors.bean.Account;
import cn.hors.bean.FeedBack;
import cn.hors.bean.Order;
import cn.hors.bean.UserInfo;
import cn.hors.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserInfoService userservice;
    @Resource
    private AccountService accountService;
    @Resource
    private OrderService orderService;
    @Resource
    private FeedBackService feedBackService;
    @Resource
    private DoctorService doctorService;

    int IDD;

    /**
     * 获得个人数据
     * @param model
     * @param accountId 账号id
     * @return
     */
    @GetMapping({"/info","/info/{accountId}"})
    public String info(ModelMap model,@PathVariable(required = false)Integer accountId){
        UserInfo users=userservice.findByAccId(accountId);
        users.setPicture("/hors/images/"+users.getPicture());
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
            UserInfo user = this.userservice.findById(id);
            model.addAttribute("user",user);
        }
        return getModelName()+"/editor";
    }

    /**
     * 保存修改数据
     * @param user
     * @return
     */
    @PostMapping("/editor")
    @ResponseBody
    public Map<String,Object> save(UserInfo user){
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
            if(userservice.insertSelective(user)>0){
                results.put("code",0);
                results.put("msg","新增成功");
            }else {
                results.put("code",1);
                results.put("msg","新增失败");
            }
        }
        return results;
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

    @PostMapping("/uacc")
    @ResponseBody
    public Map<String,Object> saveAcc(Account account){
        Map<String,Object> results = new HashMap<>();
        account.setRoleId(8);
        account.setPassword(DigestUtils.md5DigestAsHex(account.getPassword().getBytes()));
        if (account.getAccountId()!=null){
            if (accountService.update(account)){
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




    @PostMapping("/userfileUpload")
    public String fileUpload(@RequestPart("file") MultipartFile file, UserInfo user, SessionStatus status) throws FileNotFoundException {
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
        UserInfo users=userservice.findByAccId(accountId);
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
            List<Order> orders = this.orderService.findByUseId(userId);
            model.addAttribute("orders",orders);
        }
        return getModelName()+"/orderUser";
    }

    /**
     * 转跳到添加反馈信息的页面
     * @param userId 用于显示反馈人
     * @param doctorId  用于显示被反馈的医生
     * @param model
     * @return
     */
    @GetMapping("/content/{userId}/{doctorId}")
    public String toContent(@PathVariable(required = false) Integer userId, @PathVariable(required = false) Integer doctorId, Model model){
        if(userId!=null){
            if(doctorId!=null){
                String userName=userservice.findById(userId).getName();
                String doctorName=doctorService.findById(doctorId).getName();
                model.addAttribute("userName",userName);
                model.addAttribute("doctorName",doctorName);
                model.addAttribute("userId",userId);
                model.addAttribute("doctorId",doctorId);
            }

        }
        return getModelName()+"/content";
    }

    /**
     * 新增反馈信息
     * @param feedBack
     * @param model
     * @return
     */
    @PostMapping("/content")
    public String content(FeedBack feedBack, Model model){
        if (feedBackService.insert(feedBack)){
            System.out.println(feedBack.getDoctorId());
            Integer id=feedBack.getUserId();
            return "redirect:/user/contentUser/"+id;
        }else {
            return "redirect:/user/content";
        }
    }

    /**
     * 转跳到反馈信息的页面
     * @param userId 用于显示反馈人
     * @param model
     * @return
     */
    @GetMapping({"/contentUser/{userId}","/contentUser"})
    public String contentAll(@PathVariable(required = false) Integer userId,Model model){
        System.out.println(userId);
        if(userId!=null){
            List<FeedBack> feedBackList=feedBackService.findByUsId(userId);
            model.addAttribute("contents",feedBackList);
        }
        return getModelName()+"/contentUser";
    }

    public String getModelName() {
        return "user";
    }
}
