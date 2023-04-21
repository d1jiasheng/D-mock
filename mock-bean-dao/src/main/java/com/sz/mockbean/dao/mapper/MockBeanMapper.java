package com.sz.mockbean.dao.mapper;

import com.sz.mockbean.dao.po.MockBean;
import com.sz.mockbean.dao.po.MockBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MockBeanMapper {
    long countByExample(MockBeanExample example);

    int deleteByExample(MockBeanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MockBean record);

    int insertSelective(MockBean record);

    List<MockBean> selectByExample(MockBeanExample example);

    MockBean selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MockBean record, @Param("example") MockBeanExample example);

    int updateByExample(@Param("record") MockBean record, @Param("example") MockBeanExample example);

    int updateByPrimaryKeySelective(MockBean record);

    int updateByPrimaryKey(MockBean record);

    List<MockBean> selectColumnByExample(@Param("columns") List<String> columns, @Param("example") MockBeanExample example);
}