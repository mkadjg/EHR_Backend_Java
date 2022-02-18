package com.biofarma.ehr.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "doctor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends BaseModel implements Serializable {

    @Id
    @Column(name = "doctor_id", nullable = false, unique = true)
    private Integer nurseId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "doctor_id", nullable = false, unique = true)
    private Person person;

}
