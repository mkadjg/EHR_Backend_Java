package com.biofarma.ehr.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "nurse")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nurse extends BaseModel implements Serializable {

    @Id
    @Column(name = "nurse_id", nullable = false, unique = true)
    private Integer nurseId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "nurse_id", nullable = false, unique = true)
    private Person person;

}
