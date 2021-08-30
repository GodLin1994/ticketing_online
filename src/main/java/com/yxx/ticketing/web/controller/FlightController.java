package com.yxx.ticketing.web.controller;

import com.yxx.ticketing.model.Flights;
import com.yxx.ticketing.model.FlightsExample;
import com.yxx.ticketing.model.ResponseData;
import com.yxx.ticketing.model.User;
import com.yxx.ticketing.service.FlightService;
import com.yxx.ticketing.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 航班资源控制器
 */
@Controller
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;
    private String dateFormat = "yyyy-MM-dd";

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    /**
     * 根据id查询航班详细信息
     *
     * @param flightId 航班id
     * @return
     */
    @RequestMapping(value = "/{flightId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData findById(@PathVariable(value = "flightId") int flightId) {

        Flights flight = flightService.findById(flightId);
        return new ResponseData().success().data(flight);
    }


    /**
     * 访问航班查询页面
     *
     * @param startCity    出发城市
     * @param endCity      目的城市
     * @param startDate    出发日期
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/{startCity}/{endCity}", method = RequestMethod.GET)
    public ModelAndView url(@PathVariable(value = "startCity") String startCity,
                            @PathVariable(value = "endCity") String endCity,
                            Date startDate,
                            Date backDate,
                            ModelAndView modelAndView) throws ParseException {
        System.out.println("startCity--->" + startCity);
        System.out.println("endCity--->" + endCity);
        System.out.println("startDate-1-->" + startDate);
        System.out.println("backDate--->" + backDate);
        if (startDate == null) {
            Date date = new Date();
            Timestamp timeStamep = new Timestamp(date.getTime());
            System.out.println("aaaa--->" + timeStamep);
            startDate = timeStamep;
        }
        System.out.println("startDate--2->" + startDate);
        modelAndView.addObject("startCity", startCity);
        modelAndView.addObject("endCity", endCity);
        modelAndView.addObject("startDate", startDate);

        if (backDate != null) {
            //往返购票
            modelAndView.addObject("backDate", backDate);
            modelAndView.addObject("round", true);
        } else {
            //单程购票
            modelAndView.addObject("round", false);
        }
        List<String> path = new ArrayList<>();
        path.add("查询购票");
        path.add(startCity + "-" + endCity);
        modelAndView.addObject("path", path);
        modelAndView.setViewName("search");
        System.out.println("modelAndView--->" + modelAndView);
        return modelAndView;
    }

    /**
     * 查询航班
     *
     * @param startCity 出发城市
     * @param endCity   目的城市
     * @param seatLevel 座位等级
     * @param airline   航空公司
     * @param startDate 出发日期
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData find(@RequestParam String startCity,
                             @RequestParam String endCity, String seatLevel, String airline,
                             @RequestParam Date startDate) {
        FlightsExample example = new FlightsExample();
        example.setOrderByClause("flight_start_time");
        FlightsExample.Criteria criteria = example.createCriteria();
        criteria.andFlightStateLike("%计划航班");
        if (startCity != null) {
            criteria.andStartCityNameLike("%" + startCity);
        }
        if (endCity != null) {
            criteria.andEndCityNameLike("%" + endCity);
        }
        if (airline != null) {
            criteria.andAirlineNameLike("%" + airline);
        }
        System.out.println("startDate==111===》" + startDate);
        //startDate = new Timestamp(startDate.getTime());
        //System.out.println("startDate===222==》"+startDate);
        System.out.println("aaaaa-->" + DataUtils.initDateByDay(startDate));
        System.out.println("bbbbb-->" + DataUtils.getTomorrow(startDate));
        if (startDate != null) {
            criteria.andFlightStartTimeBetween(DataUtils.initDateByDay(startDate),
                    DataUtils.initDateByDay(DataUtils.getTomorrow(startDate)));
        }
        System.out.println("criteria--->" + criteria.toString());
        System.out.println("'example--->'" + example.toString());
        List<Flights> flights = flightService.find(example);
        System.out.println("'flights--->'" + flights);
        return new ResponseData().success().data(flights);
    }
}
