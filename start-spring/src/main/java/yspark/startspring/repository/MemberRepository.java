// repository.MemberRepository.interface

package yspark.startspring.repository;

import yspark.startspring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 1. save -> 회원이 저장소에 저장
    Optional<Member> findById(Long id); // 2. id로 회원 찾기. 없으면 null를 감싸서 반환
    Optional<Member> findByName(String name); // 3. member 찾기
    List<Member> findAll(); // 4. 모든 회원 list 반환
}
