package msauser.api.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 공통 유틸
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@Slf4j
public class CommonUtils {

    /**
     * salt 생성
     * @return salt 반환
     */
    public static String getSalt(){
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[20];

        sr.nextBytes(salt);

        StringBuilder sb = new StringBuilder();
        for(byte b : salt) sb.append(String.format("%02x", b));

        return sb.toString();
    }

    /**
     * SHA-256 단반향 암호화
     * @param target 암호화 대상
     * @return 암호화 된 String
     */
    public static String getSha256Encrypt(String target){
        String ret = "";

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //md.update((target+getSalt()).getBytes());
            md.update(target.getBytes());

            byte[] targetSalt = md.digest();

            StringBuilder sb = new StringBuilder();
            for(byte b : targetSalt) sb.append(String.format("%02x", b));

            ret = sb.toString();
        }catch(NoSuchAlgorithmException ne){
            log.warn("###############################################################");
            log.warn("CommonUtils > getSha256Encrypt > target :: {}, error :: {}", target, ne.getMessage());
            log.warn("###############################################################");
        }

        return ret;
    }
}
