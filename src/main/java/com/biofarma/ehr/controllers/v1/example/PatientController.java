package com.biofarma.ehr.controllers.v1.example;

import com.biofarma.ehr.services.fhir.FhirPatientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/example/patient")
public class PatientController {

    @Autowired
    FhirPatientService fhirPatientService;

    @GetMapping("/")
    public List<Patient> find() throws JsonProcessingException {
        return fhirPatientService.findAllPatient();
    }

}
