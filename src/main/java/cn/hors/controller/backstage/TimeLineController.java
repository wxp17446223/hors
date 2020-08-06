package cn.hors.controller.backstage;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/timeline")
public class TimeLineController implements BaseController{
    @GetMapping
    @PreAuthorize("hasAuthority('/timeline/r')")
    public String home(){
        return getModelName()+"/index";
    }











    @Override
    public String getModelName() {
        return "/backstage/timeline";
    }
}
