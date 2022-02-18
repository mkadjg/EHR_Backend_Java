package com.biofarma.ehr.services.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FhirPatientServiceImpl implements FhirPatientService {

    @Value("${fhir.url}")
    String fhirUrl;

    @Autowired
    ObjectMapper objectMapper;

    FhirContext ctx = FhirContext.forR4();
    IParser parser = ctx.newJsonParser();

    @Override
    public List<Patient> findAllPatient() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String fhirPatientUrl = fhirUrl + "/Patient";
        try {
            List<Patient> patients = new ArrayList<>();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> httpEntity = new HttpEntity<>("{}", httpHeaders);
            ResponseEntity<String> response = restTemplate.exchange(fhirPatientUrl, HttpMethod.GET, httpEntity, String.class);

            Bundle bundle = parser.parseResource(Bundle.class, response.getBody());
            for (Bundle.BundleEntryComponent entryComponent : bundle.getEntry()) {
                Patient patient = parser.parseResource(Patient.class, entryComponent.getResource().toString());
                patients.add(patient);
            }
            return patients;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Service Error !");
        }

    }
}
