package com.interview.codereview.domain.employee;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class Employee implements Serializable {

    private static final long serialVersionUID = -2284681878740221086L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "usr_id")
    private Long userId;

    @Column(name = "company_id")
    private Long companyId;

    private String department;

    private String name;

    private String email;

    private String phone;

    /**
     * 직무
     */
    private String duty;

    /**
     * 입사일
     */
    @Column(name = "date_joined")
    private LocalDate dateJoined;

    /**
     * 계약 시작일
     */
    @Column(name = "contract_start_date")
    private LocalDate contractStartDate;

    /**
     * 계약 종료일
     */
    @Column(name = "contract_end_date")
    private LocalDate contractEndDate;

    /**
     * 회사 관리자 여부
     */
    private boolean manager;

    private boolean deleted;

}
