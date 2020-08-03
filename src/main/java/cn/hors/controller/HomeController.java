package cn.hors.controller;

import cn.hors.bean.Account;
import cn.hors.bean.Doctor;
import cn.hors.bean.News;
import cn.hors.bean.Userinfo;
import cn.hors.service.AccountService;
import cn.hors.service.DoctorService;
import cn.hors.service.NewsService;
import cn.hors.service.UserinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

@Controller
@SessionAttributes({"accounts","userAcc"})
public class HomeController {

    @Resource
    private AccountService accountServices;

    @Resource
    private UserinfoService userinfoService;

    @Resource
    private DoctorService doctorService;

    @Resource
    private NewsService newsService;

    /**
     * 主页转跳
     * @return
     */
    @GetMapping("/a")
    public String home() {
        return "index";
    }


    /**
     * 去登录页
     */
    @GetMapping("/login")
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
    public String login(@RequestParam String account, @RequestParam String password, Model model,
                        RedirectAttributes attributes) {

        Account accounts = accountServices.login(account, password);

        if (accounts != null) {
            accounts.setPassword(null);
            model.addAttribute("accounts", accounts);
            Userinfo userAcc = userinfoService.findByAccId(accounts.getAccountId());
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
    public String logout(SessionStatus session) {
        session.setComplete();
        return "redirect:/";
    }


    /**
     * 注册页面转跳
     */
    @GetMapping("/register")
    public  String register(){
        return "register";
    }
    @GetMapping("/find")
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
