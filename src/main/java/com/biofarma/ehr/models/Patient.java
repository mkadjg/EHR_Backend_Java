package com.biofarma.ehr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends BaseModel implements Serializable {

    @Id
    @Column(name = "patient_id", nullable = false, unique = true)
    private Integer patientId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "patient_id", nullable = false, unique = true)
    private Person person;

}
