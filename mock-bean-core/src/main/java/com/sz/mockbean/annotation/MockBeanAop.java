package com.sz.mockbean.annotation;

import com.alibaba.fastjson.JSON;
import com.sz.mockbean.common.mockbean.MockBeanService;
import com.sz.mockbean.response.ServerResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author dijiasheng
 * @date 2023/4/21
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class MockBeanAop {

    private MockBeanService mockBeanService;

    @Pointcut("@annotation(com.sz.mockbean.annotation.MockBean)")
    public void MockBeanPointCut() {
    }

    @Around("MockBeanPointCut()")
    public Object execJAnnotation(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = method.getName();
        Class<?> methodClass = method.getReturnType();

        MockBean annotation = method.getAnnotation(MockBean.class);
        try {
            String mockResult = mockBeanService.pullMockBean(annotation.beanId(), null, className, methodName);
            if (mockResult == null) {
                return pjp.proceed();
            }
            ServerResponse<String> serverResponse = JSON.parseObject(mockResult, ServerResponse.class);
            log.info("[mockBean 切面服务] serverResponse.data:{}", JSON.toJSONString(serverResponse.getData()));
            Object o = JSON.parseObject(serverResponse.getData(), methodClass);
            log.info("[mockBean 切面服务] object:{}", JSON.toJSONString(o.getClass()));
            return o;
        } catch (Exception e) {
            log.error("[mockBean 切面服务] 出错", e);
            return pjp.proceed();
        }

    }
}
