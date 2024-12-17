package com.reggie.service;

import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    //员工登录
    Employee login(EmployeeLoginDTO employeeLoginDTO);
}
