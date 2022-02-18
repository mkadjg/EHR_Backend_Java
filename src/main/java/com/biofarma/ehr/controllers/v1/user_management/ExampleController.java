package com.biofarma.ehr.controllers.v1.user_management;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user-management")
public class ExampleController {

    @GetMapping("/activate-user")
    public Object activateUser() {
        return null;
    }
}
