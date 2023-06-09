package com.company.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespUser {
    private String username;
    private String fullname;
    @JsonProperty(value = "token")
    private RespToken respToken;
}
