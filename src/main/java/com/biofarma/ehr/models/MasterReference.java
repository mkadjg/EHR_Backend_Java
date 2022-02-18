package com.biofarma.ehr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "master_reference")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterReference extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "master_reference_id", nullable = false, unique = true)
    private Integer masterReferenceId;

    @Column(name = "master_reference_desc", nullable = false, length = 150)
    private String masterReferenceDesc;

    @Column(name = "status", nullable = false)
    private Short status;

    @ManyToOne
    @JoinColumn(name = "masref_type_id", referencedColumnName = "masref_type_id")
    private MasRefType masRefType;
}