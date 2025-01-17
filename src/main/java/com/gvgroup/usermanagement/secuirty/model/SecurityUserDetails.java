package com.gvgroup.usermanagement.secuirty.model;

import com.gvgroup.usermanagement.entity.Authority;
import com.gvgroup.usermanagement.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


public class SecurityUserDetails implements UserDetails {

    private final User user;
    private final Set<? extends GrantedAuthority> authorities;

    public SecurityUserDetails(User user) {
        this.user = user;
        this.authorities = user.getRoles()
                .stream()
                .flatMap(userRole -> userRole.getAuthorities().stream())
                .map(Authority::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getIsActive();
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
        return user.getIsActive();
    }

    public UUID getUserId() {
        return user.getUserId();
    }
}
