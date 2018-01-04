package com.interview.codereview.web.controller.employee.form;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeAnnualForm {

    private BigDecimal totalCount;

    private BigDecimal usedCount;
}