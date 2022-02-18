package com.biofarma.ehr.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "doctor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends BaseModel {
    
    @Id
    @OneToOne
    @MapsId
    @JoinColumn(name = "doctor_id")
    private Person doctorId;
}
