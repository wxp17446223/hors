package cn.hors.controller;

import cn.hors.bean.*;
import cn.hors.service.AccountService;
import cn.hors.service.DoctorService;
import cn.hors.service.NewsService;
import cn.hors.service.UserInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
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
        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        Account accounts= accountServices.login(account, md5Pass);
        if (accounts != null) {
            accounts.setPassword(md5Pass);
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
