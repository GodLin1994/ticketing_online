package com.yxx.ticketing.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 * 首页访问
 */
@Controller
@RequestMapping("")
public class IndexController {

    @RequestMapping("")
    public ModelAndView index(ModelAndView modelAndView){

        List<String> path=new ArrayList<>();
        path.add("首页");
        modelAndView.addObject("path",path);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
