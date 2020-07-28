package cn.hors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HorsController {

    @GetMapping("/{id},/")
    public String home(Model model,@PathVariable Integer id) {

        return "ys";
    }
    @GetMapping("/p1")
    public String p1() {
        return "keshi";
    }
    @GetMapping("/p2")
    public String p2() {
        return "keshiys";
    }
}
