package com.yxx.ticketing.service.impl;

import com.yxx.ticketing.service.FareService;
import com.yxx.ticketing.dao.AirfareMapper;
import com.yxx.ticketing.dao.FareMapper;
import com.yxx.ticketing.dao.FareNumMapper;
import com.yxx.ticketing.model.Airfare;
import com.yxx.ticketing.model.Fare;
import com.yxx.ticketing.model.FareExample;
import com.yxx.ticketing.model.FareNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 *
 */
@Service
@Transactional
public class FareServiceImpl implements FareService {
    private final FareMapper fareMapper;
    private final FareNumMapper fareNumMapper;
    private final AirfareMapper airfareMapper;

    @Autowired
    public FareServiceImpl(FareMapper fareMapper, FareNumMapper fareNumMapper, AirfareMapper airfareMapper) {
        this.fareMapper = fareMapper;
        this.fareNumMapper = fareNumMapper;
        this.airfareMapper = airfareMapper;
    }


    @Override
    public int updateAirFareById(Airfare airfare) {
        return airfareMapper.updateByPrimaryKeySelective(airfare);
    }

    @Override
    public List<Fare> list(FareExample example) {
        return fareMapper.selectByExample(example);
    }

    @Override
    public long count(FareExample example) {
        return fareMapper.countByExample(example);
    }

    @Override
    public FareNum findById(int flightId) {
        return fareNumMapper.selectByFlightId(flightId);
    }

    @Override
    public Double findPrice(int flightId, String seatLevel) {
        return fareMapper.findPrice(flightId,seatLevel);
    }


}
