package site.zxhy.amyadmin.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("auth")
public class Auth {
    private String adminUserName;
    private String adminPassword;
}
