package com.yxx.ticketing.dao;

import com.yxx.ticketing.model.Flights;
import com.yxx.ticketing.model.FlightsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlightsMapper {
    long countByExample(FlightsExample example);

    int deleteByExample(FlightsExample example);

    int insert(Flights record);

    int insertSelective(Flights record);

    List<Flights> selectByExample(FlightsExample example);

    int updateByExampleSelective(@Param("record") Flights record, @Param("example") FlightsExample example);

    int updateByExample(@Param("record") Flights record, @Param("example") FlightsExample example);
}