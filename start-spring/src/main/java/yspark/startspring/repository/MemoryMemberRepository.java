// repository.MemoryMemberRepository.class

package yspark.startspring.repository;

import yspark.startspring.domain.Member;

import java.util.*;

// 구현체
// 0. 초기 설정
public class MemoryMemberRepository implements MemberRepository { // option + enter -> implements methods
    // key = id, value = member, Map import 하기
    private static Map<Long, Member> store = new HashMap<>(); // 실무에서는 동시성 문제로 공유되는 변수 일때는 ConcurrentHashMap 사용
    private static long sequence = 0L; // sequence는 key값 생성, 실무에서는 long 보다는 동시성 문제를 고려해 Atomiclong 사용

    // 1. 저장하기
    @Override
    public Member save(Member member) {  // 이미 name은 넘어왔다. (domain에서의 id와 name)
        member.setId(++sequence);  // id값 세팅 -> sequence 값 +1
        store.put(member.getId(), member);  // store에 저장 -> map에 저장
        return member; // 저장된 결과 반환
    }
    // 2. id 찾기
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // store에서 꺼낸다. 결과가 없다면? -> optional로 감싸서 반환한다. -> 클라이언트가 뭘 할 수 있다.
    }
    // 3. name 찾기
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))  // 람다, 가져온 name이 파라미터로 넘어온 name과 같은지 확인(필터) 같은 경우에만 필더링이 된다 -> 찾으면 반환
                .findAny();  // 하나로 찾는다. 결과가 Optional로 반환, 끝까지 찾았는데 없으면 optional에 null이 포함되어 반환
    }
    // 4.
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());  // 반환은 list로 한다. store에 있는 values(member)반환
    }
}