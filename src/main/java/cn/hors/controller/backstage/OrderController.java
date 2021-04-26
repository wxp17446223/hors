package cn.hors.controller.backstage;

import cn.hors.bean.*;
import cn.hors.service.DepartmentsService;
import cn.hors.service.OrderService;
import cn.hors.service.TimelineService;
import cn.hors.service.UserInfoService;
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
@RequestMapping("/order")
public class OrderController implements BaseController{

    @Resource
    private OrderService orderService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private DepartmentsService departmentsService;
    @Resource
    private TimelineService timelineService;

    @GetMapping
    @PreAuthorize("hasAuthority('/order/r')")
    public String home(){
        return getModelName()+"/index";
    }

    @GetMapping(headers = "X-Requested-With=XMLHttpRequest")
    @ResponseBody
    @PreAuthorize("hasAuthority('/order/r')")
    public Map<String,Object> findAll(Order order, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int limit){
        PageHelper.startPage(page,limit);
        List<Order> orderList = orderService.findAll(order);
        PageInfo<Order> pageInfo = new PageInfo<>(orderList);
        Map<String,Object> map = new HashMap<>();
        map.put("data",pageInfo.getList());
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("msg","查询成功");
        return map;
    }

    /**
     * 新增和修改订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping({"/edit","/edit/{orderId}"})
    @PreAuthorize("hasAuthority('/order/edit/r')")
    public String editor(@PathVariable(required = false) Integer orderId, Model map){
        Order Order = null;
        if(orderId != null && orderId !=0){
            Order order = new Order();
            order.setOrderId(orderId);
            List<Order> Orders = orderService.findAll(order);
            Order = Orders.get(0);
        }
        List<Departments>  departs = departmentsService.findChildDepart();
        map.addAttribute("order",Order);
        map.addAttribute("departs",departs);
        return getModelName()+"/editor";
    }

    /**
     * 二级联动 查所有子科室
     * @return

     */
    @PostMapping("/getChildKeshi")
    @ResponseBody
    public Map<String,Object>  getChildKeshi() {
        List<Departments>  departs = departmentsService.findChildDepart();
        Map<String,Object> map = new HashMap<>();
        map.put("data",departs);
        map.put("code",0);
        map.put("count",departs.size());
        map.put("msg","查询成功");
        return map;
    }

    /**
     * 通过科室ID查询该科室下所有医生
     * @param departId
     * @return
     */
    @PostMapping("/getDoctor")
    @ResponseBody
    public Map<String,Object> getDoctor(@RequestParam Integer departId){
        Departments departments = departmentsService.findAllByDepartId(departId);
        List <Doctor> doctors = departments.getDoctors();
        System.out.println("doctors = " + doctors);
        Map<String,Object> map = new HashMap<>();
        map.put("data",doctors);
        map.put("code",0);
        map.put("count",doctors.size());
        map.put("msg","查询成功");
        return map;
    }

    @PostMapping("/getTimeline")
    @ResponseBody
    public Map<String,Object> getTimeline(@RequestParam Integer doctorId){
        List<Timeline> timelineList = timelineService.findByDoctorId(doctorId);
        System.out.println("timelineList = " + timelineList);
        Map<String,Object> map = new HashMap<>();
        map.put("data",timelineList);
        map.put("code",0);
        map.put("count",timelineList.size());
        map.put("msg","查询成功");
        return map;
    }

    /**
     * 对订单进行修改
     * @param order 订单bean
     * @return
     */
    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/order/u')")
    public Map<String,Object> save(Order order){
        Map<String,Object> map = new HashMap<>();
        if(order.getOrderId() != null && order.getOrderId() !=0){
            if (orderService.update(order)) {
                map.put("code",0);
                map.put("msg","修改成功");
            }else{
                map.put("code",1);
                map.put("msg","修改失败");
            }
        }else{
            System.out.println(order);
            if (orderService.insert(order)) {
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
     * 根据id对订单信息进行删除
     * @param ids 订单id集合
     * @return
     */
    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('/order/d')")
    public Map<String,Object> del(@RequestParam("orderId") Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        if(orderService.delByIds(ids)){
            map.put("code",0);
            map.put("msg","删除成功");
        }else{
            map.put("code",1);
            map.put("msg","删除失败");
        }
        return map;
    }

    @PostMapping(value="/checkUserPhone",consumes="application/json")
    @ResponseBody
    public Map<String,Object> toVerifyUserPhone(@RequestBody UserInfo userInfo ,Model model ) {
        System.out.println("---------"+userInfo);
        UserInfo userInfo1=userInfoService.findByPhone(userInfo.getPhone());
        Map<String,Object> map = new HashMap<>();
        if(userInfo1!=null)
        {
            System.out.println("userId------"+userInfo1.getUserId());
            model.addAttribute("userInfo1",userInfo1);
            map.put("userId",userInfo1.getUserId());
            map.put("bool", true);
        }else {
            map.put("userId",null);
            map.put("bool", false);
        }
        return map;
    }


    @Override
    public String getModelName() {
        return "backstage/order";
    }
}
