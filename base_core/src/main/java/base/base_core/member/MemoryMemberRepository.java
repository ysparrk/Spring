package base.base_core.member;

import java.util.Map;
import java.util.HashMap;

public class MemoryMemberRepository implements MemberRepository {
    // 구현체 -> implements 하고 Repository

    // 저장소이므로 Map 만들기,
    // 실무에서는 동시성 이슈로 concurrenthashmap 사용
    private static Map<Long, Member> store = new HashMap<>();
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

}
