package cn.hors.controller;

import cn.hors.bean.Doctor;
import cn.hors.bean.Order;
import cn.hors.bean.Timeline;
import cn.hors.service.DoctorService;
import cn.hors.service.TimelineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public String findDoctorByID(Model model,@RequestParam Integer doctorId){
        Doctor doctor = doctorService.findById(doctorId);
        System.out.println("doctor = " + doctor.getTimelineList());
        model.addAttribute("doctor", doctor);
        return "ys";
    }

    @GetMapping({"/order","/order/{doctorId}"})
    public String order(Model model,@RequestParam Integer doctorId,@RequestParam Integer line,@RequestParam String date){
        Doctor doctor = doctorService.findById(doctorId);
        model.addAttribute("doctor", doctor);
        model.addAttribute("date", date);
        List<Timeline> timelines = timelineService.findByDoctorIdAndDate(doctorId, date);
        model.addAttribute("timelines", timelines);
        if (line.equals("0")||line==0){
            return "orderUpTime";
        }
        return "orderDownTime";
    }
    @PostMapping({"/order"})
    public String order(Model model,@RequestParam Integer tId,@RequestParam Integer doctorId){
        System.out.println("tId = " + tId);
        System.out.println("doctorId = " + doctorId);
        Doctor doctor = doctorService.findById(doctorId);
        model.addAttribute("doctor", doctor);
        model.addAttribute("tId", tId);
        return "orderInfo";
    }
    @PostMapping({"/orderInfo"})
    public String order(Order order){

        return "orderInfo";
    }
}
