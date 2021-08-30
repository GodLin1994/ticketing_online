package com.yxx.ticketing.service.impl;

import com.yxx.ticketing.model.*;
import com.yxx.ticketing.service.FareService;
import com.yxx.ticketing.dao.OrderMapper;
import com.yxx.ticketing.model.*;
import com.yxx.ticketing.service.OppoService;
import com.yxx.ticketing.service.OrderService;
import com.yxx.ticketing.utils.OrderCoderUtil;
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
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OppoService oppoService;
    private final FareService fareService;

    @Autowired
    public OrderServiceImpl(OppoService oppoService, OrderMapper orderMapper, FareService fareService) {
        this.oppoService = oppoService;
        this.orderMapper = orderMapper;
        this.fareService = fareService;
    }

    @Override
    public int addOrder(Order order, int flightId, String level, int userId) {
        //查询舱位价格
        Double price = fareService.findPrice(flightId, level);
        //计算总价（机建费用默认50）
        Double totalPrice=price* order.getOppos().length+50;

        //查询剩余机票
        FareExample example=new FareExample();
        example.createCriteria().andFlightIdEqualTo(flightId).andSeatLevelEqualTo(level).andStateEqualTo("未出售");
        example.setOrderByClause("seat_num");
        List<Fare> fares = fareService.list(example);

        if (fares.size()>=order.getOppos().length){//余票充足
            order.setNum(OrderCoderUtil.getOrderCode((long) userId));
            order.setState("新订单");
            order.setCtime(new Date());
            order.setTprice(totalPrice);
            order.setUserId(userId);
            orderMapper.insertSelective(order);
            System.out.println("order--->"+order);
            if (order.getId()!=null){
                //插入乘机人
                int index=0;
                for (Oppo oppo:order.getOppos()){
                    oppo.setUserId(userId);
                    oppoService.addOppo(oppo);
                    if (oppo.getId()!=null){
                        //更新机票
                        Airfare fare =new Airfare();
                        fare.setId(fares.get(index).getId());
                        fare.setState("已出售");
                        fare.setOppoId(oppo.getId());
                        fare.setOrderId(order.getId());
                        fareService.updateAirFareById(fare);
                        System.out.println("fare--->"+fare);
                        index++;
                    }
                }
                return 1;
            }
        }

        return 0;
    }

    @Override
    public int addRoundOrder(Order order, int flightId, String level, int returnId, String returnLv, int userId) {
        //查询舱位价格
        Double price0 = fareService.findPrice(flightId, level);
        Double price1 = fareService.findPrice(returnId, returnLv);
        //计算总价（机建费用默认100）
        Double totalPrice=(price0+price1)* order.getOppos().length+100;

        //查询去程剩余机票
        FareExample example=new FareExample();
        example.createCriteria().andFlightIdEqualTo(flightId).andSeatLevelEqualTo(level).andStateEqualTo("未出售");
        example.setOrderByClause("seat_num");
        List<Fare> fares = fareService.list(example);
        example.clear();
        //查询返程剩余机票
        example.createCriteria().andFlightIdEqualTo(returnId).andSeatLevelEqualTo(returnLv).andStateEqualTo("未出售");
        example.setOrderByClause("seat_num");
        List<Fare> fares2 = fareService.list(example);
        if (fares.size()>=order.getOppos().length&&fares2.size()>order.getOppos().length){
            //余票充足
            order.setNum(OrderCoderUtil.getOrderCode((long) userId));
            order.setState("新订单");
            order.setCtime(new Date());
            order.setTprice(totalPrice);
            order.setUserId(userId);
            orderMapper.insertSelective(order);
            if (order.getId()!=null){
                //绑定乘机人
                int index=0;
                for (Oppo oppo:order.getOppos()){
                    oppo.setUserId(userId);
                    oppoService.addOppo(oppo);
                    if (oppo.getId()!=null){
                        //更新机票
                        Airfare fare =new Airfare();
                        Airfare fare2=new Airfare();
                        fare.setId(fares.get(index).getId());
                        fare.setState("已出售");
                        fare.setOppoId(oppo.getId());
                        fare.setOrderId(order.getId());
                        fareService.updateAirFareById(fare);
                        fare2.setId(fares2.get(index).getId());
                        fare2.setState("已出售");
                        fare2.setOppoId(oppo.getId());
                        fare2.setOrderId(order.getId());
                        fareService.updateAirFareById(fare2);
                        index++;
                    }
                }
            }
            return 1;
        }
        return 0;
    }
}
