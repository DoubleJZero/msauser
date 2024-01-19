package msauser.api.controller;

import msauser.api.dto.UserInfoDto;
import msauser.api.feign.BoardDto;
import msauser.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 사용자 controller
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    /**
     * 사용자 목록 조회
     * @return 사용자목록
     */
    @GetMapping("/list")
    public List<UserInfoDto> getUserList(){
        return userService.getUserList();
    }

    /**
     * 사용자정보 저장
     * @param userInfoDto 사용자정보DTO
     */
    @PostMapping("/saveUser")
    public void saveUser(@RequestBody UserInfoDto userInfoDto){
        userService.saveUser(userInfoDto);
    }

    /**
     * 사용자 검증
     * @param userInfoDto 사용자정보DTO
     */
    @PostMapping("/checkUser")
    public String checkUser(@RequestBody UserInfoDto userInfoDto){
        return userService.checkUser(userInfoDto);
    }

    /**
     * 사용자 목록 조회 querydsl
     * @return 사용자목록
     */
    @GetMapping("/getUserInfoList")
    public List<UserInfoDto> getUserInfoList(){
        return userService.getUserInfoList();
    }

    /**
     * 사용자정보 삭제
     * @param userInfoDto 사용자정보DTO
     */
    @PostMapping("/deleteUser")
    public void deleteUser(@RequestBody UserInfoDto userInfoDto){
        userService.deleteUser(userInfoDto.getUserId());
    }

    /**
     * 사용자 상세 조회
     * @return 사용자목록
     */
    @GetMapping("/getUserInfoDetail")
    public UserInfoDto getUserInfoDetail(@RequestBody UserInfoDto userInfoDto){
        return userService.getUserInfoDetail(userInfoDto.getUserId());
    }

    /**
     * 게시판 상세 조회
     * @return 게시판 정보
     */
    @GetMapping("/getBoardDetail")
    public BoardDto getBoardDetail(@RequestParam Long boardId){
        return userService.getBoardDetail(boardId);
    }

    @GetMapping("/kafkaTest")
    public void kafkaTest(@RequestParam String rgstId){
        BoardDto boardDto = new BoardDto();
        boardDto.setRgstId(rgstId);
        userService.kafkaTest(boardDto);
    }
}