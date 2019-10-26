package com.ass.mapper;

import com.ass.pojo.TCourseTeacher;
import com.ass.pojo.TCourseTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCourseTeacherMapper {
    int countByExample(TCourseTeacherExample example);

    int deleteByExample(TCourseTeacherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCourseTeacher record);

    int insertSelective(TCourseTeacher record);

    List<TCourseTeacher> selectByExample(TCourseTeacherExample example);

    TCourseTeacher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCourseTeacher record, @Param("example") TCourseTeacherExample example);

    int updateByExample(@Param("record") TCourseTeacher record, @Param("example") TCourseTeacherExample example);

    int updateByPrimaryKeySelective(TCourseTeacher record);

    int updateByPrimaryKey(TCourseTeacher record);
}