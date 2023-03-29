package yspark.startspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 삽입
public class HelloController {

    @GetMapping("hello")  // 웹 어플리케이션에서 /hello라고 들어오면 이 method로 들어온다
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";  // templates에서 이름이 같은 html 파일을 찾아서 렌더링 시켜준다.
    }

    // MVC
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API
    @GetMapping("hello-string")
    @ResponseBody  // http에서의 body에 응답 데이터를 직접 넣어준다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;  // hello (name) 요청한 클라이언트에게 그대로 내려간다. view x
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;
        // getter and setter java bin 표준 방식, property 접근 방식이라고 함
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

