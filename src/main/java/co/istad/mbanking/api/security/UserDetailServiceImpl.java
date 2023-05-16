package co.istad.mbanking.api.security;

import co.istad.mbanking.api.auth.AuthMapper;
import co.istad.mbanking.api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final AuthMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mapper.loadUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User is not found"));

        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUser(user);
        return  userDetails;
    }
}
