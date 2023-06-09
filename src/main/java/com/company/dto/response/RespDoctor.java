package com.company.dto.response;

import com.company.entity.Position;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
@Data
@Builder
public class RespDoctor {
    private Long doctorId;
    private String name;
    private String surname;
    private Date dob;
    private Position position;
    private String phone;
}
