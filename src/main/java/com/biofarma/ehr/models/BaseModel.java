package com.biofarma.ehr.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseModel {

    private String modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @PrePersist
    public void prePersist() {
        this.modifiedDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedDate = new Date();
    }


}
