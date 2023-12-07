package msauser.api.repository;

import msauser.api.dto.UserInfoDto;

import java.util.List;

/**
 * 사용자 querydsl interface
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
public interface UserRepositoryCustom {

    List<UserInfoDto> getUserInfoList();

    UserInfoDto getUserInfoDetail(String userId);
}
