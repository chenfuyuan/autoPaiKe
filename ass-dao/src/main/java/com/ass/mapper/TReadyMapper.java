package com.ass.mapper;

import com.ass.pojo.TReady;
import com.ass.pojo.TReadyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TReadyMapper {
    int countByExample(TReadyExample example);

    int deleteByExample(TReadyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TReady record);

    int insertSelective(TReady record);

    List<TReady> selectByExample(TReadyExample example);

    TReady selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TReady record, @Param("example") TReadyExample example);

    int updateByExample(@Param("record") TReady record, @Param("example") TReadyExample example);

    int updateByPrimaryKeySelective(TReady record);

    int updateByPrimaryKey(TReady record);
}