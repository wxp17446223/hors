package cn.hors.controller.backstage;

import cn.hors.bean.News;
import cn.hors.service.NewsService;
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
@RequestMapping("/new")
public class NewController implements BaseController {

    @Resource
    private NewsService newsService;

    @GetMapping
    @PreAuthorize("hasAuthority('/new/r')")
    public String home(){
        return getModelName()+"/index";
    }

    /**
     * 模糊查询新闻信息
     * @param news 新闻bean
     * @param page
     * @param limit
     * @return
     */
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

    /**
     * 新增和修改新闻
     * @param newId 新闻id
     * @param map
     * @return
     */
    @GetMapping({"/edit","/edit/{newId}"})
    @PreAuthorize("hasAuthority('/new/edit/r')")
    public String editor(@PathVariable(required = false) Integer newId, Model map){
        News New = null;
        if(newId != null && newId !=0){
            News news = new News();
            news.setNewId(newId);
            List<News> News = newsService.findNewsAll(news);
            New = News.get(0);
        }
        map.addAttribute("newss",New);
        return getModelName()+"/editor";
    }


    /**
     * 对新闻进行修改
     * @param news 新闻bean
     * @return
     */
    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/new/u')")
    public Map<String,Object> save(News news){
        Map<String,Object> map = new HashMap<>();
        if(news.getNewId() != null && news.getNewId() !=0){
            if (newsService.update(news)) {
                map.put("code",0);
                map.put("msg","修改成功");
            }else{
                map.put("code",1);
                map.put("msg","修改失败");
            }
        }else{
            if (newsService.insert(news)) {
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
