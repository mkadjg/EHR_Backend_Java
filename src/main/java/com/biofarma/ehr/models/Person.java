package com.biofarma.ehr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false, unique = true)
    private Integer personId;

    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Doctor doctor;

    @OneToOne(mappedBy = "nurse", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Nurse nurse;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Patient patient;
    
    @Column(name = "person_title", nullable = false, length = 3)
    private String personTitle;

    @Column(name = "person_name", nullable = false, length = 75)
    private String personName;

    @Column(name = "birthplace", length = 75)
    private String birthplace;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "gender", nullable = false)
    private Short gender;

    @Column(name = "hp_number", length = 15)
    private String hpNumber;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "citizen_type")
    private Short citizenType;

    @Column(name = "nationality", length = 75)
    private String nationality;

    @Column(name = "language_handicap", length = 75)
    private String languageHandicap;

    @Column(name = "blood_type")
    private Integer bloodType;

    @Column(name = "resus")
    private Short resus;

    @Column(name = "martial_status")
    private Integer martialStatus;

    @Column(name = "status", nullable = false)
    private Short status;

    @Column(name = "date_of_death")
    private Date dateOfDeath;
}
