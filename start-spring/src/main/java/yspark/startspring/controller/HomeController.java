package yspark.startspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")  // 처음 도메인으로 들어올때 호출된다
    public String home() {
        return "home";
    }
}
