package site.zxhy.amyadmin.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import site.zxhy.amyadmin.config.bean.JwtProps;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
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

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("permissions", permissions);

        return jwtBuilder
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setSubject(username)
                .compact();
    }

    // 从令牌中解析用户名
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        ArrayList permissions = claims.get("permissions", ArrayList.class);

        ArrayList<GrantedAuthority> objects = new ArrayList<>();

        permissions.stream().forEach(c -> {
            objects.add(new SimpleGrantedAuthority(c.toString()));
        });

        User principal = new User(claims.getSubject(), "******", objects);
        return new UsernamePasswordAuthenticationToken(principal, token, objects);
    }

    // 验证令牌是否有效
    public boolean validateToken(String token) {
        return false;
    }

    public Claims getClaims(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody();
    }


}
