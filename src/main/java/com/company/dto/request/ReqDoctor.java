package com.company.dto.request;

import com.company.entity.Position;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
@Data
public class ReqDoctor {
    private Long doctorId;
    private String name;
    private String surname;
    private Date dob;
    private String phone;
    private Long positionId;
}
