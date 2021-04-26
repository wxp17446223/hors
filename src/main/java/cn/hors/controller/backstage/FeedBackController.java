package cn.hors.controller.backstage;

import cn.hors.bean.FeedBack;
import cn.hors.service.FeedBackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/feedback")
public class FeedBackController implements BaseController{

    @Resource
    private FeedBackService feedBackService;

    @GetMapping
    @PreAuthorize("hasAuthority('/feedback/r')")
    public String home(){
        return getModelName()+"/index";
    }

    @GetMapping(headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    @PreAuthorize("hasAuthority('/feedback/r')")
    public Map<String,Object> findAll(FeedBack feedBack, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int limit){
        PageHelper.startPage(page,limit);
        List<FeedBack> feedBackList = feedBackService.findAll(feedBack);
        PageInfo<FeedBack> pageInfo = new PageInfo<>(feedBackList);
        Map<String,Object> map = new HashMap<>();
        map.put("data",pageInfo.getList());
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("msg","查询成功");
        return map;
    }

    /**
     * 新增和修改反馈
     * @param backId
     * @param map
     * @return
     */
    @GetMapping({"/edit","/edit/{backId}"})
    @PreAuthorize("hasAuthority('/feedback/edit/r')")
    public String editor(@PathVariable(required = false) Integer backId, Model map){
        FeedBack FeedBack = null;
        if(backId != null && backId !=0){
            cn.hors.bean.FeedBack feedBacks = new FeedBack();
            feedBacks.setBackId(backId);
            List<cn.hors.bean.FeedBack> FeedBacks = feedBackService.findAll(feedBacks);
            FeedBack = FeedBacks.get(0);
        }
        map.addAttribute("feedback",FeedBack);
        return getModelName()+"/editor";
    }

    /**
     * 对新闻进行修改
     * @param feedBack 反馈bean
     * @return
     */
    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/feedback/u')")
    public Map<String,Object> save(FeedBack feedBack){
        Map<String,Object> map = new HashMap<>();
        if(feedBack.getBackId() != null && feedBack.getBackId() !=0){
            if (feedBackService.update(feedBack)) {
                map.put("code",0);
                map.put("msg","修改成功");
            }else{
                map.put("code",1);
                map.put("msg","修改失败");
            }
        }else{
            if (feedBackService.insert(feedBack)) {
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
     * 根据id对反馈信息进行删除
     * @param ids 反馈id集合
     * @return
     */
    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/feedback/d')")
    public Map<String,Object> del(@RequestParam("backId") Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        if(feedBackService.deleteByIds(ids)){
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
        return "backstage/feedback";
    }
}
