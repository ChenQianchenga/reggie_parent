package com.reggie.service.Impl;
import com.reggie.context.MessageConstant;
import com.reggie.context.StatusConstant;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.entity.Employee;
import com.reggie.exception.AccountLockedException;
import com.reggie.exception.AccountNotFoundException;
import com.reggie.exception.PasswordErrorException;
import com.reggie.mapper.EmployeeMapper;
import com.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

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
}
