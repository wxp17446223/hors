package cn.hors.controller.backstage;

import cn.hors.bean.PResource;
import cn.hors.bean.PRole;
import cn.hors.service.PResourceService;
import cn.hors.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/role")
public class RoleController implements BaseController{
    @Resource
    private RoleService roleService;
    @Resource
    private PResourceService pResourceService;
    @GetMapping
    @PreAuthorize("hasAuthority('/role/r')")
    public String home(){
        return getModelName()+"/index";
    }

    @GetMapping(headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    @PreAuthorize("hasAuthority('/role/r')")
    public Map<String,Object> findAll(PRole account, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int limit){
        PageHelper.startPage(page,limit);
        List<PRole> PRoles = roleService.find(account);
        PageInfo<PRole> pageInfo = new PageInfo<>(PRoles);
        Map<String,Object> map = new HashMap<>();
        map.put("data",pageInfo.getList());
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("msg","查询成功");
        return map;
    }
    @GetMapping({"/edit","/edit/{id}"})
    @PreAuthorize("hasAuthority('/role/edit/r')")
    public String editor(@PathVariable(required = false) Integer id, @RequestParam(defaultValue = "-1") Integer pid, Model map){
        PRole PRole = null;
        if(id != null && id !=0){
            PRole account = new PRole();
            account.setId(id);
            List<PRole> PRoles = roleService.find(account);
            PRole = PRoles.get(0);
        }
        map.addAttribute("role",PRole);
        return getModelName()+"/editor";
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/role/u')")
    public Map<String,Object> save(PRole account, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        if(account.getId() != null && account.getId() !=0){
            if (roleService.update(account)) {
                map.put("code",0);
                map.put("msg","修改成功");
            }else{
                map.put("code",1);
                map.put("msg","修改失败");
            }
        }else{
            if (roleService.insert(account)) {
                map.put("code",0);
                map.put("msg","新增成功");
            }else{
                map.put("code",1);
                map.put("msg","新增失败");
            }
        }
        return map;
    }

    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/role/d')")
    public Map<String,Object> del(@RequestParam("id") Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        if(roleService.deleteById(ids)){
            map.put("code",0);
            map.put("msg","删除成功");
        }else{
            map.put("code",1);
            map.put("msg","删除失败");
        }
        return map;
    }

    @GetMapping("/authorize/{id}")
    @PreAuthorize("hasAuthority('/role/authorize/r')")
    public String authorize(@PathVariable Integer id, Model model){
        model.addAttribute("id",id);
        return getModelName()+"/authorize";
    }

    @PutMapping("/authorize")
    @PreAuthorize("hasAuthority('/role/authorize/r')")
    @ResponseBody
    public Map<String,Object> authorize(@RequestParam(value = "id",required = false) Integer id, @RequestParam(value = "resourceIds[]",required = false) Integer[] resourceIds){
        System.out.println(2);
        Map<String,Object> map = new HashMap<>();
        try {
            pResourceService.authorization(id, resourceIds);
            map.put("code",0);
            map.put("msg","授权成功");
        }catch (Exception e){
            map.put("code",1);
            map.put("msg","授权失败:"+e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
    @GetMapping(path = "/authorize/{id}",headers = "X-Requested-With=XMLHttpRequest")
    @PreAuthorize("hasAuthority('/role/authorize/r')")
    @ResponseBody
    public Map<String,Object> authorize(@PathVariable Integer id){
        System.out.println(3);
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> data = new HashMap<>();
        List<PResource> pResources = pResourceService.find(null);
        PRole role = roleService.findById(id);
        List<PResource> accountResource = role.getResources();
        map.put("code",0);
        map.put("msg","查询成功");
        data.put("list",pResources);
        List<Integer> ids = accountResource.stream().map(res -> res.getId()).collect(Collectors.toList());
        data.put("checkedId",ids);
        map.put("data",data);
        return map;
    }
    @Override
    public String getModelName() {
        return "backstage/role";
    }
}
