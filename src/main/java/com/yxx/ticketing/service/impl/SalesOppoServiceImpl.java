package com.yxx.ticketing.service.impl;

import com.yxx.ticketing.dao.SaleOppoMapper;
import com.yxx.ticketing.model.SaleFlight;
import com.yxx.ticketing.model.SaleOppo;
import com.yxx.ticketing.service.SalesOppoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SalesOppoServiceImpl implements SalesOppoService {
    private final SaleOppoMapper saleOppoMapper;

    public SalesOppoServiceImpl(SaleOppoMapper saleOppoMapper) {
        this.saleOppoMapper = saleOppoMapper;
    }

    @Override
    public List<SaleOppo> getAllOpposByOidAndFid(int ordId, int fid) {
        Map<String,Integer> map=new HashMap<>();
        map.put("oid",ordId);
        map.put("fid",fid);

        return saleOppoMapper.getAllOpposByOidAndFid(map);
    }
}
