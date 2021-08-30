package com.yxx.ticketing.service;

import com.yxx.ticketing.model.Airfare;
import com.yxx.ticketing.model.Fare;
import com.yxx.ticketing.model.FareExample;
import com.yxx.ticketing.model.FareNum;

import java.util.List;

/**
 *
 *
 */
public interface FareService {

    /**
     * 根据主键更新机票信息
     * @param airfare
     * @return
     */
    int updateAirFareById(Airfare airfare);

    /**
     * 查询符合条件的机票
     * @return
     */
    List<Fare> list(FareExample example);


    /**
     * 按条件查询机票数量
     * @param example
     * @return
     */
    long count(FareExample example);

    /**
     * 根据航班id查询航班各舱位的价格和剩余票数
     * @param flightId
     * @return
     */
    FareNum findById(int flightId);

    /**
     * 查询舱位票价
     * @param flightId
     * @param seatLevel
     * @return
     */
    Double findPrice(int flightId,String seatLevel);
}
