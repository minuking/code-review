package com.interview.codereview.domain.employee;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity(name = "employee_annual")
public class EmployeeAnnual implements Serializable {

    private static final long serialVersionUID = 6867866833996521729L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "year")
    private String year;

    @Column(name = "total_count")
    private BigDecimal totalCount;

    @Column(name = "used_count")
    private BigDecimal usedCount;

}
