package com.company.service.impl;

import com.company.dto.request.ReqDoctor;
import com.company.dto.request.ReqPatient;
import com.company.dto.response.RespDoctor;
import com.company.dto.response.RespPatient;
import com.company.dto.response.RespStatus;
import com.company.dto.response.Response;
import com.company.entity.Doctor;
import com.company.entity.Patient;
import com.company.entity.Position;
import com.company.enums.EnumAvailableStatus;
import com.company.exception.ExceptionConstants;
import com.company.exception.HospitalException;
import com.company.repository.DoctorRepository;
import com.company.repository.PatientRepository;
import com.company.repository.PositionRepository;
import com.company.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final PositionRepository positionRepository;

    @Override
    public Response<List<RespDoctor>> getdoctorList() {
        Response<List<RespDoctor>> response = new Response<>();
        try {
            List<Doctor> doctorList = doctorRepository.findAllByActive(EnumAvailableStatus.ACTIVE.value);
            if (doctorList.isEmpty()) {
                throw new HospitalException(ExceptionConstants.DOCTOR_NOT_FOUND, "Doctor not found");
            }
            List<RespDoctor> respDoctorList = doctorList.stream().map(this::mapping).collect(Collectors.toList());
            response.setT(respDoctorList);
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
    public Response<List<RespDoctor>> getDoctorListByPositionId(Long positionId) {
        Response<List<RespDoctor>> response = new Response<>();
        try {
            if (positionId == null) {
                throw new HospitalException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Position position = positionRepository.findPositionByIdAndActive(positionId, EnumAvailableStatus.ACTIVE.value);
            if (position == null) {
                throw new HospitalException(ExceptionConstants.POSITION_NOT_FOUND, "Position not found");
            }
            List<Doctor> doctorList = doctorRepository.findDoctorListByPositionAndActive(position, EnumAvailableStatus.ACTIVE.value);
            if (doctorList.isEmpty()) {
                throw new HospitalException(ExceptionConstants.DOCTOR_NOT_FOUND, "Doctor not found in this Position");
            }
            List<RespDoctor> respDoctorList = doctorList.stream().map(this::mapping).collect(Collectors.toList());
            response.setT(respDoctorList);
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

    public Response addDoctor(@RequestBody ReqDoctor reqDoctor) {
        Response response = new Response();
        try {
            String name = reqDoctor.getName();
            String surname = reqDoctor.getSurname();
            Long positionId = reqDoctor.getPositionId();
            if (name == null || surname == null || positionId == null) {
                throw new HospitalException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Position position = positionRepository.findPositionByIdAndActive(positionId, EnumAvailableStatus.ACTIVE.value);
            if (position == null) {
                throw new HospitalException(ExceptionConstants.POSITION_NOT_FOUND, "Position not found");
            }
            Doctor doctor = Doctor.builder()
                    .name(name)
                    .surname(surname)
                    .dob(reqDoctor.getDob())
                    .phone(reqDoctor.getPhone())
                    .position(position)
                    .build();
            doctorRepository.save(doctor);
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
    public Response updateDoctor(ReqDoctor reqDoctor) {
        Response response = new Response();
        try {
            Long doctorId = reqDoctor.getDoctorId();
            String name = reqDoctor.getName();
            String surname = reqDoctor.getSurname();
            Long positionId = reqDoctor.getPositionId();
            if (name == null || surname == null || positionId == null|| doctorId==null) {
                throw new HospitalException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Position position = positionRepository.findPositionByIdAndActive(positionId, EnumAvailableStatus.ACTIVE.value);
            if (position == null) {
                throw new HospitalException(ExceptionConstants.POSITION_NOT_FOUND, "Position not found");
            }
            Doctor doctor = doctorRepository.findDoctorByIdAndActive(doctorId, EnumAvailableStatus.ACTIVE.value);
            if (doctor == null) {
                throw new HospitalException(ExceptionConstants.DOCTOR_NOT_FOUND, "Doctor not found");
            }
            doctor.setName(name);
            doctor.setSurname(surname);
            doctor.setPhone(reqDoctor.getPhone());
            doctor.setPosition(position);
            doctorRepository.save(doctor);
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
    public Response deleteDoctor(Long doctorId) {
        Response response = new Response();
        try {
            if (doctorId == null) {
                throw new HospitalException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Doctor doctor = doctorRepository.findDoctorByIdAndActive(doctorId, EnumAvailableStatus.ACTIVE.value);
            if (doctor == null) {
                throw new HospitalException(ExceptionConstants.DOCTOR_NOT_FOUND, "Doctor not found");
            }
            doctor.setActive(EnumAvailableStatus.DEACTIVE.value);
            doctorRepository.save(doctor);
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


    public RespDoctor mapping(Doctor doctor) {
        Position position = Position.builder()
                .id(doctor.getPosition().getId())
                .name(doctor.getPosition().getName())
                .description(doctor.getPosition().getDescription())
                .build();
        RespDoctor respDoctor = RespDoctor.builder()
                .doctorId(doctor.getId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .position(position)
                .phone(doctor.getPhone())
                .build();

        return respDoctor;
    }
}
