package com.yxx.ticketing.service;

import com.yxx.ticketing.model.User;
import com.yxx.ticketing.model.UserOrder;

import java.util.List;

/**
 *
 *
 */
public interface UserOrderService {

    public List<UserOrder> getALLOrderByUserId(User user, UserOrder order);

    public Boolean isRealUser(User user, Integer oid);

    public void cancelOrderByOid(Integer oid);

    public UserOrder getOneOrderByOid(Integer oid);

    public Boolean isStateAndTime(Integer oid, Integer fid);

    public int chargeback(Integer oid, Integer fid);

    public int payforOrderNum(String out_trade_no);

    public int upgradeOrder(Integer oid, Integer fid, String lv);

    public double getPayMoney(Integer oid, Integer fid, String lv);

    double getPayMoneyForTime(Integer oid, Integer newfid, Integer oldfid, String lv);

    int ticketChangeTime(Integer oid, Integer newfid, Integer oldfid, String lv);
}
