package com.biofarma.ehr.repositories;

import com.biofarma.ehr.models.Person;
import com.biofarma.ehr.models.PersonLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonLoginRepository extends JpaRepository<PersonLogin, Integer> {
}
