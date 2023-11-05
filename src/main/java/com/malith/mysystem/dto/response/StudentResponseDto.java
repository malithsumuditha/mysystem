package com.malith.mysystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto {

    private long studentId;
    private String name;
    private String address;
    private int age;
}
