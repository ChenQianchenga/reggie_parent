package com.reggie.controller.admin;

import com.reggie.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
