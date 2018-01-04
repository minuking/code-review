package com.interview.codereview.component.service;

import com.interview.codereview.web.controller.employee.dto.EmployeeWithEmployeeAnnualDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface EmployeeAnnualService {

    /**
     * 연차 목록 조회
     * @param companyId
     * @param year
     * @param pageable
     * @param searchKeyword
     * @return
     */
    Page<EmployeeWithEmployeeAnnualDto> findBy(Long companyId, String year, Pageable pageable, String searchKeyword);

    /**
     * 연차 정보 수정
     * @param year
     * @param employeeId
     * @param totalCount
     * @param usedCount
     */
    void updateEmployeeAnnual(String year, Long employeeId, BigDecimal totalCount, BigDecimal usedCount);


}
