package com.yxx.ticketing.service;

import com.yxx.ticketing.model.Oppo;

import java.util.List;

/**
 *
 *
 */
public interface OppoService {

    /**
     * 查询用户下的所有乘机人
     * @param userId
     * @return
     */
    List<Oppo> findByUserId(int userId);

    /**
     * 新增一个乘机人
     * @param oppo
     * @return
     */
    int addOppo(Oppo oppo);
}
