package board.ex01board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board") // 대표 주소 묶기
public class BoardController {
    @GetMapping("/create") // create.html로 들어가기
    public String saveForm() {
        return "create";
    }

    @PostMapping("/create") // 글 작성 ->
    public String save() {

    }
}

