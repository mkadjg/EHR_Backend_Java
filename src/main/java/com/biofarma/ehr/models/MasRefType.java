package com.biofarma.ehr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "masref_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasRefType extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "masref_type_id", nullable = false, unique = true)
    private Integer masrefTypeId;

    @Column(name = "masref_desc", nullable = false, length = 75)
    private String masrefDesc;

    @Column(name = "status", nullable = false)
    private Short status;
}
