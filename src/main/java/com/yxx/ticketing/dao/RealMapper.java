package com.yxx.ticketing.dao;

import com.yxx.ticketing.model.Real;
import com.yxx.ticketing.model.RealExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RealMapper {
    long countByExample(RealExample example);

    int deleteByExample(RealExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Real record);

    int insertSelective(Real record);

    List<Real> selectByExample(RealExample example);

    Real selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Real record, @Param("example") RealExample example);

    int updateByExample(@Param("record") Real record, @Param("example") RealExample example);

    int updateByPrimaryKeySelective(Real record);

    int updateByPrimaryKey(Real record);
}