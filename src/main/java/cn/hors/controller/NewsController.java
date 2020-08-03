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
     *
     * @param model
     * @param news
     * @return
     */
    @GetMapping("/all")
    public String findNewsAll(Model model, News news, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int limit) {
        PageHelper.startPage(page, limit);
        List<News> newsAll = newsService.findNewsAll(news);
        PageInfo<News> pageInfo = new PageInfo<>(newsAll);
        model.addAttribute("news", newsAll);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("departs", departmentsService.findAllByDid(-1));
        return "news";
    }
    /**
     * 查询所有公告信息
     * @param model
     * @param news
     * @return
     */
    @GetMapping("/notice")
    public String findNoticeAll(Model model, News news, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int limit) {
        PageHelper.startPage(page, limit);
        List<News> newsAll = newsService.findNoticeAll(news);
        PageInfo<News> pageInfo = new PageInfo<>(newsAll);
        model.addAttribute("news", newsAll);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("departs", departmentsService.findAllByDid(-1));
        return "newsNotice";
    }
    /**
     * 查询单条具体信息
     * @param model
     * @param newId
     * @return
     */
    @GetMapping("/new")
    public String findByNewId(Model model, @RequestParam Integer newId) {
        News news = newsService.findByNewId(newId);
        model.addAttribute("newss", news);
        return "newslist";
    }

    /**
     * 计算每次点击进去的浏览数量
     * @param model
     * @param newId
     * @return
     */
    @GetMapping("/scanCount")
    public String scanCounter(Model model, News news, @RequestParam Integer newId) {
        Integer counter = newsService.scanCounter(newId);
        List<News> newsAll = newsService.findNewsAll(null);
        int i = newsAll.indexOf(newsService.findByNewId(newId));
        model.addAttribute("newsAll",newsAll);
        model.addAttribute("news", newsService.findByNewId(newId));
        if (i == 0) {
            model.addAttribute("newsup", newsService.findByNewId(newId));
            System.out.println("i:"+newsAll.get(i));
            System.out.println(newsAll.size());
            System.out.println("i+1:"+newsAll.get(i + 1));
            model.addAttribute("newsdown", newsService.findByNewId(newsAll.get(i + 1).getNewId()));
        } else if (i == newsAll.size() - 1) {
            model.addAttribute("newsup", newsService.findByNewId(newsAll.get(i - 1).getNewId()));
            model.addAttribute("newsdown", newsService.findByNewId(newsAll.get(i).getNewId()));
        } else {
            model.addAttribute("newsup", newsService.findByNewId(newsAll.get(i - 1).getNewId()));
            model.addAttribute("newsdown", newsService.findByNewId(newsAll.get(i + 1).getNewId()));
        }

        return "newslist";
    }


}
