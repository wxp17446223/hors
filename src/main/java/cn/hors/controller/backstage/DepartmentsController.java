package cn.hors.controller.backstage;

import cn.hors.bean.Departments;
import cn.hors.bean.PAccount;
import cn.hors.bean.PResource;
import cn.hors.service.DepartmentsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/departments")
public class DepartmentsController implements BaseController {
    @Resource
    private DepartmentsService service;

    @Override
    public String getModelName() {
        return "backstage/departments";
    }
    /**
     * 进入科室管理
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('/departments/r')")
    public String home(){
        return getModelName()+ "/index";
    }


    @GetMapping(headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    @PreAuthorize("hasAuthority('/departments/r')")
    public Map<String,Object> findAll(Departments departments, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int limit){
        PageHelper.startPage(page,limit);
        List<Departments> departmentsList = service.findAll(departments);
        PageInfo<Departments> pageInfo = new PageInfo<>(departmentsList);
        Map<String,Object> map = new HashMap<>();
        map.put("data",pageInfo.getList());
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("msg","查询成功");
        return map;
    }
    /**
     * 新增和修改科室资源
     * @param id 资源id
     * @param pid 父资源id
     * @param map 结果存储
     * @return
     */
    @GetMapping({"/edit","/edit/{id}"})
    @PreAuthorize("hasAuthority('/departments/edit/r')")
    public String editor(@PathVariable(required = false) Integer id, @RequestParam(defaultValue = "-1") Integer pid, Model map){
        Departments departments = null;
        if(id != null && id !=0){
            departments  = service.findById(id);
        }else{
               departments = new Departments();
               departments.setParent(service.findById(pid));
        }
        map.addAttribute("departments",departments);
        return getModelName()+"/editor";
    }

    /**
     * 保存科室
     * @param department 科室bean
     * @return
     */
    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/departments/u')")
    public Map<String,Object> save(Departments department,@Param("name") String name){
        Map<String,Object> map = new HashMap<>();
        Departments d = new Departments();
        d.setDepartName(department.getDepartName());
        if(service.findAll(d).size()>0&&!department.getDepartName().equals(name)){
            map.put("code",1);
            map.put("msg","科室名称不能重复");
            return map;
        }
        if(department.getDepartId() != null && department.getDepartId() !=0){
            if (service.update(department)) {
                System.out.println(department.getDate());
                System.out.println(2);
                map.put("code",0);
                map.put("msg","修改成功");
            }else{
                map.put("code",1);
                map.put("msg","修改失败");
            }
        }else{
            if(department.getDate()==null||department.getDate().equals("")){
                map.put("code",1);
                map.put("msg","请输入日期");
                return map;
            }
            if (service.insert(department)){
                System.out.println(3);
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
     * 删除科室资源 包括删除父科室资源
     * @param ids
     * @return
     */
    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/departments/d')")
    public Map<String,Object> del(@RequestParam("id") Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        if(service.deleteByIds(ids)){
            map.put("code",0);
            map.put("msg","删除成功");
        }else{
            map.put("code",1);
            map.put("msg","删除失败");
        }
        return map;
    }
}
