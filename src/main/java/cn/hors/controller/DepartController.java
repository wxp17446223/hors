package cn.hors.controller;

import cn.hors.bean.Departments;
import cn.hors.consts.SystemConst;
import cn.hors.service.DepartmentsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/depart")
@SessionAttributes({SystemConst.PARENT_DEPARTMENTS})
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

    @GetMapping("/luxian")
    public String luxian(){
        return "Article-57";
    }

    /**
     * 就诊流程 页面跳转
     * @return
     */
    @GetMapping("/jiuzhen")
    public String jiuzhen(){
        return "jiuzhen";
    }

    /**
     * 出诊安排 页面跳转
     * @return
     */
    @GetMapping("/chuzhen")
    public String chuzhen(Model model){
        //查到所有父科室
        List<Departments> parentDepartment = departmentsService.findAllByDid(-1);
        model.addAttribute(SystemConst.PARENT_DEPARTMENTS,parentDepartment);
        return "chuzhen";
    }

    /**
     * 出诊单个父科室下的所有医生信息安排 页面跳转
     * @return
     */
    @GetMapping("/chuzhenByDepartId")
    public String chuzhenByDepartId(Model model,@RequestParam Integer departId){
        //查到父科室 跟父科室下的所有子科室 跟子科室下的所有医生
        Departments departId1 = departmentsService.findAllByDepartId(departId);
        model.addAttribute("parent",departId1);
        System.out.println("departId1.getDoctors() = " + departId1.getDoctors());
        return "chuzhen";
    }
}
