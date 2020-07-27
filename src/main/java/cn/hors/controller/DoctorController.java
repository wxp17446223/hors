package cn.hors.controller;

import cn.hors.bean.Doctor;
import cn.hors.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class DoctorController {

    @Resource
    private DoctorService doctorService;
    @GetMapping("/")
    public String findAllDoctor(Model model,Doctor doctor){
        model.addAttribute("doctors", doctorService.findAll(doctor));
        return "keshiys";
    }
}
