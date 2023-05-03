package board.ex01board.service;

import board.ex01board.dto.BoardDTO;
import board.ex01board.entity.BoardEntity;
import board.ex01board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// DTO -> Entity 변환 (Entity class)
// Entity -> DTO 변환 (DTO class)
// controller 부터 호출 -> DTO로 넘겨받음, repo로 넘길때 Entity로 넘겨준다 ...

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository; // 생성자 주입 방식
    // save 메서드 정의
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity); // 매개변수를 entity 클래스 타입으로 받는다. return도 entity type
    }
}
