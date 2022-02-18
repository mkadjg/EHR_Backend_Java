package com.biofarma.ehr.dtos.responses.common.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationMetaData {
    int page;
    int size;
    int pagesAmount;
    int dataAmount;
}
