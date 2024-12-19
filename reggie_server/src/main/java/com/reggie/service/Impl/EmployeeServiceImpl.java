package com.reggie.service.Impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import static com.reggie.constant.MessageConstant.*;
import com.reggie.constant.MessageConstant;
import com.reggie.constant.PasswordConstant;
import com.reggie.constant.StatusConstant;
import com.reggie.context.BaseContext;
import com.reggie.dto.EmployeeDTO;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.dto.EmployeePageQueryDTO;
import com.reggie.dto.PasswordEditDTO;
import com.reggie.entity.Employee;
import com.reggie.exception.AccountLockedException;
import com.reggie.exception.AccountNotFoundException;
import com.reggie.exception.BaseException;
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
            throw new AccountNotFoundException();
        }
        //账号锁定
        if (employee.getStatus() == StatusConstant.DISABLE){
            //抛出账号锁定异常
            throw new AccountLockedException();
        }
        //密码错误
        //对前端传递过来的明文密码进行md5加密处理
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!pwd.equals(employee.getPassword())){
            throw new PasswordErrorException();
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
    //账号的启用和禁用
    public void startOrStop(Integer status, Long id) {

        // 检查员工是否存在
        Employee existingEmployee = employeeMapper.getById(id);
        if (existingEmployee == null) {
            throw new BaseException("未找到对应的员工记录，ID: " + id);
        }
        Employee employee = new Employee();
        //设置员工状态
        employee.setStatus(status);
        //设置员工id
        employee.setId(id);
        try {
            int rowsUpdated = employeeMapper.updateStatusById(employee); // 更新状态
            if (rowsUpdated == 0) {
                throw new BaseException("更新失败，可能是记录不存在或状态未修改");
            }
        } catch (Exception e) {
            throw new BaseException("更新员工状态时发生异常");
        }
    }

    @Override
    public Employee getById(long id) {
        return employeeMapper.getById(id);
    }

    @Override
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        //对象拷贝
        BeanUtils.copyProperties(employeeDTO,employee);
        //更新数据库
        employeeMapper.update(employee);

    }

    @Override
    public void editPassword(PasswordEditDTO passwordEditDTO) {
        String oldPassword = passwordEditDTO.getOldPassword();
        String newPassword = passwordEditDTO.getNewPassword();
        //根据token获取当前用户id
        long empId = BaseContext.getCurrentId();

        //通过员工id查询当前对象
        Employee employee = employeeMapper.getById(empId);
        //判断对象是否为空
        if (employee == null){
            throw new PasswordErrorException();
        }
        //对旧密码明文进行md5
        oldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        //判断旧密码是否正确
        if (!employee.getPassword().equals(oldPassword)){
            throw new PasswordErrorException();
        }
        //对新密码明文进行MD5
        newPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        Employee emp = Employee.builder()
                .id(empId)
                .password(newPassword).build();
        employeeMapper.update(emp);


    }
}
