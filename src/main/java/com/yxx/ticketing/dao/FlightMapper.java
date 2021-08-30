package com.yxx.ticketing.dao;

import com.yxx.ticketing.model.Flight;
import org.apache.ibatis.annotations.Param;

/**
 *
 *
 */
public interface FlightMapper {
    public Flight getOneFlight(@Param("fid") Integer fid);
}
