package base.base_core;

import base.base_core.member.Grade;
import base.base_core.member.Member;
import base.base_core.member.MemberService;
import base.base_core.member.MemberServiceImpl;
import base.base_core.order.OderServiceImpl;
import base.base_core.order.Order;
import base.base_core.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP); // VIP인 멤버 만들기
        memberService.join(member);  // 멤버 객체에 넣기

        Order order = orderService.createOrder(memberId, "itemA", 10000); // 10000인 상품 구매 -> order가 생긴다

        System.out.println("order = " + order);  // toString에 있는 것으로 호출되어 출력
        System.out.println("order.calculatePrice() = " + order.calculatePrice());  // 계산된 가격 출력
    }

}
