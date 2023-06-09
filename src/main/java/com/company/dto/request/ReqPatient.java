package com.company.dto.request;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReqPatient {

    private Long patientId;
    private String name;
    private String surname;
    private Date dob;
    private String phone;
    private String address;
    @JsonProperty(value = "token")
    private ReqToken reqToken;
}
