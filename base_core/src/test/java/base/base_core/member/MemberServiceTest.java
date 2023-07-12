package base.base_core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService = new MemberServiceImpl();
    
    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L); // 서비스에서 찾아서 test에서 검증

        // then
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
