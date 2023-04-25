package yspark.startspring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import yspark.startspring.domain.Member;
import yspark.startspring.service.MemberService;

import java.util.List;

@Controller
public class MemberController {
    // =================== 스프링 빈 =========================
    // 멤버 서비스를 가져다 쓴다. new로 받아서 쓸 수 있다. 하지만 별 기능이 없다. 하나만 공용으로 생성하는 것이 좋다.
//    private final MemberService memberService = new MemberService();

    // 스프링이 관리하게 되면 스프링 컨테이너로 등록하고 스프링 컨테이너로부터 받아서 쓸 수 있도록 바꿔야 한다.

    private final MemberService memberService;
    // setter 주입(cmd + n)
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    // 생성자 생성(cmd+N)
    @Autowired // 생성자 호출, memberService를 스프링이 스프링 컨테이너에 있는 memberService를 가져다가 연결시켜준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // ===================== 회원 가입 ===============================
    @GetMapping(value="/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    // 회원 컨트롤러에 회원을 실제 등록하는 기능
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 홈 화면으로 보낸다.
    }

    // ====================== 회원 조회 ==================================
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); // member를 다 가져온다
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
