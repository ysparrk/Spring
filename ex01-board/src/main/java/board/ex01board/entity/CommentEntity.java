package board.ex01board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name= "comment_table")
// 시간 관리로 인해 BaseEntity 상속
public class CommentEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false) // 제약조건
    private String commentWriter;

    @Column
    private String commentContents;

}
