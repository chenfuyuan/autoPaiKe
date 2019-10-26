package com.ass.mapper;

import com.ass.pojo.TCourse;
import com.ass.pojo.TCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCourseMapper {
    int countByExample(TCourseExample example);

    int deleteByExample(TCourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCourse record);

    int insertSelective(TCourse record);

    List<TCourse> selectByExample(TCourseExample example);

    TCourse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCourse record, @Param("example") TCourseExample example);

    int updateByExample(@Param("record") TCourse record, @Param("example") TCourseExample example);

    int updateByPrimaryKeySelective(TCourse record);

    int updateByPrimaryKey(TCourse record);
    
    List<Object> selectName(String name);
    
    String selectNameById(Integer id);
}