package com.interview.codereview.component.service.impl;

import com.interview.codereview.component.repository.employee.EmployeeAnnualRepository;
import com.interview.codereview.component.repository.employee.EmployeeRepository;
import com.interview.codereview.component.service.EmployeeAnnualService;
import com.interview.codereview.domain.employee.Employee;
import com.interview.codereview.domain.employee.EmployeeAnnual;
import com.interview.codereview.web.controller.employee.dto.EmployeeWithEmployeeAnnualDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class EmployeeAnnualServiceImpl implements EmployeeAnnualService {

    @Autowired
    private EmployeeAnnualRepository employeeAnnualRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Page<EmployeeWithEmployeeAnnualDto> findBy(Long companyId, String year, Pageable pageable, String searchKeyword) {

        Page<Employee> employees = employeeRepository.findBy(companyId, searchKeyword, pageable);

        // 1.스트림 미 사용시 처리 방법.
//        List<EmployeeWithEmployeeAnnualDto> employeeWithEmployeeAnnualDtos = new ArrayList<>();

//        for (Employee employee: employees) {
//
//            EmployeeAnnual employeeAnnual = employeeAnnualRepository.findByEmployeeIdAndYear(employee.getId(), year);
//
//            if (employeeAnnual == null) {
//                employeeAnnual = new EmployeeAnnual();
//                employeeAnnual.setYear(year);
//                employeeAnnual.setEmployeeId(employee.getId());
//                employeeAnnual.setCompanyId(employee.getCompanyId());
//            }
//
//            EmployeeWithEmployeeAnnualDto employeeAnnualDto = new EmployeeWithEmployeeAnnualDto(employee, employeeAnnual);
//            employeeWithEmployeeAnnualDtos.add(employeeAnnualDto);
//        }

        // 2.스트림 사용하여 처리.
        List<EmployeeWithEmployeeAnnualDto> result = employees.getContent().stream().map((employee) -> {

            //문제의 소지가 있는 부분이라고 생각합니다. QueryDsl?
            EmployeeAnnual employeeAnnual = employeeAnnualRepository.findByEmployeeIdAndYear(employee.getId(), year);

            if (employeeAnnual == null) {
                employeeAnnual = new EmployeeAnnual();
                employeeAnnual.setYear(year);
                employeeAnnual.setEmployeeId(employee.getId());
                employeeAnnual.setCompanyId(employee.getCompanyId());
            }

            return new EmployeeWithEmployeeAnnualDto(employee, employeeAnnual);
        }).collect(Collectors.toList());

//        return new PageImpl<>(employeeWithEmployeeAnnualDtos, pageable, employeeWithEmployeeAnnualDtos.size());
        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public void updateEmployeeAnnual(String year, Long employeeId, BigDecimal totalCount, BigDecimal usedCount) {

        Employee employee = employeeRepository.findOne(employeeId);

        EmployeeAnnual employeeAnnual = employeeAnnualRepository.findByEmployeeIdAndYear(employeeId, year);

        if (employeeAnnual != null) {
            employeeAnnual.setTotalCount(totalCount);
            employeeAnnual.setUsedCount(usedCount);
            employeeAnnualRepository.save(employeeAnnual);
        }
        else {
            EmployeeAnnual createEmployeeAnnual = new EmployeeAnnual();
            createEmployeeAnnual.setCompanyId(employee.getCompanyId());
            createEmployeeAnnual.setEmployeeId(employee.getId());
            createEmployeeAnnual.setYear(year);
            createEmployeeAnnual.setTotalCount(totalCount);
            createEmployeeAnnual.setUsedCount(usedCount);
            employeeAnnualRepository.save(createEmployeeAnnual);
        }
    }
}