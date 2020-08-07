package cn.hors.controller;

import cn.hors.bean.*;
import cn.hors.bean.Account;
import cn.hors.bean.Doctor;
import cn.hors.bean.News;
import cn.hors.bean.UserInfo;
import cn.hors.message.AliyunMessageUtil;
import cn.hors.service.AccountService;
import cn.hors.service.DoctorService;
import cn.hors.service.NewsService;
import cn.hors.service.UserInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"accounts","userAcc"})
public class HomeController {

    @Resource
    private AccountService accountServices;

    @Resource
    private UserInfoService userinfoService;

    @Resource
    private DoctorService doctorService;

    private String randomNum;
    private String currentTime;

    @Resource
    private NewsService newsService;

    /**
     * 主页转跳
     * @return
     */
    @GetMapping("/")
//    @PreAuthorize("hasAuthority('/index/r')")
    public String home(Model model) {
        List<Doctor> list = doctorService.findByName(null);
        model.addAttribute("list",list);
        return "index";
    }


    /**
     * 去登录页
     */
    @GetMapping("/login")
//    @PreAuthorize("hasAuthority('/index/login/r')")
    public String toLogin() {
        return "login";
    }

    /**
     * 登录方法
     *
     * @param account
     * @param password
     * @param attributes
     * @return
     */
    @PostMapping("/login")
//    @PreAuthorize("hasAuthority('/index/login/r')")
    public String login(@RequestParam String account, @RequestParam String password, Model model,
                        RedirectAttributes attributes) {
        Account accounts= accountServices.login(account, password);
        if (accounts != null) {
            model.addAttribute("accounts", accounts);
            UserInfo userAcc = userinfoService.findByAccId(accounts.getAccountId());
            model.addAttribute("userAcc",userAcc);
            return "index";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误！");
            return "redirect:/login";
        }
    }

    /**
     * 注销方法
     * @param session
     * @return
     */
    @GetMapping("/logout")
//    @PreAuthorize("hasAuthority('/index/logout/r')")
    public String logout(SessionStatus session) {
        session.setComplete();
        return "redirect:/";
    }


    /**
     * 注册页面转跳
     */
    @GetMapping("/register")
//    @PreAuthorize("hasAuthority('/index/register/r')")
    public  String register(){
        return "register";
    }


    @PostMapping("/register")
    @ResponseBody
    public Map<String,Object> save(UserInfo user, Account account){
        Map<String,Object> results = new HashMap<>();
        if (accountServices.insert(account)){
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


    /**
     * 发送验证码
     * @return
     * @throws ClientException
     */
    @PostMapping({"/sendMsg","/sendMsg/{phone}"})
    public String sendMsg(@PathVariable(required = false)String phone) throws Exception {
        randomNum = createRandomNum(6);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 5);
        currentTime = sf.format(c.getTime());// 生成5分钟后时间，用户校验是否过期
        String jsonContent = "{\"code\":\"" + randomNum + "\"}";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("phoneNumber", phone);
        paramMap.put("msgSign", "hors手机验证");
        paramMap.put("templateCode", "SMS_198932705");
        paramMap.put("jsonContent", jsonContent);
        SendSmsResponse sendSmsResponse = AliyunMessageUtil.sendSms(paramMap);
        System.out.println(sendSmsResponse.getCode());
        if(!(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK"))) {
            if(sendSmsResponse.getCode() == null) {
                //这里可以抛出自定义异常
                throw new RuntimeException("发送失败");
            }
            if(!sendSmsResponse.getCode().equals("OK")) {
                //这里可以抛出自定义异常
                throw new RuntimeException("发送失败");
            }
        }
        return null;
    }

    @PostMapping({"/validateNum","/validateNum/{msgNum}"})
    public Map<String, Object> validateNum(@PathVariable String msgNum) {
        Map<String,Object>map=new HashMap<>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        String tamp = sf.format(c.getTime());
        System.out.println(msgNum);
        System.out.println(randomNum);
        System.out.println(currentTime);
        if (tamp.compareTo(currentTime) > 0) {
            if (msgNum.equals(randomNum)){
                //校验成功
                map.put("code",0);
                map.put("msg","验证通过");

            }else {
                //验证码不正确，校验失败
                map.put("code",1);
                map.put("msg","验证失败");
            }
        } else {
            // 超时
            map.put("code",2);
            map.put("msg","验证超时");
        }
        return map;
    }

    /**
     * 生成随机数
     * @param num 位数
     * @return
     */
    public static String createRandomNum(int num){
        String randomNumStr = "";
        for(int i = 0; i < num;i ++){
            int randomNum = (int)(Math.random() * 10);
            randomNumStr += randomNum;
        }
        return randomNumStr;
    }


    /**
     * 注册
     * @param user
     * @param account
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public Map<String,Object> save(UserInfo user, Account account){
        Map<String,Object> results = new HashMap<>();
        account.setPassword(DigestUtils.md5DigestAsHex(account.getPassword().getBytes()));
        if (accountServices.insertSelective(account) > 0) {
            user.setAccountId(account.getAccountId());
            if (userinfoService.insert(user)) {
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


    @GetMapping("/find")
//    @PreAuthorize("hasAuthority('/index/find/r')")
    public String find(String name,Integer type,Model map){
        if(type == 1){
            List<Doctor> dlist = doctorService.findByName(name);
            List<News> nlist = newsService.findByTitleOrContent(name);
            map.addAttribute("dlist",dlist);
            map.addAttribute("olist",nlist);
            return "search";
        }else if(type ==2){
            List<Doctor> list = doctorService.findByName(name);
            map.addAttribute("list",list);
            return "dsearch";
        }
        List<News> list = newsService.findByTitleOrContent(name);
        map.addAttribute("list",list);
        return "newsearch";
    }


}
