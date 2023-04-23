package com.sz.mockbean.mapper;

import com.sz.mockbean.po.MockBeanConfig;
import com.sz.mockbean.po.MockBeanConfigExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MockBeanConfigMapper {
    long countByExample(MockBeanConfigExample example);

    int deleteByExample(MockBeanConfigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MockBeanConfig record);

    int insertSelective(MockBeanConfig record);

    List<MockBeanConfig> selectByExample(MockBeanConfigExample example);

    MockBeanConfig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MockBeanConfig record, @Param("example") MockBeanConfigExample example);

    int updateByExample(@Param("record") MockBeanConfig record, @Param("example") MockBeanConfigExample example);

    int updateByPrimaryKeySelective(MockBeanConfig record);

    int updateByPrimaryKey(MockBeanConfig record);

    List<MockBeanConfig> selectColumnByExample(@Param("columns") List<String> columns, @Param("example") MockBeanConfigExample example);

    @Update({"<script>" +
            " insert into mockbean_config" +
            " (app_name, bean_id, bean_name, class_name, method_name, method_parameter, is_delete, create_time)" +
            " values" +
            " <foreach item='r' collection='records' separator=','>" +
            " (#{r.appName}, #{r.beanId}, #{r.beanName}, #{r.className}, #{r.methodName}, #{r.methodParameter}, #{r.isDelete}, #{r.createTime})" +
            " </foreach>" +
            " </script>"})
    int bulkInsert(@Param("records") List<MockBeanConfig> records);
}