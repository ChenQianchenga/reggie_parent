package com.reggie.controller.admin;

import com.reggie.annotation.IgnoreToken;
import com.reggie.constant.JwtClaimsConstant;
import com.reggie.dto.EmployeeDTO;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.entity.Employee;
import com.reggie.properties.JwtProperties;
import com.reggie.result.R;
import com.reggie.service.Impl.EmployeeServiceImpl;
import com.reggie.utils.JwtUtil;
import com.reggie.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/admin/employee")
@Slf4j
//@Api 注解通常用于 Swagger 或 Springfox 文档生成工具中，用于描述 API 的基本信息。具体来说，@Api 注解可以应用于控制器类上，以提供关于该控制器的元数据信息
//@Api 注解:
//用途: 提供关于 API 的元数据信息，以便在生成的文档中显示。
//属性:
//tags: 用于指定 API 的标签，这些标签可以帮助组织和分类 API。
//description: 用于提供 API 的详细描述。
//value: 与 tags 属性功能相同，可以互换使用。
@Api(tags = "员工相关接口")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private JwtProperties jwtProperties; //jwt令牌相关配置类

    /**
     * 测试用于测试jwt校验
     * @return
     */
    @ApiOperation("Jwt测试接口")
    @IgnoreToken //自定义放行拦截注解
    @GetMapping("/testJwt")
    public R<String> testJwt(){
        return R.success("jwt test");
    }
    @PostMapping("login")
    @ApiOperation("员工登录接口")
    public R<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info("员工登录：用户名：{}，密码：{}",employeeLoginDTO.getUsername(),employeeLoginDTO.getPassword());
        //调用业务登陆返回对象
        Employee employeeLogin = employeeService.login(employeeLoginDTO);
        //设置jwt中有效载荷部分的数据，通常是用户的身份标识
        HashMap<String,Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employeeLogin.getId());

        //创建jwt令牌
        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(),jwtProperties.getAdminTtl(),claims);
        //封装响应对象
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employeeLogin.getId())
                .name(employeeLogin.getName())
                .userName(employeeLogin.getUsername())
                .token(token)
                .build();
        return R.success(employeeLoginVO);
    }

    /**
     * 员工退出
     *
     * @return 返回success
     */
    @PostMapping("/logout")
    @ApiOperation("员工退出接口")
    public R<String> logout() {
        log.info("员工退出");
        return R.success("退出登录");
    }

    @PostMapping
    @ApiOperation("员工注册")
    public R<String> add(@RequestBody EmployeeDTO employeeDTO){
        log.info("新增员工：{}",employeeDTO);
        employeeService.save(employeeDTO);
        return R.success();
    }
}
