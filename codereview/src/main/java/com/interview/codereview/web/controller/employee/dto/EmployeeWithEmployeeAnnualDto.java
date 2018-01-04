package com.interview.codereview.web.controller.employee.dto;

import com.interview.codereview.domain.employee.Employee;
import com.interview.codereview.domain.employee.EmployeeAnnual;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeWithEmployeeAnnualDto implements Serializable {

    private static final long serialVersionUID = -1063799428816316304L;

    private Employee employee;

    private EmployeeAnnual employeeAnnual;

    public EmployeeWithEmployeeAnnualDto() {

    }

    public EmployeeWithEmployeeAnnualDto(Employee employee, EmployeeAnnual employeeAnnual) {
        this.employee = employee;
        this.employeeAnnual = employeeAnnual;
    }

}
