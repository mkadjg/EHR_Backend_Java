package com.biofarma.ehr.dtos.requests.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDataDto {
    private String type;
    private Object attributes;
}
