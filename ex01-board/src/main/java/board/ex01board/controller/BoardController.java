package board.ex01board.controller;

import board.ex01board.dto.BoardDTO;
import board.ex01board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board") // 대표 주소 묶기
public class BoardController {
    private final BoardService boardService; // 생성자 주입 방식

    // create.html로 들어가기
    @GetMapping("/create")
    public String saveForm() {
        return "create";
    }
    // 글 작성 ->
    @PostMapping("/create")
    public String save(@ModelAttribute BoardDTO boardDTO) { // create.html의 name과 DTO의 필드값이 동일 -> setter알아서 호출
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index";
    }
}

