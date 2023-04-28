package com.sz.mockbean.common.mockbean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author dijiasheng
 * @date 2023/4/21
 */
public class MockBeanConfig {

    private String address;

    private String host;

    private String appName;

    private Boolean serviceOpen;

    public MockBeanConfig(String fileName) throws FileNotFoundException {
        Properties properties = new Properties();
        InputStream resourceStream = this.getClass().getResourceAsStream(fileName);
        try {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.serviceOpen = Boolean.parseBoolean(properties.getProperty("mockbean.open", "false"));
        this.address = properties.getProperty("mockbean.address");
        this.host = properties.getProperty("mockbean.host");
        this.appName = properties.getProperty("mockbean.appName");
    }

    public MockBeanService init() {
        return new MockBeanService(this);
    }

    public String getAddress() {
        return address;
    }

    public String getHost() {
        return host;
    }

    public String getAppName() {
        return appName;
    }

    public Boolean getServiceOpen() {
        return serviceOpen;
    }
}
