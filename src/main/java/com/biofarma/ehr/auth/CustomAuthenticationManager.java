package com.biofarma.ehr.auth;

import com.biofarma.ehr.models.PersonLogin;
import com.biofarma.ehr.repositories.PersonLoginRepository;
import com.biofarma.ehr.utilities.HashUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authentication;
    }

    public Authentication authenticate(CustomAuthenticationToken authentication, PersonLoginRepository personLoginRepository, JwtConfig jwtConfig) throws AuthenticationException {

        PersonLogin personLogin = personLoginRepository.findByEmail(authentication.getPrincipal().toString()).orElse(null);

        if (personLogin == null) {
            throw new UsernameNotFoundException("Invalid username or email!");
        }

        if (personLogin.getPerson().getStatus() == 1) {
            throw new UsernameNotFoundException("Email or user is inactive !");
        }

        if (personLogin.getLockStatus() == 1) {
            throw new UsernameNotFoundException("User already login!");
        }

        String passwordHex = HashUtil.SHA_256.digestAsHex(authentication.getCredentials().toString());
        if (passwordHex.equals(personLogin.getPassword())) {
            personLogin.setLockStatus((short)1);
            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.plusMinutes(jwtConfig.getExpirationInMinutes());
            personLogin.setLockUntil(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
            personLoginRepository.save(personLogin);
            authentication.setDetails(personLogin.getPerson());
            return authentication;
        } else {
            throw new UsernameNotFoundException("Invalid password!");
        }

    }
}
