package yspark.startspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yspark.startspring.domain.Member;
import yspark.startspring.repository.MemberRepository;
import yspark.startspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@Service // 스프링이 확인하고 MemberService를 스프링 컨테이너에 등록시켜준다.
public class MemberService {
    // test와 인스턴스 같게 하기
    private final MemberRepository memberRepository;
    // new 레포를 직접 생성하는 것이 아니라 외부에서 넣어주도록 바꿔준다.
    @Autowired // memberRepository가 필요한 것을 알고 스프링이 넣어준다
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 1. 회원 가입
    public Long join(Member member) {
        // 중복 회원 금지
        // memberRepository에서 findByName으로 있는지 찾아본다.
        // findByName -> optional member 결과 -> ifPresent
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 2. 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
