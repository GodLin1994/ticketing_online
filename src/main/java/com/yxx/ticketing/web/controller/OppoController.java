package com.yxx.ticketing.web.controller;

import com.yxx.ticketing.web.converter.LoginUser;
import com.yxx.ticketing.model.Oppo;
import com.yxx.ticketing.model.ResponseData;
import com.yxx.ticketing.model.User;
import com.yxx.ticketing.service.OppoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 *
 */
@Controller
@RequestMapping(value = "oppo")
@ResponseBody
public class OppoController {

    private final OppoService oppoService;

    @Autowired
    public OppoController(OppoService oppoService) {
        this.oppoService = oppoService;
    }

    /**
     * 查询乘机人
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseData getOppo(@LoginUser User user){
        List<Oppo> oppos = oppoService.findByUserId(user.getId());
        return new ResponseData().success(oppos);
    }
}
