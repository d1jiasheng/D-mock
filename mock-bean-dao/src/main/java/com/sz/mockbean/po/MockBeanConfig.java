package com.sz.mockbean.po;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MockBeanConfig implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String appName;

    /**
     * 
     */
    private Long beanId;

    /**
     * 
     */
    private String beanName;

    /**
     * 
     */
    private String className;

    /**
     * 
     */
    private String methodName;

    /**
     * 
     */
    private Integer returnType;

    /**
     * 
     */
    private String methodParameter;

    /**
     * 
     */
    private Integer isDelete;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";

    public static final String APP_NAME = "appName";

    public static final String BEAN_ID = "beanId";

    public static final String BEAN_NAME = "beanName";

    public static final String CLASS_NAME = "className";

    public static final String METHOD_NAME = "methodName";

    public static final String RETURN_TYPE = "returnType";

    public static final String METHOD_PARAMETER = "methodParameter";

    public static final String IS_DELETE = "isDelete";

    public static final String CREATE_TIME = "createTime";

    public static final String UPDATE_TIME = "updateTime";

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return app_name 
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 
     * @param appName 
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * 
     * @return bean_id 
     */
    public Long getBeanId() {
        return beanId;
    }

    /**
     * 
     * @param beanId 
     */
    public void setBeanId(Long beanId) {
        this.beanId = beanId;
    }

    /**
     * 
     * @return bean_name 
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * 
     * @param beanName 
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    /**
     * 
     * @return class_name 
     */
    public String getClassName() {
        return className;
    }

    /**
     * 
     * @param className 
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 
     * @return method_name 
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 
     * @param methodName 
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * 
     * @return return_type 
     */
    public Integer getReturnType() {
        return returnType;
    }

    /**
     * 
     * @param returnType 
     */
    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    /**
     * 
     * @return method_parameter 
     */
    public String getMethodParameter() {
        return methodParameter;
    }

    /**
     * 
     * @param methodParameter 
     */
    public void setMethodParameter(String methodParameter) {
        this.methodParameter = methodParameter;
    }

    /**
     * 
     * @return is_delete 
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 
     * @param isDelete 
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 
     * @return create_time 
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * @return update_time 
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * @param updateTime 
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appName=").append(appName);
        sb.append(", beanId=").append(beanId);
        sb.append(", beanName=").append(beanName);
        sb.append(", className=").append(className);
        sb.append(", methodName=").append(methodName);
        sb.append(", returnType=").append(returnType);
        sb.append(", methodParameter=").append(methodParameter);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}