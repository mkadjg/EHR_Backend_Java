package com.biofarma.ehr.services.fhir;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import java.util.List;

public interface FhirPatientService {
    List<Patient> findAllPatient() throws JsonProcessingException;
}
