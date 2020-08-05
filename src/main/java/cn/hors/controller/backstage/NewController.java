package cn.hors.controller.backstage;

import cn.hors.bean.News;
import cn.hors.service.NewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/new")
public class NewController implements BaseController {

    @Resource
    private NewsService newsService;

    @GetMapping
    @PreAuthorize("hasAuthority('/new/r')")
    public String home(){
        return getModelName()+"/index";
    }

    @GetMapping(headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    @PreAuthorize("hasAuthority('/new/r')")
    public Map<String,Object> findAll(News news, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int limit){
        PageHelper.startPage(page,limit);
        List<News> newsList = newsService.findAllByBlurry(news);
        PageInfo<News> pageInfo = new PageInfo<>(newsList);
        Map<String,Object> map = new HashMap<>();
        map.put("data",pageInfo.getList());
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("msg","查询成功");
        return map;
    }
//
//    @PostMapping
//    @ResponseBody
//    @PreAuthorize("hasAuthority('/new/r')")
//    public Map<String,Object> findAllByBlurry(@RequestParam String title,@RequestParam String content,@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int limit){
//        PageHelper.startPage(page,limit);
//        List<News> allByBlurry = newsService.findAllByBlurry(title, content);
//        PageInfo<News> pageInfo = new PageInfo<>(allByBlurry);
//        Map<String,Object> map = new HashMap<>();
//        map.put("data",pageInfo.getList());
//        map.put("code",0);
//        map.put("count",pageInfo.getTotal());
//        map.put("msg","查询成功");
//        return map;
//    }

    /**
     * 根据id对新闻进行删除
     * @param ids 角色id集合
     * @return
     */
    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/new/d')")
    public Map<String,Object> del(@RequestParam("newId") Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        if(newsService.deleteByIds(ids)){
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
        return "/backstage/news";
    }
}
