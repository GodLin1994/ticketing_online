package com.yxx.ticketing.dao;

import com.yxx.ticketing.model.SaleOppo;

import java.util.List;
import java.util.Map;

/**
 *
 *
 */
public interface SaleOppoMapper {
    public List<SaleOppo> getAllOpposByOidAndFid(Map<String, Integer> map);
}
