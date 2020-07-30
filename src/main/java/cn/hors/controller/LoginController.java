package cn.hors.controller;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hors.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * Created by zxf on 2019年9月30日
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private AccountService accountServices;

    /**
     * 登录方法
     *
     * @param account
     * @param password
     * @param session
     * @param attributes
     * @return
     */
//    @PostMapping("/login")
//    public String login(@RequestParam String account, @RequestParam String password, HttpSession session,
//                        RedirectAttributes attributes) {
//
//        Account accounts = accountServices.login(account, password);
//
//        if (accounts != null) {
//            accounts.setPassword(null);
//            session.setAttribute("account", accounts);
//
//            return "redirect:/index";
//        } else {
//            attributes.addFlashAttribute("message", "用户名或密码错误！");
//            return "redirect:/login";
//        }
//    }

    /**
     * 注销方法
     *
     * @param session
     * @return
     */
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("account");
        return "redirect:/index";
    }

    /**
     * 去登录页
     *
     * @return
     */
    @GetMapping
    public String toLogin() {
        return "logon";
    }

}