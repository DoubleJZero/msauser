package msauser.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 게시판 Feign Client
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@FeignClient(name="board-service",url= "http://127.0.0.1:9000/board-service")
public interface BoardFeignClient {

    /**
     * 게시판 상세 조회 other service feign call
     * @return 게시판 목록
     */
    @RequestMapping(method= RequestMethod.GET, value="/v1/feign/getBoardDetail/{boardId}")
    BoardDto getBoardDetail(@PathVariable("boardId") Long boardId);

    /**
     * 사용자 삭제시 게시판 등록자 게시물 cascade 삭제
     * @param boardDto 게시판 정보 Dto
     */
    @RequestMapping(method= RequestMethod.POST ,value="/v1/feign/deleteBoardByRgstId")
    void deleteBoardByRgstId(@RequestBody BoardDto boardDto);
}
