package com.company.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HospitalException extends RuntimeException {
    private Integer code;

    public HospitalException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
