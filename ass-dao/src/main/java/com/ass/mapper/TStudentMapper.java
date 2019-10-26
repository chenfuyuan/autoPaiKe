package com.ass.mapper;

import com.ass.pojo.TStudent;
import com.ass.pojo.TStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TStudentMapper {
    int countByExample(TStudentExample example);

    int deleteByExample(TStudentExample example);

    int deleteByPrimaryKey(String id);

    int insert(TStudent record);

    int insertSelective(TStudent record);

    List<TStudent> selectByExample(TStudentExample example);

    TStudent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TStudent record, @Param("example") TStudentExample example);

    int updateByExample(@Param("record") TStudent record, @Param("example") TStudentExample example);

    int updateByPrimaryKeySelective(TStudent record);

    int updateByPrimaryKey(TStudent record);
    
    int selectIdCount(String string);
    
    List<Object> selectName(String name);
}