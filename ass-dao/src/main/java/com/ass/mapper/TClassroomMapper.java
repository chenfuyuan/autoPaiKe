package com.ass.mapper;

import com.ass.pojo.TClassroom;
import com.ass.pojo.TClassroomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TClassroomMapper {
    int countByExample(TClassroomExample example);

    int deleteByExample(TClassroomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TClassroom record);

    int insertSelective(TClassroom record);

    List<TClassroom> selectByExample(TClassroomExample example);

    TClassroom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TClassroom record, @Param("example") TClassroomExample example);

    int updateByExample(@Param("record") TClassroom record, @Param("example") TClassroomExample example);

    int updateByPrimaryKeySelective(TClassroom record);

    int updateByPrimaryKey(TClassroom record);
    
    List<Object> selectName(String name);
    
    String selectNameById(Integer id);
    
    Integer selectIDByName(String name);
}