package com.biofarma.ehr.dtos.responses.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private ResponseDataDto data;
    private Object meta;
    private Object links;
}
