package board.ex01board.repository;

import board.ex01board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> { // 상속<entity 클래스, pk type>
}
