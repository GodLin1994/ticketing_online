package com.yxx.ticketing.dao;

import com.yxx.ticketing.model.Fare;
import com.yxx.ticketing.model.FareExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FareMapper {
    long countByExample(FareExample example);


    List<Fare> selectByExample(FareExample example);



    /**
     * 查询舱位票价
     * @param flightId
     * @param seatLevel
     * @return
     */
    Double findPrice(@Param("flightId") int flightId, @Param("seatLevel") String seatLevel);
}