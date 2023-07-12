package base.base_core.order;

import base.base_core.discount.DiscountPolicy;
import base.base_core.discount.FixDiscountPolicy;
import base.base_core.member.Member;
import base.base_core.member.MemberRepository;
import base.base_core.member.MemoryMemberRepository;

public class OderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 회원찾기
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 고정할인 정책


    // OrderService는 할인에 대해 몰라서 discountPolicy에서 알아서 해서 결과만 던져 달라고 설계 됨
    // 단일체계 원칙에 잘 설계됨
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원 찾고
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인하는데 멤버를 넘긴다
        // 주문을 만들어서 반환하면 OrderService의 역할이 끝난다
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
