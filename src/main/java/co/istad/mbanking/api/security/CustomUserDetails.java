package co.istad.mbanking.api.security;

import co.istad.mbanking.api.auth.Role;
import co.istad.mbanking.api.user.Authority;
import co.istad.mbanking.api.user.User;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
public class CustomUserDetails implements UserDetails {
    private User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // create List of simple GrantedAuthority
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for (Role role : user.getRoles()){
            authorityList.add(new SimpleGrantedAuthority(role.getAuthority()));
//            for (Authority authority : role.getAuthority()){
//                authorityList.add(new SimpleGrantedAuthority(authority.getName()));
//            }
        }
        return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
