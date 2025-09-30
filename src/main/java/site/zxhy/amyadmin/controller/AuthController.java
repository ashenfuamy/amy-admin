package site.zxhy.amyadmin.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.zxhy.amyadmin.dto.GenerateTokenDto;
import site.zxhy.amyadmin.utils.JwtUtil;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtil jwtUtil;

    @PostMapping("/generateToken")
    private String generateToken(HttpServletRequest request,@RequestBody GenerateTokenDto generateTokenDto) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            // 提取并解码Base64字符串
            String base64Credentials = authHeader.substring("Basic ".length());
            String credentials = new String(
                    Base64.getDecoder().decode(base64Credentials),
                    StandardCharsets.UTF_8
            );
            // 分割用户名和密码（格式为"username:password"）
            final String[] values = credentials.split(":", 2);

            String username = values[0];
            String password = values[1];


            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);

            return jwtUtil.generateToken(username);
        }


        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
