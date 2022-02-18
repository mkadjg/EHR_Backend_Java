package com.biofarma.ehr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "person_login")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonLogin extends BaseModel implements Serializable {

    @Id
    @Column(name = "person_login_id", nullable = false, unique = true)
    private Integer personLoginId;

    @Column(name = "email", length = 225, nullable = false)
    private String email;

    @Column(name = "sso", nullable = false)
    private Integer sso;

    @Type(type = "text")
    @Column(name = "password", nullable = false)
    private String password;

    @Type(type = "text")
    @Column(name = "answer_of_hint_01", nullable = false)
    private String answerOfHint01;

    @Type(type = "text")
    @Column(name = "answer_of_hint_02", nullable = false)
    private String answerOfHint02;

    @Column(name = "lock_status", nullable = false)
    private Short lockStatus;

    @Column(name = "lock_until", columnDefinition = "DATE")
    private Date lockUntil;

    @OneToOne
    @MapsId
    @JoinColumn(name = "person_login_id", nullable = false, unique = true)
    private Person person;

}
