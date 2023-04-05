package yspark.startspring.service;

import yspark.startspring.domain.Member;
import yspark.startspring.repository.MemberRepository;
import yspark.startspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
