package com.company.controller;

import com.company.dto.request.ReqDoctor;
import com.company.dto.response.RespDoctor;
import com.company.dto.response.Response;
import com.company.exception.ExceptionConstants;
import com.company.exception.HospitalException;
import com.company.service.DoctorService;
import com.company.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/GetDoctorList")
    public Response<List<RespDoctor>> getDoctorList() {
        return doctorService.getdoctorList();
    }

    @GetMapping("/GetDoctorListByPositionId/{positionId}")
    public Response<List<RespDoctor>> getDoctorListByPositionId(@PathVariable Long positionId) {
        return doctorService.getDoctorListByPositionId(positionId);
    }

    @PostMapping("/AddDoctor")
    public Response getDoctorListByPositionId(@RequestBody ReqDoctor reqDoctor) {
        return doctorService.addDoctor(reqDoctor);
    }
    @PostMapping("/UpdateDoctor")
    public Response updateDoctor(@RequestBody ReqDoctor reqDoctor) {
        return doctorService.updateDoctor(reqDoctor);
    }
    @GetMapping("/DeleteDoctor/{doctorId}")
    public Response deleteDoctor(@PathVariable Long doctorId) {
        return doctorService.deleteDoctor(doctorId);
    }


}
