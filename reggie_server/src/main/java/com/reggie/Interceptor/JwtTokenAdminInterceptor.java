package com.reggie.Interceptor;

import com.reggie.constant.JwtClaimsConstant;
import com.reggie.context.BaseContext;
import com.reggie.properties.JwtProperties;
import com.reggie.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一进行jwt令牌的校验
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * jwt令牌的校验
     * //目标资源方法执行前执行。 返回true：放行 返回false：不放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截器拦截到请求：{}", request.getRequestURI());

//        if (!(handler instanceof HandlerMethod)) {
//            //如果不是映射到controller某个方法的请求，则直接放行，例如请求的是/doc.html
//            return true;
//        }
        return true;

//        //获取请求的方法
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        //判断当前被拦截的Controller方法上是否加入了IgonreToken注解
//        boolean methodAnnotation = handlerMethod.hasMethodAnnotation(IgnoreToken.class);
//        if (methodAnnotation) {
//            return true;
//        }
//
//
//        //从请求头获取jwt令牌
//        String token = request.getHeader(jwtProperties.getAdminTokenName());
//
//        try {
//            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
//
//            //获取员工id
//            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
//            //将员工id存入ThreadLocal中
//            BaseContext.setCurrentId(empId);
//
//        } catch (Exception e) {
//            log.error("jwt令牌解析失败");
//            //401代表用户没有访问权限，需要进行身份认证
//            response.setStatus(401);
//            return false;
//        }
//        return true;
    }

    //目标资源方法执行后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle ... ");
    }

    //视图渲染完毕后执行，最后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion .... ");
    }

}
