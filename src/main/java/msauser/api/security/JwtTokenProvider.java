package msauser.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * JwtTokenProvider
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${token.access-expired-time}")
    private long ACCESS_EXPIRED_TIME;

    @Value("${token.refresh-expired-time}")
    private long REFRESH_EXPIRED_TIME;

    @Value("${token.secret}")
    private String SECRET;

    private static Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    public String createJwtAccessToken(String userId, String uri, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles", String.join(",", roles));

        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(
                        new Date(System.currentTimeMillis() + ACCESS_EXPIRED_TIME)
                )
                .setIssuedAt(new Date())
                .signWith(key)
                .setIssuer(uri)
                .compact();
    }

    public String createJwtRefreshToken() {
        Claims claims = Jwts.claims();
        claims.put("value", UUID.randomUUID());

        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(
                        new Date(System.currentTimeMillis() + REFRESH_EXPIRED_TIME)
                )
                .setIssuedAt(new Date())
                .signWith(key)
                .compact();
    }

    private Claims getClaimsFromJwtToken(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Date getExpiredTime(String token) {
        return getClaimsFromJwtToken(token).getExpiration();
    }

    public String getClaimSubject(String token){
        return getClaimsFromJwtToken(token).getSubject();
    }

}
