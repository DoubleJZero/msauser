package msauser.api.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import msauser.api.dto.UserInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static msauser.data.entity.QTbUserInfo.tbUserInfo;

/**
 * 사용자 querydsl repository implements
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    /**
     * 사용자 목록 조회 querydsl
     * @return 사용자 목록
     */
    @Override
    public List<UserInfoDto> getUserInfoList() {
        return queryFactory.select(Projections.fields(UserInfoDto.class
                                    , tbUserInfo.userId
                                    , tbUserInfo.userPw
                                    , tbUserInfo.userNm
                                    , tbUserInfo.userBirth
                            )).from(tbUserInfo)
                            .fetch();
    }

    /**
     * 사용자 상세 조회
     * @param userId 사용자 아아디
     * @return 사용자 정보
     */
    @Override
    public UserInfoDto getUserInfoDetail(String userId) {
        return queryFactory.select(Projections.fields(UserInfoDto.class
                        , tbUserInfo.userId
                        , tbUserInfo.userPw
                        , tbUserInfo.userNm
                        , tbUserInfo.userBirth
                )).from(tbUserInfo)
                .where(
                        tbUserInfo.userId.eq(userId)
                )
                .fetchOne();
    }
}
