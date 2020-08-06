package cn.hors.controller;

import cn.hors.bean.FeedBack;
import cn.hors.service.DoctorService;
import cn.hors.service.FeedBackService;
import cn.hors.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping
public class ContentController {

    @Resource
    private FeedBackService feedBackService;

    @PostMapping("/user/content")
    public String content(FeedBack feedBack, Model model){
        if (feedBackService.insert(feedBack)){
            System.out.println(feedBack.getDoctorId());
           Integer id=feedBack.getUserId();
            return "redirect:/user/orderUser/"+id;
        }else {
            return "redirect:/user/content";
        }
    }
}
