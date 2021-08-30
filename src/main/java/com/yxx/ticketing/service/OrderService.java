package com.yxx.ticketing.service;

import com.yxx.ticketing.model.Order;

/**
 *
 *
 */
public interface OrderService {

    /**
     * 提交单程购票订单
     * @param flightId 航班id
     * @param level 舱位等级
     * @return
     */
    int addOrder(Order order, int flightId, String level, int userId);

    /**
     * 往返购票订单
     * @param order 订单信息
     * @param flightId 去程航班
     * @param level 去程舱位等级
     * @param returnId 返程航班id
     * @param returnLv 返程舱位等级
     * @param userId 用户id
     * @return
     */
    int addRoundOrder(Order order,int flightId, String level,int returnId, String returnLv, int userId );
        }
