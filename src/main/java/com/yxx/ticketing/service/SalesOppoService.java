package com.yxx.ticketing.service;

import com.yxx.ticketing.model.SaleFlight;
import com.yxx.ticketing.model.SaleOppo;

import java.util.List;

public interface SalesOppoService {
    /**
     * 查符合条件的订单用户信息
     * @param
     * @return
     */
    List<SaleOppo> getAllOpposByOidAndFid(int ordId, int fid);
}
