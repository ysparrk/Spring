package board.ex01board.controller;

import board.ex01board.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    @PostMapping("/save")
    // ajax 요청이므로 ResponseBody 필요
    public @ResponseBody String save(@ModelAttribute CommentDTO commentDTO) {
        System.out.println("commentDTO" + commentDTO);
        return "요청 성공";
    }
}
