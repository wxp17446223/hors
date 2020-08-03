package cn.hors.controller;

import cn.hors.bean.Doctor;
import cn.hors.bean.Order;
import cn.hors.bean.Timeline;
import cn.hors.service.DoctorService;
import cn.hors.service.OrderService;
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
    @Resource
    private OrderService orderService;

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

    /**
     * 通过查询日期上午或下午 对医生进行预约 时间段
     * @param model
     * @param doctorId 医生ID
     * @param line 1 下午  0 上午
     * @param date 日期
     * @return
     */
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

    /**
     * 返回排班表的Id 医生ID 知道时间段 最后进行预约
     * @param model
     * @param tId 排班id
     * @param doctorId 医生Id
     * @return
     */
    @PostMapping({"/order"})
    public String order(Model model,@RequestParam Integer tId,@RequestParam Integer doctorId){
        Doctor doctor = doctorService.findById(doctorId);
        Timeline timeline = timelineService.findById(tId);
        model.addAttribute("doctor", doctor);
        model.addAttribute("timeline", timeline);
        return "orderInfo";
    }

    /**
     * 将预约信息插入到预约表
     * @param order 预约信息
     * @return
     */
    @PostMapping("/orderInfo")
    public String order(Model model,Order order){
//        if (orderService.insert(order)) {
//            if (timelineService.updateQuota(order.getTId())) {
//                model.addAttribute("msg","预约成功");
//            }
//        }else {
//            model.addAttribute("msg","预约失败");
//        }
        System.out.println("order = " + order);
        return "redirect:/depart/keshi";
    }

    @GetMapping("/sreach")
    public String sreachByName(String name,int page,Model model){
        List<Doctor> list = doctorService.findByName(name);
        model.addAttribute("list",list);
        String flag;
        if(list.size()<page*10){
            flag = "t";
        }else {
            flag = "f";
        }
        model.addAttribute("list",list);
        model.addAttribute("flag",flag);
        return "index";
    }

}
