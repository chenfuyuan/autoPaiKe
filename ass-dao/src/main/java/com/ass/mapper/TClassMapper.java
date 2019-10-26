package com.ass.mapper;

import com.ass.pojo.TClass;
import com.ass.pojo.TClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TClassMapper {
    int countByExample(TClassExample example);

    int deleteByExample(TClassExample example);

    int deleteByPrimaryKey(String id);

    int insert(TClass record);

    int insertSelective(TClass record);

    List<TClass> selectByExample(TClassExample example);

    TClass selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TClass record, @Param("example") TClassExample example);

    int updateByExample(@Param("record") TClass record, @Param("example") TClassExample example);

    int updateByPrimaryKeySelective(TClass record);

    int updateByPrimaryKey(TClass record);
    
    int selectIdCount(String string);
    
    List<Object> selectName(String name);
    
    String selectNameById(String id);
}