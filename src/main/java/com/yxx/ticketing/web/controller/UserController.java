package com.yxx.ticketing.web.controller;

import com.yxx.ticketing.web.converter.LoginUser;
import com.yxx.ticketing.model.ResponseData;
import com.yxx.ticketing.model.User;
import com.yxx.ticketing.model.UserExample;
import com.yxx.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 *
 */
@RequestMapping("user")
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 访问登录页
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView toLogin(ModelAndView modelAndView, @LoginUser User user) {

        modelAndView.setViewName("login");
        modelAndView.addObject("page", "login");
        return modelAndView;
    }

    /**
     * 访问注册页
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView toRegister(ModelAndView modelAndView) {
        modelAndView.setViewName("register");
        modelAndView.addObject("page", "register");
        return modelAndView;
    }

    /**
     * 登用户录请求
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData login(HttpSession session, @RequestBody User user) {
        System.out.println("user-->" + user);
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        if (user.getUsername() != null) {
            criteria.andUsernameEqualTo(user.getUsername());
        }
        if (user.getMobile() != null) {
            criteria.andMobileEqualTo(user.getMobile());
        }
        List<User> users = userService.findByExample(example);
        System.out.println("usersss---->" + users);
        if (users.size() > 0) {//用户名存在
            if (user.getPassword().equals(users.get(0).getPassword())) {
                //密码正确
                session.setAttribute("user", users.get(0));
                return new ResponseData().success("登录成功");
            }
            //密码不正确
            return new ResponseData().fail("密码错误");
        }
        return new ResponseData().fail("用户名或手机号不存在");
    }

    /**
     * 用户注册请求
     *
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData register(HttpSession session, @RequestBody User user) {

        System.out.println("user--->" + user);
        System.out.println("user-getPassword-->" + user.getPassword());
        System.out.println("user-getPassword-->" + user.getSurePassword());
        System.out.println("user-getPassword-???->" + user.getSurePassword() !=user.getPassword());
        if (!user.getSurePassword().equals(user.getPassword())) {
            return new ResponseData().fail("密码不一致！");
        }
        if(userService.selectByUsername(user.getUsername()) !=null){
            return new ResponseData().fail("此用户名已经被注册！");
        }
        if(userService.selectByMobile(user.getMobile()) !=null){
            return new ResponseData().fail("此手机号已经被注册！");
        }
        User newUser = new User();
        newUser.setMobile(user.getMobile());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        int isUseSuccess=userService.insertUser(user);
        System.out.println("'isUseSuccess---->'"+isUseSuccess);
        if(isUseSuccess == 1){
            return new ResponseData().success("注册成功！");
        }else{
            return new ResponseData().fail("注册失败！");
        }

    }
}
