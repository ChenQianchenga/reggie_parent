package com.reggie.mapper;

import com.github.pagehelper.Page;
import com.reggie.annotation.AutoFill;
import com.reggie.constant.AutoFillConstant;
import com.reggie.dto.EmployeePageQueryDTO;
import com.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {
    /**
     * 根据用户名查询员工
     * @param username 用户名
     * @return 员工对象
     */
    Employee getByUsername(String username);

    /**
     * 新增员工
     * @param employee 员工对象
     */
    @AutoFill(type = AutoFillConstant.INSERT)
    void save(Employee employee);
    //分页查询功能
    Page<Employee> PageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}
