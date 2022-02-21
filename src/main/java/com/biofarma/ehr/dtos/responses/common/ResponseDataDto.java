package com.biofarma.ehr.dtos.responses.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDataDto {
    String id;
    String type;
    Object attributes;
}
