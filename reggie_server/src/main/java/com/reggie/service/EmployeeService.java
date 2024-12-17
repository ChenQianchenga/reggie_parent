package com.reggie.service;

import com.reggie.annotation.AutoFill;
import com.reggie.constant.AutoFillConstant;
import com.reggie.dto.EmployeeDTO;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.dto.EmployeePageQueryDTO;
import com.reggie.entity.Employee;
import com.reggie.result.PageResult;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    //员工登录
    Employee login(EmployeeLoginDTO employeeLoginDTO);
    //新增员工
    @AutoFill(type = AutoFillConstant.INSERT)
    void save(EmployeeDTO employeeDTO);
    //分页查询员工
    PageResult PageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}
