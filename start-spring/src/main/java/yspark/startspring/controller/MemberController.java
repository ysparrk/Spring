package yspark.startspring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yspark.startspring.service.MemberService;

@Controller
public class MemberController {
    // 멤버 서비스를 가져다 쓴다. new로 받아서 쓸 수 있다. 하지만 별 기능이 없다. 하나만 공용으로 생성하는 것이 좋다.
//    private final MemberService memberService = new MemberService();

    // 스프링이 관리하게 되면 스프링 컨테이너로 등록하고 스프링 컨테이너로부터 받아서 쓸 수 있도록 바꿔야 한다.


    private final MemberService memberService;
    // 생성자 생성(cmd+N)
    @Autowired // 생성자 호출, memberService를 스프링이 스프링 컨테이너에 있는 memberService를 가져다가 연결시켜준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
