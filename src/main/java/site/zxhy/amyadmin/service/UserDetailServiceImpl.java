package site.zxhy.amyadmin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import site.zxhy.amyadmin.config.security.Auth;
import site.zxhy.amyadmin.dto.JwtUserDto;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final Auth auth;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(auth.getAdminUserName())) {
            return new JwtUserDto(auth);
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }
}
