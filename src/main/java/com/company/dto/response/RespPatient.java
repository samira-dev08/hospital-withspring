package com.company.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class RespPatient {
    private Long patientId;
    private String name;
    private String surname;
    private Date dob;
    private String phone;
    private String address;
}
