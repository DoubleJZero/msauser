package msauser.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msauser.api.dto.UserInfoDto;
import msauser.api.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자 controller other service feign call
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
@RequestMapping("/v1/feign")
public class FromOtherServiceCallController {

    private final UserService userService;

    /**
     * 사용자 상세 정보 조회
     * @param userId 사용자 아이디
     */
    @GetMapping("/getUserInfoDetail/{userId}")
    public UserInfoDto getUserInfoDetail(@PathVariable String userId) {
        return userService.getUserInfoDetail(userId);
    }
}
