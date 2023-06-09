package com.company.controller;

import com.company.dto.request.ReqPatient;
import com.company.dto.request.ReqToken;
import com.company.dto.response.RespPatient;
import com.company.dto.response.Response;
import com.company.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/GetPatientList")
    public Response<List<RespPatient>> getPatientList(@RequestBody ReqToken reqToken) {
        return patientService.getPatientList(reqToken);
    }

    @PostMapping("/GetPatientById")
    public Response<RespPatient> getPatientById(@RequestBody ReqPatient reqPatient) {
        return patientService.getPatientListById(reqPatient);
    }

    @PostMapping("/AddPatient")
    public Response addPatient(@RequestBody ReqPatient reqPatient) {

        return patientService.addPatient(reqPatient);
    }

    @PutMapping("/UpdatePatient")
    public Response updatePatient(@RequestBody ReqPatient reqPatient) {

        return patientService.updatePatient(reqPatient);
    }

    @PutMapping("/DeletePatient")
    public Response deletePatient(@RequestBody ReqPatient reqPatient) {

        return patientService.deletePatient(reqPatient);
    }


//    @GetMapping("/GetPatientById")// requestParam ile
//    public Response<RespPatient> getPatientById(@RequestParam("patientId") Long patientId ){
//        return patientService.getPatientListById(patientId);
//    }

}
