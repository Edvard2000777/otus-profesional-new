package Yaremchuk.https.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryUserService implements UserDetailsService {
    private final String USER = "user";
    private final String ADMIN = "admin";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GrantedAuthority userAuthority = new SimpleGrantedAuthority("USER");
        GrantedAuthority adminAuthority = new SimpleGrantedAuthority("ADMIN");

        if (USER.equals(username)) {
            return new User(USER, USER, List.of(userAuthority));
        } else if (ADMIN.equals(username)) {
            return new User(ADMIN,ADMIN,List.of(adminAuthority));
        }
        throw new UsernameNotFoundException("Указанный пользователь не найден");
    }
}
