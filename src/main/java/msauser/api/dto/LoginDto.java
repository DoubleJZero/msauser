package msauser.api.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * LoginDto
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@Getter
@Setter
public class LoginDto {
    private String userId;              //사용자 아아디

    private String userPw;              //사용자 비밀번호
}
