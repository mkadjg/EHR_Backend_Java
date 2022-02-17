package com.biofarma.ehr.auth;

import com.biofarma.ehr.models.Users;
import com.biofarma.ehr.repositories.UsersRepository;
import com.biofarma.ehr.utilities.HashUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authentication;
    }

    public Authentication authenticate(Authentication authentication, UsersRepository usersRepository) throws AuthenticationException {

        Users users = usersRepository.findByUsername(authentication.getPrincipal().toString()).orElse(null);

        if (users == null) {
            throw new UsernameNotFoundException("Invalid username!");
        }

        String passwordHex = HashUtil.SHA_256.digestAsHex(authentication.getCredentials().toString());
        if (passwordHex.equals(users.getPassword())) {
            return authentication;
        } else {
            throw new UsernameNotFoundException("Invalid password!");
        }

    }
}
