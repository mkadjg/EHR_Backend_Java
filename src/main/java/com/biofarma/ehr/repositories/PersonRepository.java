package com.biofarma.ehr.repositories;

import com.biofarma.ehr.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
