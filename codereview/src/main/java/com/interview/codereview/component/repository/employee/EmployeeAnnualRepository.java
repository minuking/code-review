package com.interview.codereview.component.repository.employee;

import com.interview.codereview.domain.employee.EmployeeAnnual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeAnnualRepository extends JpaRepository<EmployeeAnnual, Long> {

    EmployeeAnnual findByEmployeeIdAndYear(@Param("employeeId") Long employeeId, @Param("year") String year);
}
