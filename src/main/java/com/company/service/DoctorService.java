package com.company.service;

import com.company.dto.request.ReqDoctor;
import com.company.dto.request.ReqPatient;
import com.company.dto.response.RespDoctor;
import com.company.dto.response.Response;
import com.company.entity.Doctor;

import java.util.List;

public interface DoctorService {
    Response<List<RespDoctor>> getdoctorList();
    Response<List<RespDoctor>> getDoctorListByPositionId(Long positionId);
    Response addDoctor(ReqDoctor reqDoctor);

    Response updateDoctor(ReqDoctor reqDoctor);

    Response deleteDoctor(Long doctorId);
}
