package com.biofarma.ehr.repositories;

import java.util.Optional;

import com.biofarma.ehr.models.PersonLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonLoginRepository extends JpaRepository<PersonLogin, Integer> {

    @Query("FROM PersonLogin p WHERE p.email = ?1 ")
    Optional<PersonLogin> findByEmail(String email);
}
