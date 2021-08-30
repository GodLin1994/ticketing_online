package com.yxx.ticketing.service;

import com.yxx.ticketing.model.Flights;
import com.yxx.ticketing.model.FlightsExample;

import java.util.List;

/**
 *
 *
 */
public interface FlightService {

    /**
     * 查符合条件的航班
     * @param example 查询条件
     * @return
     */
    List<Flights> find(FlightsExample example);

    /**
     * 根据航班id查询航班信息
     * @param flightId 航班id
     * @return
     */
    Flights findById(int flightId);
}
