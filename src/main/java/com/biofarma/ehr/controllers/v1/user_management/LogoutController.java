package com.biofarma.ehr.controllers.v1.user_management;

import javax.servlet.http.HttpServletRequest;

import com.biofarma.ehr.controllers.v1.user_management.dto.ChangePasswordDto;
import com.biofarma.ehr.dtos.responses.common.ResponseDto;
import com.biofarma.ehr.models.PersonLogin;
import com.biofarma.ehr.repositories.PersonLoginRepository;
import com.biofarma.ehr.utilities.HashUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user-management")
public class LogoutController {

    @Autowired
    PersonLoginRepository personLoginRepository;

    @PostMapping("/logout")
    public ResponseDto activateUser(@RequestParam String email, HttpServletRequest request) {
        ResponseDto response = new ResponseDto();
        try{
            PersonLogin personLogin = personLoginRepository.findByEmail(email).orElseThrow(RuntimeException::new);
            personLogin.setLockStatus((short) 1);
            personLogin.setLockUntil(null);
            personLoginRepository.save(personLogin);

            response.setLinks(request.getRequestURI());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Service Error !");
        }
    }

    @PostMapping("/change-password")
    public ResponseDto activateUser(@RequestParam String email, @RequestBody ChangePasswordDto dto, HttpServletRequest request) {
        ResponseDto response = new ResponseDto();
        try{
            PersonLogin personLogin = personLoginRepository.findByEmail(email).orElseThrow(RuntimeException::new);
            
            String passwordHex = HashUtil.SHA_256.digestAsHex(dto.getOldPassword());
            if (passwordHex.equals(personLogin.getPassword())) {
                passwordHex = HashUtil.SHA_256.digestAsHex(dto.getNewPassword());
                personLogin.setPassword(passwordHex);
                personLoginRepository.save(personLogin);
                
                response.setLinks(request.getRequestURI());
                return response;
            } else {
                throw new UsernameNotFoundException("Invalid password!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Service Error !");
        }
    }
}
