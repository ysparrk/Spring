package yspark.startspring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yspark.startspring.domain.Member;
import yspark.startspring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    // clear를 위해 멤버레포지토리 가져오기
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
     /* MemberService의 MemoryMemberRepository가 Test와 다른게 문제다.
        → 다른 인스턴스라 같은 레포를 테스트 해야 하는데 다른 레포가 테스트 되고 있다.
        ⇒ 같은 인스턴스 쓰게 하기
     * */


    // test는 한글로 적어도 된다
    @Test
    void 회원가입() {
        // 1. given 어떤 데이터 기반
        Member member = new Member();
        member.setName("hello");

        // 2. when 이걸 검증하는구나
        Long saveId = memberService.join(member);  // 저장한 id 검증

        // 3. then  결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
//        memberService.join(member1);
//        memberService.join(member2);  // 같은 spring이 들어오므로 validate 검증에서 예외가 발생

//        // 1. 예외 잡기 try-catch
//        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//            // 예외 발생, 예외 메세지가 같은지 본다 ->  정상적으로 성공
//        }

        // 2.
        memberService.join(member1);
        // 람다 로직을 실행하기 위해 예외가 발생해야 한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}