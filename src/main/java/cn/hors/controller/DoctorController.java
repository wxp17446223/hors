package cn.hors.controller;

import cn.hors.bean.Doctor;
import cn.hors.bean.Timeline;
import cn.hors.service.DoctorService;
import cn.hors.service.TimelineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Resource
    private DoctorService doctorService;
    @Resource
    private TimelineService timelineService;

    @GetMapping("/d")
    public String findAllDoctor(Model model,Doctor doctor){
        model.addAttribute("doctors", doctorService.findAll(doctor));
        return "keshiys";
    }

    /**
     * 查询医生的信息和排班信息
     * @param model
     * @param doctorId
     * @return
     */
    @GetMapping({"/timeline","/timeline/{doctorId}"})
    public String findDoctorByID(Model model,@PathVariable Integer doctorId){
        Doctor doctor = doctorService.findById(doctorId);
        System.out.println("doctor = " + doctor.getTimelineList());
        model.addAttribute("doctor", doctor);
        return "ys";
    }

    @GetMapping({"/order","/order/{doctorId}"})
    public String order(Model model,@PathVariable Integer doctorId){
        Doctor doctor = doctorService.findById(doctorId);
        System.out.println("doctor = " + doctor.getTimelineList());
        model.addAttribute("doctor", doctor);
        return "orderTime";
    }
}
