package msauser.api.service;

import msauser.api.dto.UserInfoDto;
import msauser.api.feign.BoardDto;
import msauser.api.kafka.KafkaMessageProducer;
import msauser.api.kafka.Topic;
import msauser.api.repository.UserRepository;
import msauser.data.entity.TbUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import msauser.api.feign.BoardFeignClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 사용자 service
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    private final BoardFeignClient boardFeignClient;

    private final PasswordEncoder bCryptPasswordEncoder;

    private final KafkaMessageProducer kafkaMessageProducer;

    private final RedisTemplate<String, String> redisTemplate;

    /**
     * 사용자 목록 조회
     * @return 사용자목록
     */
    public List<UserInfoDto> getUserList(){
        return userRepository.findAll().stream().map(UserInfoDto::of).collect(Collectors.toList());
    }

    /**
     * 사용자정보 저장
     * @param userInfoDto 사용자정보DTO
     */
    @Transactional
    public void saveUser(UserInfoDto userInfoDto){
        Optional.ofNullable(userRepository.findByUserId(userInfoDto.getUserId()))
                .ifPresentOrElse(e -> {
                    e.setUserPw(bCryptPasswordEncoder.encode(userInfoDto.getUserPw()));
                    e.setUserNm(userInfoDto.getUserNm());
                    e.setUserBirth(userInfoDto.getUserBirth());
                    e.setUpdtId(userInfoDto.getUserId());
                    userRepository.saveAndFlush(e);
                }, ()->{
                    TbUserInfo entity =  userInfoDto.toEntity();
                    entity.setUserPw(bCryptPasswordEncoder.encode(entity.getUserPw()));
                    entity.setRgstId("system");
                    entity.setUpdtId("system");
                    userRepository.saveAndFlush(entity);
                });
    }

    /**
     * 사용자 검증
     * @param userInfoDto 사용자정보DTO
     */
    public String checkUser(UserInfoDto userInfoDto){
        return Optional.ofNullable(userRepository.findByUserIdAndUserPw(userInfoDto.getUserId(), bCryptPasswordEncoder.encode(userInfoDto.getUserPw()))).isPresent() ? "Y" : "N";
    }

    /**
     * 사용자 목록 조회 querydsl
     * @return 사용자목록
     */
    public List<UserInfoDto> getUserInfoList(){
        return userRepository.getUserInfoList();
    }

    /**
     * 사용자정보 삭제
     * @param userId 사용자 아이디
     */
    @Transactional
    public void deleteUser(String userId){
        boardFeignClient.deleteBoardByRgstId(BoardDto.builder().rgstId(userId).build());
        userRepository.deleteByUserId(userId);
    }

    /**
     * 사용자 상세 조회
     * @return 사용자목록
     */
    public UserInfoDto getUserInfoDetail(String userId){
        return userRepository.getUserInfoDetail(userId);
    }

    /**
     * 게시판 상세 조회
     * @return 게시판 정보
     */
    public BoardDto getBoardDetail(Long boardId){
        return boardFeignClient.getBoardDetail(boardId);
    }

    /**
     * kafka test
     * @param boardDto 게시판
     */
    public void kafkaTest(BoardDto boardDto){
        kafkaMessageProducer.send(Topic.USER_TOPIC.getName(), boardDto);
    }

    /**
     * get redis test
     * @return String
     */
    public String getRedisTest(){
        return redisTemplate.opsForValue().get("redisTestValue");
    }

    /**
     * set redis test
     */
    public void saveRedisTest(){
        redisTemplate.opsForValue().set("redisTestValue", "hello redis!");
    }
}