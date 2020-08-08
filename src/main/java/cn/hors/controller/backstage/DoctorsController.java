package cn.hors.controller.backstage;

import cn.hors.bean.Departments;
import cn.hors.bean.Doctor;
import cn.hors.bean.PAccount;
import cn.hors.service.DepartmentsService;
import cn.hors.service.DoctorService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    /**
     * 新增 修改医生信息
     * @param doctorId
     * @param model
     * @return
     */
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
     * 一级联动 页面初始化 查父科室
     * @return
     */
    @PostMapping("/getParent")
    @ResponseBody
    public Map<String,Object> findParents(){
        List<Departments> departs = departmentsService.findAllByDid(-1);
        Map<String,Object> map = new HashMap<>();
        map.put("data",departs);
        map.put("code",0);
        map.put("count",departs.size());
        map.put("msg","查询成功");

        return map;
    }


    /**
     * 删除用户
     * @param ids 用户id集合
     * @return
     */
    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/doctors/d')")
    public Map<String,Object> del(@RequestParam("id") Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        if(doctorService.deleteByIds(ids)){
            map.put("code",0);
            map.put("msg","删除成功");
        }else{
            map.put("code",1);
            map.put("msg","删除失败");
        }
        return map;
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

    /**
     * 保存医生信息
     * @param doctor
     * @return
     */
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


    /**
     * 医生上传图片
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value="/fileImp1",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    // 不返回json数据就要添加ResponseBody标签
    public Map<String,Object> fileImp1(@RequestParam("file") MultipartFile file , HttpServletRequest request) throws FileNotFoundException {
        String path= ResourceUtils.getURL("classpath:static").getPath().replace("%20"," ").replace('/', '\\');
        String realPath=path.substring(1,path.indexOf("\\target"))+"\\src\\main\\resources\\static\\hors\\pic";//从路径字符串中取出工程路径

        System.out.println("realPath = " + realPath);
        File newfile = new File(realPath, file.getOriginalFilename());
        if(!newfile.exists()){
            newfile.mkdirs();
        }
        System.out.println("newfile = " + newfile);
        Map map = new HashMap();

        System.out.printf(file.getOriginalFilename());

        //将文件绝对路径返回
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("src", newfile.getPath());
        try {
            file.transferTo(newfile);
            map.put("msg","OK");
            map.put("code","0");
            map.put("data", data);
            map.put("picturePath","/hors/pic/"+file.getOriginalFilename());//   /hors/pic/5.jpg

        } catch (IOException e) {
            e.printStackTrace();
            map.put("msg","erro");
            map.put("code","1");
            map.put("data", data);
            map.put("picturePath",file.getOriginalFilename());
        }
        /* 返回接口格式
	          "code": 0
	          ,"msg": ""
	          ,"data": {
	           "src": "http://cdn.layui.com/123.jpg"
	          }
	    */
        return map;
    }

    /**
     * ajax 判断医生工号 唯一
     * @param doctor
     * @return
     */
    @RequestMapping(value="/checkJobNumber",method=RequestMethod.POST,consumes="application/json")
    @ResponseBody
    public boolean toVerifyJobNumber(@RequestBody Doctor doctor) {
        System.out.println("doctor = " + doctor);
        if (doctor.getDoctorId()!=null && doctor.getDoctorId()!=0){
            //修改界面 允许该工号存在 因为是修改
            return true;
        }
        Integer jobNumber = doctor.getJobNumber();
        //根据用户名查询管理员(包括status为0的  以防恢复引起bug)
        Doctor doctor1 = doctorService.findByJobNumber(jobNumber);
        if(doctor1==null)
        {
            //返回true则为没有该工号 可以被注册
            return true;
        }else {
            return false;
        }
    }
    @Override
    public String getModelName() {
        return "/backstage/doctor";
    }
}
