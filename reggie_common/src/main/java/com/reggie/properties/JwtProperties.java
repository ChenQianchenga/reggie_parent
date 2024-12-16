package com.reggie.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Component //将其注册为 Spring 容器中的一个 Bean
@ConfigurationProperties(prefix = "reggie.jwt") //来绑定配置文件中的 reggie.jwt 属性
@Data //使用 Lombok 的 @Data 自动生成了 getter、setter、toString 等方法
@Slf4j
public class JwtProperties {

    /**
     * 管理端员工生成jwt令牌相关配置
     */
    @NotBlank(message = "管理端密钥不能为空")
    private String adminSecretKey;
    @Min(value = 1000, message = "管理端令牌有效期必须大于 1 秒")
    private long adminTtl;
    @NotBlank(message = "管理端令牌名称不能为空")
    private String adminTokenName;

    /**
     * 用户端微信用户生成jwt令牌相关配置
     */
    @NotBlank(message = "用户端密钥不能为空")
    private String userSecretKey;
    @Min(value = 1000, message = "用户端令牌有效期必须大于 1 秒")
    private long userTtl;
    @NotBlank(message = "用户端令牌名称不能为空")
    private String userTokenName;

    //在应用启动时，可以将加载的配置打印到日志中，方便确认配置是否正确,可通过 @PostConstruct 方法输出
    @PostConstruct
    public void logProperties() {
        log.info("JWT 配置：管理员密钥={}, 有效期={}, Token 名称={}",
                adminSecretKey, adminTtl, adminTokenName);
        log.info("JWT 配置：用户密钥={}, 有效期={}, Token 名称={}",
                userSecretKey, userTtl, userTokenName);
    }

}
