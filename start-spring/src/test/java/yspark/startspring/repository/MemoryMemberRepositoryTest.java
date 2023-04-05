package yspark.startspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import yspark.startspring.domain.Member;

import java.awt.*;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

// public으로 안해도 된다
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test가 끝날때마다 data를 지워준다
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // 1. save test
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);  // save 저장하면 id가 셋팅이 된다

        Member result = repository.findById(member.getId()).get();
        // 검증 준비
        // optional에서 값 꺼낼때 get, 반환타입 이름 result로 설정,
//        System.out.println("result = " + (result == member)); // result가 member랑 같다면 출력해도 된다.
        // Assertions
//        Assertions.assertEquals(member, result); // 같은지 확인한다 (기대하는것, 결과), true라면 출력되진 않지만 돌아간다, false면 에러, org.junit.jupiter.api
        assertThat(member).isEqualTo(result); // org.assertj.core.api
    }

    // 2. findByName test
    @Test  // 이름으로 찾는 것 test
    public void findByName() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        // spring1, spring2라는 회원이 회원가입 되었다.

        // 검증하기
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    // 3. findAll test
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);  // 회원 두명 확인
    }

}
