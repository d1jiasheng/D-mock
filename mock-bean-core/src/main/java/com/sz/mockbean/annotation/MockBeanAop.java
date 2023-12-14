package com.sz.mockbean.annotation;

import com.alibaba.fastjson.JSON;
import com.sz.mockbean.common.mockbean.MockBeanConfig;
import com.sz.mockbean.model.MockBeanClientHolder;
import com.sz.mockbean.model.MockBeanModel;
import com.sz.mockbean.request.MockBeanProtocal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    private static long COUNT = 0;

    private MockBeanClientHolder mockBeanClientHolder;

    private MockBeanConfig mockBeanConfig;

    @Pointcut("@annotation(com.sz.mockbean.annotation.MockBean)")
    public void MockBeanPointCut() {
    }

    @Around("MockBeanPointCut()")
    public Object execJAnnotation(ProceedingJoinPoint pjp) throws Throwable {
        if (!mockBeanConfig.getServiceOpen()) {
            return pjp.proceed();
        }
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = method.getName();
        Class<?> methodClass = method.getReturnType();

        MockBean annotation = method.getAnnotation(MockBean.class);
        try {
            String mockResult = mockBeanClientHolder.write(genProtocal(annotation.beanId(), null, className, methodName));
            MockBeanProtocal result = null;
            if (StringUtils.isEmpty(mockResult) || (result = JSON.parseObject(mockResult, MockBeanProtocal.class)) == null) {
                return pjp.proceed();
            }
            log.info("[mockBean 切面服务] serverResponse.data:{}", JSON.toJSONString(result.getMsg()));
            Object o = genMockResult(result.getMsg(), methodClass);
            log.info("[mockBean 切面服务] object:{}", JSON.toJSONString(o.getClass()));
            return o;
        } catch (Exception e) {
            log.error("[mockBean 切面服务] 出错", e);
            return pjp.proceed();
        }

    }

    private MockBeanProtocal genProtocal(Long beanId, String beanName, String className, String methodName) {
        MockBeanModel model = genBeanModel(beanId, beanName, className, methodName);
        MockBeanProtocal protocal = new MockBeanProtocal();
        protocal.setSeqId(getSeqId().toString());
        protocal.setAction("request");
        protocal.setMsg(JSON.toJSONString(model));
        return protocal;
    }

    private MockBeanModel genBeanModel(Long beanId, String beanName, String className, String methodName) {
        MockBeanModel mockBeanModel = new MockBeanModel();
        mockBeanModel.setBeanId(beanId);
        mockBeanModel.setAppName(mockBeanConfig.getAppName());
        mockBeanModel.setBeanName(beanName);
        mockBeanModel.setClassName(className);
        mockBeanModel.setMethodName(methodName);
        return mockBeanModel;
    }

    private synchronized Long getSeqId() {
        return COUNT += 1;
    }

    private Object genMockResult(String msg, Class<?> returnType) {
        if (returnType.isAssignableFrom(String.class)) {
            return msg;
        }
        if (returnType.isAssignableFrom(Integer.class)) {
            return Integer.parseInt(msg);
        }
        return JSON.parseObject(msg, returnType);
    }
}
