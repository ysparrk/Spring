package board.ex01board.controller;

import board.ex01board.dto.BoardDTO;
import board.ex01board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // Pageable import 시 주의
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board") // 대표 주소 묶기
public class BoardController {
    private final BoardService boardService; // 생성자 주입 방식
    // 1. CREATE
    // create.html로 들어가기
    @GetMapping("/create")
    public String saveForm() {
        return "create";
    }
    // 글 작성
    @PostMapping("/create")
    public String save(@ModelAttribute BoardDTO boardDTO) { // create.html의 name과 DTO의 필드값이 동일 -> setter알아서 호출
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index";
    }

    // 2. READ
    // list. DB에서 전체 게시글 가져와서 list.html에 보여주기
    @GetMapping("/")
    public String findAll(Model model) { // 데이터 가져온다 -> 모델 객체 사용
        List<BoardDTO> boardDTOList = boardService.findAll(); // 전체 게시글 가져오기
        model.addAttribute("boardList", boardDTOList); // 가져온 데이터 모델 객체에 담는다
        return "list";
    }

    // detail
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page=1) Pageable pageable) {
        // 1) 해당 게시글의 조회수를 하나 올리고 2) 게시글 데이터를 가져와서 detail.html에 출력 -> 두번 호출 발생
        // 페이지 값을 받아주도록 추가 PageableDefault 페이지 요청이 없는 경우 Pageable 값을 받아준다
        boardService.updateHits(id); // 1)
        BoardDTO boardDTO = boardService.findByID(id); // 2) DTO로 받기
        model.addAttribute("board", boardDTO); // 모델에 담기
        model.addAttribute("page", pageable.getPageNumber()); // 페이지 번호
        return "detail";
    }

    // 3. UPDATE
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        BoardDTO boardDTO = boardService.findByID(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO); // 수정
        model.addAttribute("board", board); // 수정이 반영된 객체를 detail로 가져간다
        return "detail"; // 수정하고나서 상세 페이지로
//        return "redirect:/board/" + boardDTO.getId(); // 이 방법은 조회수에 영향줄 수 있음
    }

    // 4. DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board/";
    }

    // 5. PAGING
    /*
     게시글이 14개라면, 한페이지에 5개씩 -> 3개 페이지
     /board/paging?page=1  -> 몇 페이지에 대한 요청을 받았나
     페이징 처리 된 데이터를 가지고 화면으로 넘어가야 함 -> Model 객체 사용
    */
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
//        pageable.getPageNumber();  // 몇 페이지인지
        Page<BoardDTO> boardList = boardService.paging(pageable);  // 페이지 값을 가져옴

        /* page 갯수가 총 20개라면
        * 현재 사용자가 3페이지라면, user가 있는 페이지는 프론트에서 다르게 보인다 + 링크되어있지 않음
        * 밑에 보여지는 페이지 갯수는 3개
        * 총 페이지 갯수 8개라면, 7 8만 보여야 한다 -> endPage
        *  */

        int blockLimit = 3; // 밑에 보여지는 페이지 갯수
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ... 이 나온다
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages(); // 3 6 9 12 15 ...

        // model에 값을 담아서 paging.html로 간다
        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "paging";
    }

}

