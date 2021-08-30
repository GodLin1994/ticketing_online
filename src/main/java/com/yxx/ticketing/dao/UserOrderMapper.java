package com.yxx.ticketing.dao;

import com.yxx.ticketing.model.User;
import com.yxx.ticketing.model.UserOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *
 */
public interface UserOrderMapper {

    public List<UserOrder> getALLOrderByUserId(@Param("user") User user, @Param("order") UserOrder order);

    public UserOrder getUserByOid(@Param("oid") Integer oid);

    public void updateOrderState(@Param("oid") Integer oid, @Param("state") String state);

    public UserOrder getOneOrderByOid(@Param("oid") Integer oid);

    public int pay_updateOrderState(@Param("num") String out_trade_no);

    public int getCountFlightsByOid(@Param("oid") Integer oid);
}
