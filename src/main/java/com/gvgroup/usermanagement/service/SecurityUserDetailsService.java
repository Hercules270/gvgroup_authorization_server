package com.gvgroup.usermanagement.service;

import com.gvgroup.usermanagement.entity.User;
import com.gvgroup.usermanagement.model.SecurityUserDetails;
import com.gvgroup.usermanagement.service.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserQueryService userQueryService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userQueryService.findUserByUserName(username)
                .map(SecurityUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found."));
    }
}
