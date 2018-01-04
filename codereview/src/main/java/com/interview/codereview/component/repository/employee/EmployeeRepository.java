package com.interview.codereview.component.repository.employee;

import com.interview.codereview.domain.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * 회사의 직원 목록 검색
     *
     * @param companyId    회사 ID
     * @param keyword      검색어
     * @param pageable
     * @return
     */
    Page<Employee> findBy(Long companyId, String keyword, Pageable pageable);

}
