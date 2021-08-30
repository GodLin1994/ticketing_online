package com.yxx.ticketing.dao;

import com.yxx.ticketing.model.Flights;
import com.yxx.ticketing.model.SaleFlight;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *
 */
public interface SaleFlightMapper {

    public List<SaleFlight> getFlights(@Param("id") Integer id);
}
