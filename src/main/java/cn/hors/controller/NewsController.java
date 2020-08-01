package cn.hors.controller;

import cn.hors.bean.News;
import cn.hors.service.DepartmentsService;
import cn.hors.service.NewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Resource
    private DepartmentsService departmentsService;
    @Resource
    private NewsService newsService;

    /**
     * 查询所有新闻信息
     * @param model
     * @param news
     * @return
     */
    @GetMapping("/all")
    public String findNewsAll(Model model, News news,@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "3")int limit){
        PageHelper.startPage(page,limit);
        List<News> newsAll = newsService.findNewsAll(news);
        PageInfo<News> pageInfo = new PageInfo<>(newsAll);
        model.addAttribute("news", newsAll);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("departs", departmentsService.findAllByDid(-1));
        return "news";
    }

    /**
     * 查询单条具体信息
     * @param model
     * @param newId
     * @return
     */
    @GetMapping("/new")
    public String findByNewId(Model model, @RequestParam Integer newId){
        model.addAttribute("newss", newsService.findByNewId(newId));
        return "newslist";
    }

    @GetMapping("/scanCount")
    public String scanCounter(Model model,@RequestParam Integer newId) {
        Integer counter = newsService.scanCounter(newId);
        model.addAttribute("news", newsService.findByNewId(newId));
        return "newslist";
    }


}
