package cn.hors.controller.backstage;

import cn.hors.bean.Doctor;
import cn.hors.bean.PAccount;
import cn.hors.service.DoctorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/doctors")
public class DoctorsController implements BaseController {
    @Resource
    private DoctorService doctorService;

    @GetMapping
    @PreAuthorize("hasAuthority('/doctors/r')")
    public String home(){
        return getModelName()+"/index";
    }

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




    @Override
    public String getModelName() {
        return "/backstage/doctor";
    }
}
