package com.app.controlefinanceiro.service.security.impl;

import com.app.controlefinanceiro.model.user.User;
import com.app.controlefinanceiro.model.userRole.UserRole;
import com.app.controlefinanceiro.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    // forma do Spring Security buscar os usuários pelo username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if ("testuser".equals(username)) {
            return new User(
                    "testuser",
                    "password",
                    UserRole.USER,
                    AuthorityUtils.createAuthorityList("ROLE_USER"));
        }

        return repository.findByLogin(username);

    }
}
