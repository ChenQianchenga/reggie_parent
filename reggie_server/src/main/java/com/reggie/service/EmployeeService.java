package com.reggie.service;

import com.reggie.annotation.AutoFill;
import com.reggie.constant.AutoFillConstant;
import com.reggie.dto.EmployeeDTO;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.dto.EmployeePageQueryDTO;
import com.reggie.dto.PasswordEditDTO;
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
    //启用,禁用员工状态
    void startOrStop(Integer status, Long id);
    //根据id查询员工
    Employee getById(long id);
    //修改员工信息
    void update(EmployeeDTO employeeDTO);
    //修改员工密码
    void editPassword(PasswordEditDTO passwordEditDTO);
}
