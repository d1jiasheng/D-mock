package com.sz.mockbean.mapper;

import com.sz.mockbean.po.MockBeanConfig;
import com.sz.mockbean.po.MockBeanConfigExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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
            " (app_name, bean_id, bean_name, class_name, method_name, method_parameter, is_delete, create_time, return_type)" +
            " values" +
            " <foreach item='r' collection='records' separator=','>" +
            " (#{r.appName}, #{r.beanId}, #{r.beanName}, #{r.className}, #{r.methodName}, #{r.methodParameter}, #{r.isDelete}, #{r.createTime}, #{r.returnType})" +
            " </foreach>" +
            " </script>"})
    int bulkInsert(@Param("records") List<MockBeanConfig> records);

    @Select({"<script>" +
            " select * from mockbean_config where app_name = #{appName} and is_delete = 0 and bean_id in (" +
            " <foreach item='r' collection='beanIds' separator=','>" +
            " #{r}" +
            " </foreach>" +
            " )" +
            " </script>"
    })
    List<MockBeanConfig> bulkSelectByAppNameAndBeanIds(@Param("appName") String appName,
                                                       @Param("beanIds") List<Long> beanIds);
}