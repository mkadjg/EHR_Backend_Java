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
public class MasterReferensi extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "master_referensi_id", nullable = false, unique = true)
    private Integer masterReferensiId;

    @ManyToOne
    @JoinColumn(name = "masref_type_id", referencedColumnName = "masref_type_id")
    private MasRefType masRefType;

    @Column(name = "master_referensi_desc", nullable = false, length = 150)
    private String masterReferensiDesc;

    @Column(name = "status", nullable = false)
    private Short status;
}