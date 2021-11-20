package com.project.system.model;

import com.project.system.entity.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StaffDto {
    private Staff staff;
    private Integer countEmployees;
}
