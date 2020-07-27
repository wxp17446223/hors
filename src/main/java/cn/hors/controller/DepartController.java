package cn.hors.controller;

import cn.hors.bean.Departments;
import cn.hors.service.DepartmentsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Controller
public class DepartController {
    @Resource
    private DepartmentsService departmentsService;
    @GetMapping("/keshi")
    public String findAllDoctor(Model model, Departments departments){
        model.addAttribute("departments", departmentsService.findAll(departments));
        return "keshi";
    }
}
