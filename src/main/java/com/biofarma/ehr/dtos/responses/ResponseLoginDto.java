package com.biofarma.ehr.dtos.responses;

import com.biofarma.ehr.dtos.responses.common.BaseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLoginDto extends BaseResponseDto {
    private Integer personId;
    private String personTitle;
    private String personName;
    private String birthplace;
    private Date birthdate;
    private Short gender;
    private String hpNumber;
    private String email;
    private Short citizenType;
    private String nationality;
    private String languageHandicap;
    private Integer bloodType;
    private Short resus;
    private Integer martialStatus;
    private Short status;
    private Date dateOfDeath;
    private String token;
}
