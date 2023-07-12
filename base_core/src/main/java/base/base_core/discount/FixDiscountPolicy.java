package base.base_core.discount;

import base.base_core.member.Grade;
import base.base_core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 1000원 할인
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {  // vip면 할인
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
