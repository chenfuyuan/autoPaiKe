package com.ass.mapper;

import com.ass.pojo.TDepartment;
import com.ass.pojo.TDepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TDepartmentMapper {
    int countByExample(TDepartmentExample example);

    int deleteByExample(TDepartmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TDepartment record);

    int insertSelective(TDepartment record);

    List<TDepartment> selectByExample(TDepartmentExample example);

    TDepartment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TDepartment record, @Param("example") TDepartmentExample example);

    int updateByExample(@Param("record") TDepartment record, @Param("example") TDepartmentExample example);

    int updateByPrimaryKeySelective(TDepartment record);

    int updateByPrimaryKey(TDepartment record);
    
    List<Object> selectName(String name);
}