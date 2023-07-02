package board.ex01board.service;

import board.ex01board.dto.BoardDTO;
import board.ex01board.entity.BoardEntity;
import board.ex01board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// DTO -> Entity 변환 (Entity class)
// Entity -> DTO 변환 (DTO class)
// controller 부터 호출 -> DTO로 넘겨받음, repo로 넘길때 Entity로 넘겨준다 ...

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository; // 생성자 주입 방식
    // 1. CREATE
    // save 메서드 정의
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity); // 매개변수를 entity 클래스 타입으로 받는다. return도 entity type
    }
    // 2. READ
    // list
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll(); // 1. list형태의 entity 가져온다
        List<BoardDTO> boardDTOList = new ArrayList<>(); // 2.DTO객체로 옮겨 담아서 controller로 return
        // 3. entity -> DTO 객체로 옮겨 담는다, 반복문을 돌려서 꺼내오기
        for (BoardEntity boardEntity: boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList; // 리스트 return
    }

    // detail
    @Transactional // JPA에서 제공하는 메서드 외에 별도로 추가되는 메서드 사용하는 경우, 영속성 컨텐츠 처리
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findByID(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) { // 있으면
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity); // DTO로 변환해서 return
            return boardDTO;
        } else {
            return null;
        }
    }

    // 3. UPDATE
    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntitiy(boardDTO); // entity로 변환
        boardRepository.save(boardEntity);
        return findByID(boardDTO.getId());
    }

    // 4. DELETE
    public void delete(Long id) {
        boardRepository.deleteById(id); // 삭제만 처리
    }


    // 5. PAGING
    public Page<BoardDTO> paging(Pageable pageable) {
        // 한 페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬!!
        int page = pageable.getPageNumber() - 1;  // page 위치에 있는 값은 0 부터 시작한다
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수
        // 정렬 기준 -> Sort.by ~~~ 내림차순, entity에 있는 기준
        Page<BoardEntity> boardEntities =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부

        // 엔티티로 담겨 있어 DTO로 가져가야 한다
        /* board는 엔티티 객체, 이 변수를 하나씩 꺼내서 DTO 객체로 옮겨담는다
        * 목록 : id, writer, title, hits, createdTime의 정보를 담는다.
        *  */
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));

        // 객체를 controller 쪽으로 return
        return boardDTOS;

    }
}
