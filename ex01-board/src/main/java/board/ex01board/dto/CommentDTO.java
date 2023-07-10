package board.ex01board.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Long id;  // 댓글 id
    private String commentWriter;  // 작성자
    private String commentContents;  // 내용
    private Long boardId; // 게시글 번호
    private LocalDateTime commentCreatedTime; // 댓글 작성 시간

}
