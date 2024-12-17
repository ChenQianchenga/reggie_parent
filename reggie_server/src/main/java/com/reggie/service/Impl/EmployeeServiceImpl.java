package com.reggie.service.Impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.reggie.context.BaseContext;
import com.reggie.constant.MessageConstant;
import com.reggie.constant.PasswordConstant;
import com.reggie.constant.StatusConstant;
import com.reggie.dto.EmployeeDTO;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.dto.EmployeePageQueryDTO;
import com.reggie.entity.Employee;
import com.reggie.exception.AccountLockedException;
import com.reggie.exception.AccountNotFoundException;
import com.reggie.exception.PasswordErrorException;
import com.reggie.mapper.EmployeeMapper;
import com.reggie.result.PageResult;
import com.reggie.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    //员工登录
    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();
        //调用数据库层查询当前用户信息
        Employee employee = employeeMapper.getByUsername(username);
        //用户不存在
        if (employee == null){
            //抛出自定义的异常(账号不存在)
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //账号锁定
        if (employee.getStatus() == StatusConstant.DISABLE){
            //抛出账号锁定异常
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }
        //密码错误
        //对前端传递过来的明文密码进行md5加密处理
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!pwd.equals(employee.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        return employee;
    }
    //员工添加
    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        //对象的拷贝
        BeanUtils.copyProperties(employeeDTO,employee);
        //设置默认密码
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        //设置账号状态
        employee.setStatus(StatusConstant.ENABLE);
//        //获取ThreadLocal中的empId(员工id)
//        Long empId = BaseContext.getCurrentId();
//        //创建的用户
//        employee.setCreateUser(empId);
//        //修改的用户
//        employee.setUpdateUser(empId);
//        //设置员工创建时间
//        employee.setCreateTime(LocalDateTime.now());
//        //设置员工修改时间
//        employee.setUpdateTime(LocalDateTime.now());
        employeeMapper.save(employee);



    }
    //分页查询员工信息
    @Override
    public PageResult PageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        //分页查询插件自动生成分页功能
        PageHelper.startPage(employeePageQueryDTO.getPage(),employeePageQueryDTO.getPageSize());
        Page<Employee> page = employeeMapper.PageQuery(employeePageQueryDTO);
        //返回封装PageResult对象
        PageResult pageResult = new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }
}
