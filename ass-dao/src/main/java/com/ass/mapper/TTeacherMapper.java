package com.ass.mapper;

import com.ass.pojo.TTeacher;
import com.ass.pojo.TTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTeacherMapper {
    int countByExample(TTeacherExample example);

    int deleteByExample(TTeacherExample example);

    int deleteByPrimaryKey(String id);

    int insert(TTeacher record);

    int insertSelective(TTeacher record);

    List<TTeacher> selectByExample(TTeacherExample example);

    TTeacher selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TTeacher record, @Param("example") TTeacherExample example);

    int updateByExample(@Param("record") TTeacher record, @Param("example") TTeacherExample example);

    int updateByPrimaryKeySelective(TTeacher record);

    int updateByPrimaryKey(TTeacher record);

	int selectIdCount(String string);
	
	List<Object> selectName(String name);
	
	String selectNameById(String id);
}