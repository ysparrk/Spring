package yspark.startspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 삽입
public class HelloController {

    @GetMapping("hello")  // 웹 어플리케이션에서 /hello라고 들어오면 이 method로 들어온다
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";  // templates에서 이름이 같은 html 파일을 찾아서 렌더링 시켜준다.

    }
}
