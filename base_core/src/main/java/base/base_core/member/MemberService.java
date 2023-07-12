package base.base_core.member;

public interface MemberService {
    // 회원가입 -> join
    void join(Member member);

    // 회원 조회
    Member findMember(Long memberId);
}
