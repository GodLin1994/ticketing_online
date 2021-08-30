package com.yxx.ticketing.service.impl;

import com.yxx.ticketing.dao.SaleFlightMapper;
import com.yxx.ticketing.model.SaleFlight;
import com.yxx.ticketing.service.SalesFlightsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SalesFlightsServiceImpl implements SalesFlightsService {
    private final SaleFlightMapper saleFlightMapper;

    public SalesFlightsServiceImpl(SaleFlightMapper saleFlightMapper) {
        this.saleFlightMapper = saleFlightMapper;
    }

    @Override
    public List<SaleFlight> findByOrdId(int ordId) {
        return saleFlightMapper.getFlights(ordId);
    }
}
