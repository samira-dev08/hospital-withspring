package com.company.service.impl;

import com.company.dto.request.ReqPatient;
import com.company.dto.request.ReqToken;
import com.company.dto.response.RespPatient;
import com.company.dto.response.RespStatus;
import com.company.dto.response.Response;
import com.company.entity.Patient;
import com.company.entity.User;
import com.company.entity.UserToken;
import com.company.enums.EnumAvailableStatus;
import com.company.exception.ExceptionConstants;
import com.company.exception.HospitalException;
import com.company.repository.PatientRepository;
import com.company.repository.UserRepository;
import com.company.repository.UserTokenRepository;
import com.company.service.PatientService;
import com.company.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final Utility utility;


    @Override
    public Response<List<RespPatient>> getPatientList(ReqToken reqToken) {
        Response<List<RespPatient>> response = new Response<>();
        try {
            utility.checkToken(reqToken);
            List<Patient> patientList = patientRepository.findAllByActive(EnumAvailableStatus.ACTIVE.value);
            if (patientList.isEmpty()) {
                throw new HospitalException(ExceptionConstants.PATIENT_NOT_FOUND, "Patient not found");
            }
            List<RespPatient> respPatientList = patientList.stream().map(this::mapping).collect(Collectors.toList());
            response.setT(respPatientList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (HospitalException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "INTERNAL_EXCEPTION"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response<RespPatient> getPatientListById(ReqPatient reqPatient) {
        Response<RespPatient> response = new Response<>();
        try {
            Long patientId = reqPatient.getPatientId();
            utility.checkToken(reqPatient.getReqToken());
            if (patientId == null) {
                throw new HospitalException(ExceptionConstants.INVALID_REQUEST_DATA, " Invalid request data");
            }
            Patient patient = patientRepository.findPatientByIdAndActive(patientId, EnumAvailableStatus.ACTIVE.value);
            if (patient == null) {
                throw new HospitalException(ExceptionConstants.PATIENT_NOT_FOUND, "Patient not found");
            }
            RespPatient respPatient = mapping(patient);
            response.setT(respPatient);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (HospitalException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "INTERNAL_EXCEPTION"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response addPatient(ReqPatient reqPatient) {
        Response response = new Response();
        try {
            String name = reqPatient.getName();
            String surname = reqPatient.getSurname();
            ReqToken reqToken = reqPatient.getReqToken();
            utility.checkToken(reqToken);
            if (name == null || surname == null) {
                throw new HospitalException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Patient patient = Patient.builder().
                    name(name).
                    surname(surname).
                    dob(reqPatient.getDob()).
                    phone(reqPatient.getPhone()).
                    address(reqPatient.getAddress())
                    .build();
            patientRepository.save(patient);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (HospitalException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "INTERNAL_EXCEPTION"));
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response updatePatient(ReqPatient reqPatient) {
        Response response = new Response();
        try {
            String name = reqPatient.getName();
            String surname = reqPatient.getSurname();
            Long patientId = reqPatient.getPatientId();
            ReqToken reqToken = reqPatient.getReqToken();
            utility.checkToken(reqToken);
            if (name == null || surname == null || patientId == null) {
                throw new HospitalException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Patient patient = patientRepository.findPatientByIdAndActive(patientId, EnumAvailableStatus.ACTIVE.value);
            if (patient == null) {
                throw new HospitalException(ExceptionConstants.PATIENT_NOT_FOUND, "Patient not found");
            }
            patient.setName(name);
            patient.setSurname(surname);
            patient.setAddress(reqPatient.getAddress());
            patient.setDob(reqPatient.getDob());
            patient.setPhone(reqPatient.getPhone());

            patientRepository.save(patient);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (HospitalException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "INVALID_EXCEPTION"));
        }

        return response;
    }

    @Override
    public Response deletePatient(ReqPatient reqPatient) {
        Response response = new Response();
        try {
            Long patientId = reqPatient.getPatientId();
            ReqToken reqToken=reqPatient.getReqToken();
            utility.checkToken(reqToken);
            if (patientId == null) {
                throw new HospitalException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Patient patient = patientRepository.findPatientByIdAndActive(patientId, EnumAvailableStatus.ACTIVE.value);
            if (patient == null) {
                throw new HospitalException(ExceptionConstants.PATIENT_NOT_FOUND, "Patient not found");
            }
            patient.setActive(EnumAvailableStatus.DEACTIVE.value);
            patientRepository.save(patient);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (HospitalException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "INVALID_EXCEPTION"));
        }


        return response;
    }

    private RespPatient mapping(Patient patient) {
        RespPatient respPatient = RespPatient.builder().
                patientId(patient.getId()).
                name(patient.getName()).
                surname(patient.getSurname()).
                dob(patient.getDob()).
                phone(patient.getPhone()).
                address(patient.getAddress()).
                build();

        return respPatient;


    }
}
