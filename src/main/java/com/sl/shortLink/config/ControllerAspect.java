package com.sl.shortLink.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  controller全局日志打印
 * @author wangzhiyong
 * @date 2021/12/18 下午4:33
 * @param
 * @return null
 */
@Slf4j
@Component
@Aspect
public class ControllerAspect {

    @Pointcut("execution(* com.sl.shortLink.controller..*.*(..))")
    private void pointCutMethodController(){

    }

    @Before("pointCutMethodController()")
    public void doBeforeAdvice(JoinPoint joinPoint) throws Throwable{
        //获取目标类名
        String className = joinPoint.getSignature().getDeclaringTypeName();
        //获取目标方法名
        String methodName = joinPoint.getSignature().getName();
        //获取请求参数,过滤不打印的请求参数
        List<Object> params = shieldTokenPrint(joinPoint.getArgs());
        log.info("==========================aop-log-controller:{},method:{},reqParams:{},",className,methodName,JSONObject.toJSONString(params));
    }

    /**
     *  过滤不需要打印的请求参数
     * @author wangzhiyong
     * @date 2021/12/27 上午10:37
     * @param args
     * @return java.util.List<java.lang.Object>
     */
    private List<Object> shieldTokenPrint(Object[] args) {
        List<Object> objects = Arrays.asList(args);
        if (CollectionUtils.isEmpty(objects)) {
            return new ArrayList<>();
        }
        List<Object> collect = objects.stream()
                .filter(e -> !(e instanceof HttpServletRequest))
                .filter(e -> !(e instanceof HttpServletResponse))
                .collect(Collectors.toList());
        return collect;
    }
}
