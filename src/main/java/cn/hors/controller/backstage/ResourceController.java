package cn.hors.controller.backstage;

import cn.hors.bean.PResource;
import cn.hors.service.PResourceService;
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

@Controller
@RequestMapping("/resource")
public class ResourceController implements BaseController{
    @Resource
    private PResourceService service;

    @GetMapping
    @PreAuthorize("hasAuthority('/resource/r')")
    public String home(){
        return getModelName()+"/index";
    }

    @GetMapping(headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    @PreAuthorize("hasAuthority('/resource/r')")
    public Map<String,Object> findAll(PResource account, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int limit){
        PageHelper.startPage(page,limit);
        List<PResource> PResources = service.find(account);
        PageInfo<PResource> pageInfo = new PageInfo<>(PResources);
        Map<String,Object> map = new HashMap<>();
        map.put("data",pageInfo.getList());
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("msg","查询成功");
        return map;
    }
    @GetMapping({"/edit","/edit/{id}"})
    @PreAuthorize("hasAuthority('/resource/edit/r')")
    public String editor(@PathVariable(required = false) Integer id, @RequestParam(defaultValue = "-1") Integer pid, Model map){
        PResource pResources = null;
        if(id != null && id !=0){
            PResource account = new PResource();
            account.setId(id);
            List<PResource> PResources = service.find(account);
            pResources = PResources.get(0);
        }else{
            pResources = new PResource();
            pResources.setParent(service.findById(pid));
        }
        map.addAttribute("resource",pResources);
        return getModelName()+"/editor";
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/resource/u')")
    public Map<String,Object> save(PResource account, HttpServletRequest request){
        account.setAuthority(account.getUrl()+"/"+account.getCode());
        System.out.println(account.getAuthority());
        Map<String,Object> map = new HashMap<>();
        if(account.getId() != null && account.getId() !=0){
            if(account.getPid() == null || account.getPid() == -1){
                System.out.println(1);
                map.put("code",2);
                map.put("msg","最终父资源不可进行授权编辑");
                return map;
            }
            if (service.updateResource(account)) {
                System.out.println(2);
                map.put("code",0);
                map.put("msg","修改成功");
            }else{
                map.put("code",1);
                map.put("msg","修改失败");
            }
        }else{
            if (service.insertResource(account)) {
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

    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/resource/d')")
    public Map<String,Object> del(@RequestParam("id") Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        if(service.deleteResourceById(ids)){
            map.put("code",0);
            map.put("msg","删除成功");
        }else{
            map.put("code",1);
            map.put("msg","删除失败");
        }
        return map;
    }
    @Override
    public String getModelName() {
        return "backstage/resource";
    }
}
