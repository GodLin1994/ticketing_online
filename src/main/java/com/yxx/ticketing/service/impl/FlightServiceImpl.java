package com.yxx.ticketing.service.impl;

import com.yxx.ticketing.dao.FlightsMapper;
import com.yxx.ticketing.model.Flights;
import com.yxx.ticketing.model.FlightsExample;
import com.yxx.ticketing.service.FlightService;
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
public class FlightServiceImpl implements FlightService {
    private final FlightsMapper flightsMapper;

    @Autowired
    public FlightServiceImpl(FlightsMapper flightsMapper) {
        this.flightsMapper = flightsMapper;
    }


    @Override
    public List<Flights> find(FlightsExample example) {
        return flightsMapper.selectByExample(example);
    }

    @Override
    public Flights findById(int flightId) {
        FlightsExample example=new FlightsExample();
        example.createCriteria().andFlightIdEqualTo(flightId);
        List<Flights> flights = flightsMapper.selectByExample(example);
        if (flights.size()>0){
            return flights.get(0);
        }
        return null;
    }
}
