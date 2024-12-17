package com.reggie.config;

import com.reggie.Interceptor.JwtTokenAdminInterceptor;
import lombok.extern.slf4j.Slf4j;
import com.reggie.json.JacksonObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration //配置类
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry){
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenAdminInterceptor) //那个拦截器
                .addPathPatterns("/admin/**") // 拦截器拦截的资源
                .excludePathPatterns("/admin/employee/login", "/admin/employee/logout"); //那些路径不拦截
    }
    /*
    Spring MVC 中自定义消息转换器，并扩展现有的消息转换器列表。自定义的消息转换器主要是用来将 Java 对象转换为 JSON 格式，或者反之。
     */
    /**
     * 扩展spring MVC的消息转换器
     *
     * @param converters 消息转换器
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("开启扩展消息转换器...");
        //创建消息转换器对象
        /*
        Spring 提供的消息转换器，基于 Jackson 进行 JSON 的序列化和反序列化。
        它用于将 Java 对象转换成 JSON 格式，或者将 JSON 格式数据转换成 Java 对象。
         */
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置消息转换器，将Java转化成JSON
        /*
        setObjectMapper 方法：用于设置自定义的 ObjectMapper。
        JacksonObjectMapper 是你自定义的 Jackson 配置类，继承自 Jackson 提供的 ObjectMapper。
        在 JacksonObjectMapper 中，你可以配置 JSON 序列化、反序列化规则，比如日期格式、字段过滤规则、属性命名策略等。
         */
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将自己创建的消息转化器添加到容器中
        /*
        add(0, messageConverter)：将自定义的消息转换器添加到 converters 列表的第一个位置。
        为什么放在第一位？
        Spring MVC 在执行消息转换时，会从列表的头部开始查找合适的转换器。如果将自定义的消息转换器放在最前面，就可以优先使用它来进行消息转换。
         */
        converters.add(0, messageConverter);
    }


}
