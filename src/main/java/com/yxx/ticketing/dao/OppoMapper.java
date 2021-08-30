package com.yxx.ticketing.dao;

import com.yxx.ticketing.model.Oppo;
import com.yxx.ticketing.model.OppoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OppoMapper {
    long countByExample(OppoExample example);

    int deleteByExample(OppoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Oppo record);

    int insertSelective(Oppo record);

    List<Oppo> selectByExample(OppoExample example);

    Oppo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Oppo record, @Param("example") OppoExample example);

    int updateByExample(@Param("record") Oppo record, @Param("example") OppoExample example);

    int updateByPrimaryKeySelective(Oppo record);

    int updateByPrimaryKey(Oppo record);
}