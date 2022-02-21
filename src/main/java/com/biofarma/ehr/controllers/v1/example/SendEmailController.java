package com.biofarma.ehr.controllers.v1.example;

import com.biofarma.ehr.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/example/send-email")
public class SendEmailController {

    @Autowired
    UserManagementService userManagementService;

    @GetMapping("/basic")
    public void basic() {
        userManagementService.sendEmailForgotPassword("saputra.sndi@gmail.com");
    }
}
