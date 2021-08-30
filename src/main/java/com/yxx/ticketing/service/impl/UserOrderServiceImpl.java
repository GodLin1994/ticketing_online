package com.yxx.ticketing.service.impl;

import com.yxx.ticketing.dao.AirfareMapper;
import com.yxx.ticketing.dao.FlightMapper;
import com.yxx.ticketing.dao.UserOrderMapper;
import com.yxx.ticketing.dao.WalletMapper;
import com.yxx.ticketing.model.Airfare;
import com.yxx.ticketing.model.Flight;
import com.yxx.ticketing.model.User;
import com.yxx.ticketing.model.UserOrder;
import com.yxx.ticketing.model.*;
import com.yxx.ticketing.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 *
 */
@Service
@Transactional
public class UserOrderServiceImpl implements UserOrderService {

    private final UserOrderMapper userOrderMapper;
    private final AirfareMapper airfareMapper;
    private final FlightMapper flightMapper;
    private final WalletMapper walletMapper;

    @Autowired
    public UserOrderServiceImpl(UserOrderMapper userOrderMapper, AirfareMapper airfareMapper,
                                FlightMapper flightMapper, WalletMapper walletMapper) {
        this.userOrderMapper = userOrderMapper;
        this.airfareMapper = airfareMapper;
        this.flightMapper = flightMapper;
        this.walletMapper = walletMapper;
    }

    @Override
    public List<UserOrder> getALLOrderByUserId(User user, UserOrder order) {
        return userOrderMapper.getALLOrderByUserId(user,order);
    }

    @Override
    public Boolean isRealUser(User user, Integer oid) {
        System.out.println("isRealUser"+user);
        UserOrder userOrder = userOrderMapper.getUserByOid(oid);
        System.out.println("isRealUser"+userOrder);
        return Integer.parseInt(userOrder.getUserId().trim()) == user.getId();
    }

    @Override
    public void cancelOrderByOid(Integer oid) {
        UserOrder order = userOrderMapper.getUserByOid(oid);
        if (order.getState().equals("新订单")){
            userOrderMapper.updateOrderState(oid,"已取消");
            airfareMapper.updateAirfareByOrderId(oid,null);
        }
    }

    @Override
    public UserOrder getOneOrderByOid(Integer oid) {
        return userOrderMapper.getOneOrderByOid(oid);
    }

    @Override
    public Boolean isStateAndTime(Integer oid,Integer fid) {
        UserOrder userOrder = userOrderMapper.getUserByOid(oid);
        if (!userOrder.getState().equals("已付款")){
            return false;
        }
        System.out.println("还在");
        //拿到航班对应出发时间
        Flight flight = flightMapper.getOneFlight(fid);
        long m = flight.getsTime().getTime() - new Date().getTime();
        long minutes = (m / (1000 * 60));
        if (minutes < 180) {
            System.out.println("还在"+minutes);
            return false;
        }
        return true;
    }

    @Override
    public int chargeback(Integer oid, Integer fid) {
        int rs = 0;
        double d = 0;
        List<Airfare> airfares = airfareMapper.getAirfareByOidAndFid(oid, fid);
        for (Airfare airfare : airfares) {
           d += airfare.getPrice()+50;
        }
        UserOrder userOrder = userOrderMapper.getOneOrderByOid(oid);

        int countFlightsByOid = userOrderMapper.getCountFlightsByOid(oid);
        //判断是否是单程订单
        if (countFlightsByOid==1){
            userOrderMapper.updateOrderState(oid,"已退单");
        }
        rs=airfareMapper.updateAirfareByOrderId(oid,fid);
        System.out.println(rs);
        rs=walletMapper.updateWalletBalanceById(Integer.parseInt(userOrder.getUserId()),d);
        System.out.println(rs);
        return rs;
    }

    @Override
    public int payforOrderNum(String out_trade_no) {
        int rs = userOrderMapper.pay_updateOrderState(out_trade_no);
        return rs;
    }

    @Override
    public int upgradeOrder(Integer oid, Integer fid, String lv) {

        //找到原来的机票
        List<Airfare> airs = airfareMapper.getAirfareByOidAndFid(oid, fid);
        //先把原来的订单下机票变为可出售
        airfareMapper.updateAirfareByOrderId(oid,fid);
        //找到新机票s
        List<Airfare> airfares = airfareMapper.selectAirfaresToUpgrade(fid, lv);
        int rs= 0;
        for (int i = 0; i < airs.size(); i++) {
            rs = airfareMapper.updateAirfareByid(((Airfare)(airfares.toArray()[i])).getId()
                    ,((Airfare)(airs.toArray()[i])).getOppoId(),oid);
        }

        return rs;
    }

    @Override
    public double getPayMoney(Integer oid, Integer fid, String lv) {
        UserOrder order = userOrderMapper.getOneOrderByOid(oid);
        if (!"已付款".equals(order.getState())) {
            return 0;
        }
        Flight flight = flightMapper.getOneFlight(fid);
        long m = flight.getsTime().getTime() - new Date().getTime();
        long minutes = (m / (1000 * 60));
        if (minutes < 180) {
            return 0;
        }

        //找到原来的机票
        List<Airfare> airs = airfareMapper.getAirfareByOidAndFid(oid, fid);
        //找到新机票s
        List<Airfare> airfares = airfareMapper.selectAirfaresToUpgrade(fid, lv);
        double money = 0;
        for (int i = 0; i < airs.size(); i++) {
            money += ((Airfare)(airfares.toArray()[i])).getPrice()-((Airfare)(airs.toArray()[i])).getPrice();
        }
        return money;
    }

    @Override
    public double getPayMoneyForTime(Integer oid, Integer newfid, Integer oldfid, String lv) {
        UserOrder order = userOrderMapper.getOneOrderByOid(oid);
        if (!"已付款".equals(order.getState())) {
            return 0;
        }
        Flight flight = flightMapper.getOneFlight(oldfid);
        long m = flight.getsTime().getTime() - new Date().getTime();
        long minutes = (m / (1000 * 60));
        if (minutes < 180) {
            return 0;
        }

        //找到原来的机票
        List<Airfare> airs = airfareMapper.getAirfareByOidAndFid(oid, oldfid);
        //找到新机票s
        List<Airfare> airfares = airfareMapper.selectAirfaresToUpgrade(newfid, lv);
        double money = 0;
        for (int i = 0; i < airs.size(); i++) {
            money += ((Airfare)(airfares.toArray()[i])).getPrice()-((Airfare)(airs.toArray()[i])).getPrice();
        }
        return money;
    }

    @Override
    public int ticketChangeTime(Integer oid, Integer newfid, Integer oldfid, String lv) {

        //找到原来的机票
        List<Airfare> airs = airfareMapper.getAirfareByOidAndFid(oid, oldfid);
        //先把原来的订单下机票变为可出售
        airfareMapper.updateAirfareByOrderId(oid,oldfid);
        //找到新机票s
        List<Airfare> airfares = airfareMapper.selectAirfaresToUpgrade(newfid, lv);
        int rs= 0;
        for (int i = 0; i < airs.size(); i++) {
            rs = airfareMapper.updateAirfareByid(((Airfare)(airfares.toArray()[i])).getId()
                    ,((Airfare)(airs.toArray()[i])).getOppoId(),oid);
        }

        return rs;

    }
}
