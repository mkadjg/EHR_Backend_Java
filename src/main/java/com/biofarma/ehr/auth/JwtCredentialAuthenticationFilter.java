package com.biofarma.ehr.auth;

import com.biofarma.ehr.dtos.requests.LoginDto;
import com.biofarma.ehr.dtos.requests.common.RequestDataDto;
import com.biofarma.ehr.dtos.requests.common.RequestDto;
import com.biofarma.ehr.dtos.responses.ResponseLoginDto;
import com.biofarma.ehr.dtos.responses.common.ResponseDataDto;
import com.biofarma.ehr.dtos.responses.common.ResponseDto;
import com.biofarma.ehr.models.Person;
import com.biofarma.ehr.models.PersonLogin;
import com.biofarma.ehr.repositories.PersonLoginRepository;
import com.biofarma.ehr.repositories.UsersRepository;
import com.biofarma.ehr.utilities.constants.UserManagementTypeConstant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class JwtCredentialAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private final CustomAuthenticationManager authManager;

    private final JwtConfig jwtConfig;

    private final PersonLoginRepository personLoginRepository;

    public JwtCredentialAuthenticationFilter(CustomAuthenticationManager authManager, PersonLoginRepository personLoginRepository, JwtConfig jwtConfig) {
        this.authManager = authManager;
        this.jwtConfig = jwtConfig;
        this.personLoginRepository = personLoginRepository;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            RequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), RequestDto.class);
            LoginDto userCredentials = new ObjectMapper().convertValue(requestDto.getData().getAttributes(), LoginDto.class);
            CustomAuthenticationToken authToken = new CustomAuthenticationToken(userCredentials.getEmail(),
                    userCredentials.getPassword());
            return authManager.authenticate(authToken, personLoginRepository, jwtConfig);
        } catch (IOException e) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Long now = System.currentTimeMillis();
            String token = Jwts.builder()
                .setSubject(auth.getName())
                .claim("authorities", auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration() * 1000L))  // in milliseconds
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();
            response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ResponseLoginDto attributes = mapper.convertValue(auth.getDetails(), ResponseLoginDto.class);
            attributes.setToken(token);

            ResponseDataDto responseDataDto = new ResponseDataDto();
            responseDataDto.setAttributes(attributes);
            responseDataDto.setType(UserManagementTypeConstant.USER_AUTH_TYPE);
            responseDataDto.setId(String.valueOf(attributes.getPersonId()));

            ResponseDto responseDto = new ResponseDto();
            responseDto.setData(responseDataDto);

            Map<String, Object> meta = new HashMap<>();
            meta.put("message", "User successfully login!");
            responseDto.setMeta(meta);

            Map<String, Object> links = new HashMap<>();
            links.put("self", request.getLocalName() + jwtConfig.getUri());
            responseDto.setLinks(links);

            response.getWriter().write(mapper.writeValueAsString(responseDto));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error");
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ResponseDto responseDto = new ResponseDto();

            Map<String, Object> meta = new HashMap<>();
            meta.put("status", "error");
            meta.put("message", failed.getMessage());
            responseDto.setMeta(meta);

            Map<String, Object> links = new HashMap<>();
            links.put("self", request.getLocalName() + jwtConfig.getUri());
            responseDto.setLinks(links);

            response.getWriter().write(mapper.writeValueAsString(responseDto));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error");
        }

    }
}
