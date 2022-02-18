package com.biofarma.ehr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends BaseModel {
    
    @Id
    @OneToOne
    @MapsId
    @JoinColumn(name = "patient_id")
    private Person doctorId;
}
