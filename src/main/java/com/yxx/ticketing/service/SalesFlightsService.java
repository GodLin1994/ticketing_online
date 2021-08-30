package com.yxx.ticketing.service;

import com.yxx.ticketing.model.Flights;
import com.yxx.ticketing.model.FlightsExample;
import com.yxx.ticketing.model.SaleFlight;

import java.util.List;

public interface SalesFlightsService {
    /**
     * 查符合条件的订单航班信息
     * @param
     * @return
     */
    List<SaleFlight> findByOrdId(int ordId);

}
