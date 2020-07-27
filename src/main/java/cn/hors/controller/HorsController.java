package cn.hors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HorsController {
    @GetMapping("/")
    public String home() {
        return "keshiys";
    }
}
