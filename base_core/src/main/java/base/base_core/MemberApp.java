package base.base_core;

import base.base_core.member.Grade;
import base.base_core.member.Member;
import base.base_core.member.MemberService;
import base.base_core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member); // member를 넣으면 회원가입 : id -> 1(L은 Long 타입), ....

        // 가입한 멤버와 find 멤버가 같아야 한다
        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.printf("find Member = " + findMember.getName());
    }
}
