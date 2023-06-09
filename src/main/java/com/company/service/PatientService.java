package com.company.service;

import com.company.dto.request.ReqPatient;
import com.company.dto.request.ReqToken;
import com.company.dto.response.RespPatient;
import com.company.dto.response.Response;

import java.util.List;

public interface PatientService {
     Response<List<RespPatient>> getPatientList(ReqToken reqToken);

     Response<RespPatient> getPatientListById(ReqPatient reqPatient);

     Response addPatient(ReqPatient reqPatient);

     Response updatePatient(ReqPatient reqPatient);

     Response deletePatient(ReqPatient reqPatient);
}
