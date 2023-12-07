package msauser.api.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 게시판 DTO
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class BoardDto {
    private Long boardId;                                       //게시판 아이디

    private String boardTitle;                                  //게시판 제목

    private String boardContents;                               //게시판 내용

    private Long boardCount;                                    //게시판 조회수

    private String rgstId;                                      //게시판 등록자 아아디

}
