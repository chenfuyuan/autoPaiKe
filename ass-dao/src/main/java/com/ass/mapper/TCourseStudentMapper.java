package com.ass.mapper;

import com.ass.pojo.TCourseStudent;
import com.ass.pojo.TCourseStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCourseStudentMapper {
    int countByExample(TCourseStudentExample example);

    int deleteByExample(TCourseStudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCourseStudent record);

    int insertSelective(TCourseStudent record);

    List<TCourseStudent> selectByExample(TCourseStudentExample example);

    TCourseStudent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCourseStudent record, @Param("example") TCourseStudentExample example);

    int updateByExample(@Param("record") TCourseStudent record, @Param("example") TCourseStudentExample example);

    int updateByPrimaryKeySelective(TCourseStudent record);

    int updateByPrimaryKey(TCourseStudent record);
}