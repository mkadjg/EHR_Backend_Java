package com.biofarma.ehr.controllers.v1.user_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {
    String oldPassword;
    String newPassword;
}
