package cn.hors.controller.backstage;

import cn.hors.bean.Departments;
import cn.hors.bean.Doctor;
import cn.hors.bean.PAccount;
import cn.hors.service.DepartmentsService;
import cn.hors.service.DoctorService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/doctors")
public class DoctorsController implements BaseController {
    @Resource
    private DoctorService doctorService;
    @Resource
    private DepartmentsService departmentsService;

    @GetMapping
    @PreAuthorize("hasAuthority('/doctors/r')")
    public String home(){
        return getModelName()+"/index";
    }

    /**
     * 得到所有的医生信息并返回到首页
     * @param doctor
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    @PreAuthorize("hasAuthority('/doctors/r')")
    public Map<String,Object> findAll(Doctor doctor, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int limit){
        PageHelper.startPage(page,limit);
        List<Doctor> doctors = doctorService.findAll(doctor);
        PageInfo<Doctor> pageInfo = new PageInfo<>(doctors);
        Map<String,Object> map = new HashMap<>();
        map.put("data",pageInfo.getList());
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("msg","查询成功");
        return map;
    }
    @RequestMapping({"/edit","/edit/{doctorId}"})
    public String editor(@PathVariable(required = false) Integer doctorId, Model model){
        if (doctorId!=null &&doctorId !=0){
            Doctor doctor = doctorService.findById(doctorId);
            model.addAttribute("doctor",doctor);
        }
        model.addAttribute("parents",departmentsService.findAllByDid(-1));

        return getModelName()+"/editor";
    }

    /**
     * 二级联动 父科室 查子科室
     * @param dId
     * @return
     */
    @PostMapping("/getType")
    @ResponseBody
    public Map<String,Object> findGetType(@RequestParam Integer dId){
        List<Departments> departs = departmentsService.findAllByDid(dId);
        System.out.println("departs = " + departs);
        Map<String,Object> map = new HashMap<>();
        map.put("data",departs);
        map.put("code",0);
        map.put("count",departs.size());
        map.put("msg","查询成功");

        return map;
    }


    @PutMapping
    @PreAuthorize("hasAuthority('/doctors/u')")
    @ResponseBody
    public Map<String,Object> save(Doctor doctor){
        System.out.println("doctor = " + doctor);
        Map<String,Object> map = new HashMap<>();
        if(doctor.getDoctorId() != null && doctor.getDoctorId() !=0){
            if (doctorService.update(doctor)) {
                map.put("code",0);
                map.put("msg","修改成功");
            }else{
                map.put("code",1);
                map.put("msg","修改失败");
            }
        }else{
            if (doctorService.insert(doctor)) {
                map.put("code",0);
                map.put("msg","新增成功");
            }else{
                map.put("code",1);
                map.put("msg","新增失败");
            }
        }
        return map;
    }

    @Override
    public String getModelName() {
        return "/backstage/doctor";
    }
}
