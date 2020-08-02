package cn.hors.controller;

import cn.hors.bean.Departments;
import cn.hors.service.DepartmentsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/depart")
public class DepartController {
    @Resource
    private DepartmentsService departmentsService;
    @GetMapping("/")
    public String home() {
        return "keshi";
    }

    /**
     * 查询所有科室
     * @param model
     * @param departments
     * @return
     */
    @GetMapping("/keshi")
    public String findAllDepartment(Model model, Departments departments){
        model.addAttribute("departments", departmentsService.findAll(departments));
        return "keshi";
    }

    /**
     * 通过dId查询所有列表
     * @param model
     * @param dId
     * @return
     */
    @GetMapping("/keshilist")
    public String findAllByDId(Model model, @RequestParam Integer dId){
        model.addAttribute("departs", departmentsService.findAllByDid(dId));
        return "keshilist";
    }

    /**
     * 查询所有父ID列表
     * @param model
     * @return
     */
    @GetMapping("/keshi/-1")
    public String findAllByDId(Model model){
        model.addAttribute("departs", departmentsService.findAllByDid(-1));
        return "keshi";
    }

    @GetMapping("/keshimx")
    public String findByDepartId(Model model,@RequestParam Integer departId){
        model.addAttribute("keshi", departmentsService.findById(departId));
        return "keshimx";
    }

    @GetMapping("/keshiys")
    public String findAllByDepartId(Model model,@RequestParam Integer departId){
        model.addAttribute("keshiys", departmentsService.findAllByDepartId(departId));
        return "keshiys";
    }
    @GetMapping("/jiuzhen")
    public String jiuzhen(){
        return "jiuzhen";
    }
}
