package base.base_core.member;

public class MemberServiceImpl implements MemberService {
    // 구현체
    // 가입하고 회원 찾으려면, 인터페이스 필요 및 구현객체 선택(MemoryMemberRepository)
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    @Override
    public void join(Member member) {
        // join에서 save 호출하면, 다형성에 의해 MemoryMemberRepository에 있는 save호출(memberRepository 아님)
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
