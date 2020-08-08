package cn.hors.controller.backstage;

import cn.hors.bean.Departments;
import cn.hors.bean.Doctor;
import cn.hors.bean.Timeline;
import cn.hors.service.DoctorService;
import cn.hors.service.TimelineService;
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
@RequestMapping("/timeline")
public class TimeLineController implements BaseController{
    @Resource
    private TimelineService timelineService;
    @Resource
    private DoctorService doctorService;
    @GetMapping
    @PreAuthorize("hasAuthority('/timeline/r')")
    public String home(){
        return getModelName()+"/index";
    }

    /**
     * 得到所有的排班信息并返回到首页
     * @param timeline
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    @PreAuthorize("hasAuthority('/timeline/r')")
    public Map<String,Object> findAll(Timeline timeline, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int limit){
        PageHelper.startPage(page,limit);
        List<Timeline> timelines = timelineService.findAll(timeline);
        PageInfo<Timeline> pageInfo = new PageInfo<>(timelines);
        Map<String,Object> map = new HashMap<>();
        map.put("data",pageInfo.getList());
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("msg","查询成功");
        return map;
    }

    /**
     * 新增 修改医生排班信息
     * @param tid
     * @param model
     * @return
     */
    @RequestMapping({"/edit","/edit/{tid}"})
    public String editor(@PathVariable(required = false) Integer tid, Model model){
        if (tid!=null &&tid !=0){
            Timeline timeline = timelineService.findById(tid);
            model.addAttribute("timeline",timeline);
            System.out.println("timeline = " + timeline);
        }
        return getModelName()+"/editor";
    }

    /**
     * 保存排班信息
     * @param timeline
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAuthority('/timeline/u')")
    @ResponseBody
    public Map<String,Object> save(Timeline timeline){
        System.out.println("timeline = " + timeline);
        String startTime=timeline.getStartTime();
        String substring =startTime .substring(0,startTime.lastIndexOf(":00"));
        System.out.println("substring = " + substring);
        int i = Integer.parseInt(substring);
        i=i+1;
        String endTime=i+":00";
        timeline.setEndTime(endTime);
        Map<String,Object> map = new HashMap<>();
        if(timeline.getTid() != null && timeline.getTid() !=0){
            if (timelineService.update(timeline)) {
                map.put("code",0);
                map.put("msg","修改成功");
            }else{
                map.put("code",1);
                map.put("msg","修改失败");
            }
        }else{
            if (timelineService.insert(timeline)) {
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
     * 删除排班信息
     * @param ids 用户id集合
     * @return
     */
    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/timeline/d')")
    public Map<String,Object> del(@RequestParam("tid") Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        if(timelineService.delByIds(ids)){
            map.put("code",0);
            map.put("msg","删除成功");
        }else{
            map.put("code",1);
            map.put("msg","删除失败");
        }
        return map;
    }


    /**
     * 一级联动 页面初始化 查科室医生
     * @return
     */
    @PostMapping("/getDoctors")
    @ResponseBody
    public Map<String,Object> findDoctors(Model model){
        List<Doctor> doctors = doctorService.findEntityAll();
        System.out.println("doctors = " + doctors);
        Map<String,Object> map = new HashMap<>();
        map.put("data",doctors);
        map.put("code",0);
        map.put("count",doctors.size());
        map.put("msg","查询成功");

        return map;
    }
    /**
     * ajax 判断医生排班信息是否存在 日期 时间段
     * @param timeline
     * @return
     */
    @RequestMapping(value="/checkTimeline",method=RequestMethod.POST,consumes="application/json")
    @ResponseBody
    public boolean toVerifyTimeline(@RequestBody Timeline timeline) {
        System.out.println("timeline = " + timeline);
        if (timeline.getTid()!=null && timeline.getTid()!=0){
            //修改界面 允许该工号存在 因为是修改
            return true;
        }
        String date = timeline.getDate();
        String startTime = timeline.getStartTime();
        Integer doctorId = timeline.getDoctorId();
        //根据医生Id日期开始时间段查询排班信息
        Timeline startTime1 = timelineService.findByDoctorIdAndDateAndStartTime(doctorId,date, startTime);
        if(startTime1==null)
        {
            //返回true则为没有该排班信息 可以被添加
            return true;
        }else {
            return false;
        }
    }


    @Override
    public String getModelName() {
        return "/backstage/timeline";
    }
}
