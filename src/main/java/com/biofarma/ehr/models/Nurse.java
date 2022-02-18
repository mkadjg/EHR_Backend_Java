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
public class Nurse extends BaseModel {
    
    @Id
    @OneToOne
    @MapsId
    @JoinColumn(name = "nurse_id")
    private Person nurseId;
}
