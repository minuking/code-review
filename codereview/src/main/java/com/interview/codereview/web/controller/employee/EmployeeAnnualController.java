package com.interview.codereview.web.controller.employee;

import com.interview.codereview.component.service.EmployeeAnnualService;
import com.interview.codereview.web.controller.employee.dto.EmployeeWithEmployeeAnnualDto;
import com.interview.codereview.web.controller.employee.form.EmployeeAnnualForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 직원 연차 관리
 */
@RestController
@RequestMapping("/companies/{companyId}")
public class EmployeeAnnualController {

    @Autowired
    private EmployeeAnnualService employeeAnnualService;

    @Autowired
    private PagedResourcesAssembler<EmployeeWithEmployeeAnnualDto> pagedAssembler;

    /**
     * 연차 목록 조회
     * @param companyId
     * @param year
     * @param searchKeyword
     * @param pageable
     * @return
     */
    @GetMapping("/annual-vacations/{year}")
    public ResponseEntity readEmployeeAnnuals(@PathVariable("companyId") Long companyId,
                                              @PathVariable("year") String year,
                                              @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
                                              Pageable pageable) {

        Page<EmployeeWithEmployeeAnnualDto> employeeAnnuals = employeeAnnualService.findBy(companyId, year, pageable, searchKeyword);

        return ResponseEntity.ok(pagedAssembler.toResource(employeeAnnuals));
    }

    /**
     * 연차 정보 수정
     * @param companyId
     * @param year
     * @param employeeId
     * @param employeeAnnualForm
     * @return
     */
    @PutMapping("annual-vacations/{year}/employees/{employeeId}")
    public ResponseEntity updateEmployeeAnnual(@PathVariable("companyId") Long companyId,
                                               @PathVariable("year") String year,
                                               @PathVariable("employeeId") Long employeeId,
                                               @RequestBody EmployeeAnnualForm employeeAnnualForm
    ) {

        employeeAnnualService.updateEmployeeAnnual(year, employeeId, employeeAnnualForm.getTotalCount(), employeeAnnualForm.getUsedCount());

        return ResponseEntity.ok().build();
    }
}
