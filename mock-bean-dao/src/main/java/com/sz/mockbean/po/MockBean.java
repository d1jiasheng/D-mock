package com.sz.mockbean.po;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MockBean implements Serializable {
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
     * 是否启用mock
     */
    private Integer useMock;

    /**
     * 
     */
    private String mockValue;

    /**
     * 
     */
    private String latestValue;

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

    public static final String USE_MOCK = "useMock";

    public static final String MOCK_VALUE = "mockValue";

    public static final String LATEST_VALUE = "latestValue";

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
     * 是否启用mock
     * @return use_mock 是否启用mock
     */
    public Integer getUseMock() {
        return useMock;
    }

    /**
     * 是否启用mock
     * @param useMock 是否启用mock
     */
    public void setUseMock(Integer useMock) {
        this.useMock = useMock;
    }

    /**
     * 
     * @return mock_value 
     */
    public String getMockValue() {
        return mockValue;
    }

    /**
     * 
     * @param mockValue 
     */
    public void setMockValue(String mockValue) {
        this.mockValue = mockValue;
    }

    /**
     * 
     * @return latest_value 
     */
    public String getLatestValue() {
        return latestValue;
    }

    /**
     * 
     * @param latestValue 
     */
    public void setLatestValue(String latestValue) {
        this.latestValue = latestValue;
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
        sb.append(", useMock=").append(useMock);
        sb.append(", mockValue=").append(mockValue);
        sb.append(", latestValue=").append(latestValue);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}