package com.yxx.ticketing.web.controller;

import com.yxx.ticketing.service.FareService;
import com.yxx.ticketing.model.Fare;
import com.yxx.ticketing.model.FareExample;
import com.yxx.ticketing.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 *
 */
@Controller
@RequestMapping("fare")
public class FareController {
    private final FareService fareService;

    @Autowired
    public FareController(FareService fareService) {
        this.fareService = fareService;
    }

    /**
     * 查询舱位价格
     * @return
     */
    @RequestMapping(value = "/{flightId}/{seatLevel}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData findPrice(@PathVariable(value = "flightId") int flightId,
                                  @PathVariable(value = "seatLevel") String seatLevel){
        System.out.println("flightId---->"+flightId);
        System.out.println("seatLevel---->"+seatLevel);
        Double price = fareService.findPrice(flightId, seatLevel);
        System.out.println("price---->"+price);
        return new ResponseData().success().data(price);
    }

    /**
     * 根据航班id查询可预定的所有机票
     * @param flightId
     * @return
     */
    @RequestMapping(value = "/flight/{flightId}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData findByFlightId(@PathVariable(value = "flightId",required = true)Integer flightId){
        FareExample example=new FareExample();
        FareExample.Criteria criteria = example.createCriteria();
        criteria.andFlightIdEqualTo(flightId).andStateEqualTo("未出售");
        List<Fare> fares = fareService.list(example);
        return new ResponseData().success().data(fares);
    }



    /**
     * 统计剩余机票数量
     * @param fare
     * @return
     */
    @RequestMapping(value = "/count",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData countByExample(Fare fare){
        FareExample example=new FareExample();
        FareExample.Criteria criteria = example.createCriteria();
        if (fare.getFlightId()!=null){
            criteria.andFlightIdEqualTo(fare.getFlightId());
        }
        if(fare.getSeatLevel()!=null){
            criteria.andSeatLevelEqualTo(fare.getSeatLevel());
        }
        long count = fareService.count(example);
        Map<String,Long> map=new HashMap<>();
        map.put("count",count);
        return new ResponseData().success().data(map);
    }


}
