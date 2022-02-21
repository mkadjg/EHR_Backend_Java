package com.biofarma.ehr.repositories;

import com.biofarma.ehr.models.PersonLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonLoginRepository extends JpaRepository<PersonLogin, Integer> {

    @Query("select pl from PersonLogin pl where pl.email=:email")
    Optional<PersonLogin> findByEmail(String email);
}
