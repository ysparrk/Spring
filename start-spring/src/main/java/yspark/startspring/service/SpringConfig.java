package yspark.startspring.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yspark.startspring.domain.Member;
import yspark.startspring.repository.MemberRepository;
import yspark.startspring.repository.MemoryMemberRepository;

@Configuration
public class SpringConfig {

    @Bean // 실행할 때 configuration을 읽고, 스프링 빈을 등록할거야! 라고 인식
    public MemberService memberService() {
        return new MemberService(memberRepository());  // 생성자에서 뭘 넣어주어야 한다 -> memberRepo
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    // 실행할 때
    // 1) 멤버 서비스와 멤버 레포를 둘다 스프링 빈에 등록한다
    // 2) 스프링 빈에 등록되어 있는 멤버 레포를 멤버 서비스에 넣어준다.

}
