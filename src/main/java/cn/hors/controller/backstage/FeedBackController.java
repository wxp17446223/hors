package cn.hors.controller.backstage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feedback")
public class FeedBackController implements BaseController{

    @Override
    public String getModelName() {
        return "backstage/feedback";
    }
}
