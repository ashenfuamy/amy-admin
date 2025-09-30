package site.zxhy.amyadmin.utils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import site.zxhy.amyadmin.config.JwtProps;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtUtil implements InitializingBean {
    private JwtBuilder jwtBuilder;
    private JwtParser jwtParser;

    private final JwtProps jwtProps;


    @Override
    public void afterPropertiesSet() throws Exception {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProps.getSecret()));
        jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
        jwtBuilder = Jwts.builder().signWith(secretKey);
    }


    // 生成JWT令牌
    public String generateToken(String username, List<String> permissions) {

        HashMap<Object, Object> claims = new HashMap<>();
        claims.put("permissions", permissions);

        return null;
    }

    // 从令牌中解析用户名
    public String getUsernameFromToken(String token) {
        return null;
    }

    // 验证令牌是否有效
    public boolean validateToken(String token) {
        return false;
    }


}
