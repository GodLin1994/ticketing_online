package com.yxx.ticketing.dao;

import com.yxx.ticketing.model.FareNum;

/**
 *
 *
 */
public interface FareNumMapper {
    //根据航班id查询该航班票价和剩余票数信息
    FareNum selectByFlightId(Integer flightId);

}
