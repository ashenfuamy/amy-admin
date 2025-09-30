package site.zxhy.amyadmin.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import site.zxhy.amyadmin.config.security.Auth;

import java.util.Collection;

@RequiredArgsConstructor
@Data
@ToString
public class JwtUserDto implements UserDetails {
    private final Auth auth;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return auth.getAdminPassword();
    }

    @Override
    public String getUsername() {
        return auth.getAdminUserName();
    }
}
