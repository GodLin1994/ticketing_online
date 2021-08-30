package com.yxx.ticketing.web.controller;

import com.yxx.ticketing.web.converter.LoginUser;
import com.yxx.ticketing.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
@RequestMapping("book")
@Controller
public class BookController {



    /**
     * 访问单程预定页面
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/{flightId}/{level}",method = RequestMethod.GET)
    public ModelAndView book(ModelAndView modelAndView, @LoginUser User user,
                             @PathVariable(value = "flightId") int flightId,
                             @PathVariable(value = "level") String level){
        modelAndView.setViewName("booking");
        //设置路径导航内容
        List<String> path=new ArrayList<>();
        path.add("查询购票");
        path.add("预定详情");
        modelAndView.addObject("path",path);
        modelAndView.addObject("flightId",flightId);
        modelAndView.addObject("level",level);
        return modelAndView;
    }

    /**
     * 访问往返购票页面
     * @param modelAndView
     * @param user
     * @param tripId 去程航班id
     * @param tripLv 去程舱位等级
     * @param returnId 返程航班id
     * @param returnLv 返程舱位等级
     * @return
     */
    @RequestMapping(value = "/round/{tripId}/{tripLv}/{returnId}/{returnLv}")
    public ModelAndView roundBook(ModelAndView modelAndView,@LoginUser User user,
                                  @PathVariable(value = "tripId") int tripId,
                                  @PathVariable(value = "tripLv") String tripLv,
                                  @PathVariable(value = "returnId") int returnId,
                                  @PathVariable(value = "returnLv") String returnLv){
        if (user==null){
            modelAndView.setViewName("login");
            return modelAndView;
        }
        modelAndView.addObject("flightId",tripId);
        modelAndView.addObject("level",tripLv);
        modelAndView.addObject("returnId",returnId);
        modelAndView.addObject("returnLv",returnLv);
        modelAndView.setViewName("roundBook");
        return modelAndView;
    }
}
