package com.junly.mybatis.mapper;

import com.junly.mybatis.beans.NameSing;
import com.junly.mybatis.beans.NameSingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NameSingMapper {
    long countByExample(NameSingExample example);

    int deleteByExample(NameSingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NameSing record);

    int insertSelective(NameSing record);

    List<NameSing> selectByExample(NameSingExample example);

    NameSing selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NameSing record, @Param("example") NameSingExample example);

    int updateByExample(@Param("record") NameSing record, @Param("example") NameSingExample example);

    int updateByPrimaryKeySelective(NameSing record);

    int updateByPrimaryKey(NameSing record);
}