
CREATE TABLE `tb_user_info` (
  `USER_ID` varchar(20) NOT NULL COMMENT '사용자아이디',
  `USER_PW` varchar(512) NOT NULL COMMENT '사용자비밀번호',
  `USER_NM` varchar(30) NOT NULL COMMENT '사용자이름',
  `USER_BIRTH` varchar(8) NOT NULL COMMENT '사용자생년월일',
  `RGST_ID` varchar(20) NOT NULL COMMENT '등록자아이디',
  `RGST_DTTM` datetime NOT NULL COMMENT '등록일시',
  `UPDT_ID` varchar(20) NOT NULL COMMENT '수정자아이디',
  `UPDT_DTTM` datetime NOT NULL COMMENT '수정일시',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci COMMENT='사용자정보';