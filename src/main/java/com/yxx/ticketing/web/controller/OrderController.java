package com.yxx.ticketing.web.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.github.pagehelper.PageHelper;
import com.yxx.ticketing.model.*;
import com.yxx.ticketing.service.*;
import com.yxx.ticketing.web.converter.LoginUser;
import com.yxx.ticketing.utils.OrderCoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 订单操作
 */
@RequestMapping(value = "order")
@Controller
public class OrderController {

    private final OrderService orderService;
    private final UserOrderService userOrderService;
    private final FlightService flightService;
    private final SalesFlightsService salesFlightsService;
    private final SalesOppoService salesOppoService;

    @Autowired
    public OrderController(OrderService orderService, UserOrderService userOrderService, FlightService flightService, SalesFlightsService salesFlightsService, SalesOppoService salesOppoService) {
        this.orderService = orderService;
        this.userOrderService = userOrderService;
        this.flightService = flightService;
        this.salesFlightsService = salesFlightsService;
        this.salesOppoService = salesOppoService;
    }


    /**
     * 访问订单列表界面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/list")
    public ModelAndView toOrderList(ModelAndView modelAndView,
                                    @LoginUser User user) {
        if (user != null) {//已经登陆
            List<String> path = new ArrayList<>();
            path.add("订单列表");
            modelAndView.addObject("path", path);
            modelAndView.setViewName("orderlist");
        } else {
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    /**
     * 访问订单详情界面
     *
     * @param orderId      订单id
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/info/{orderId}")
    public ModelAndView toOrderInfo(@PathVariable(value = "orderId") int orderId, ModelAndView modelAndView,
                                    @LoginUser User user) {

        List<String> path = new ArrayList<>();
        path.add("订单列表");
        path.add("订单详情");
        modelAndView.addObject("path", path);
        modelAndView.addObject("orderId", orderId);
        modelAndView.setViewName("orderinfo");
        return modelAndView;
    }

    /**
     * 查询该用户下所有的订单
     *
     * @param user
     * @param userOrder
     * @return
     */
    @ResponseBody
    @GetMapping(value = "")
    public ResponseData getOrder(@LoginUser User user, UserOrder userOrder,
                                 @RequestParam int limit, @RequestParam int offset) {
        PageHelper.startPage(offset, limit);
        List<UserOrder> orders = userOrderService.getALLOrderByUserId(user, userOrder);
        return new ResponseData().success().data(orders);
    }

    /**
     * 取消订单操作
     *
     * @param user
     * @param oid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
    public ResponseData cancelOrder(@LoginUser User user, @PathVariable(value = "orderId") Integer oid) {
        //判断该订单id是否是该用户下
        Boolean rs = userOrderService.isRealUser(user, oid);
        //是就执行取消操作
        if (!rs) {
            return new ResponseData().fail();
        }
        userOrderService.cancelOrderByOid(oid);
        return new ResponseData().success();
    }

    /**
     * 获取订单详情
     *
     * @param user
     * @param oid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public ResponseData getOneOrderByOid(@LoginUser User user, @PathVariable(value = "orderId") Integer oid) {
        Boolean rs = userOrderService.isRealUser(user, oid);
        System.out.println("oid--->" + oid);
        if (!rs) {
            return new ResponseData().fail();
        }
        UserOrder order = userOrderService.getOneOrderByOid(oid);

        List<SaleFlight> saleFlights = salesFlightsService.findByOrdId(order.getId());
        System.out.println("saleFlights-11-->" + saleFlights);
        List<SaleOppo> saleOppos = salesOppoService.getAllOpposByOidAndFid(order.getId(), saleFlights.get(0).getFlightId());
        System.out.println("saleOppos---->" + saleOppos);
        Set<SaleOppo> saleOpposSet = new HashSet<>(saleOppos);
        saleFlights.get(0).setSaleOppos(saleOpposSet);
        System.out.println("saleFlights-222--->" + saleFlights);
        Set<SaleFlight> saleFlightsSet = new HashSet<>(saleFlights);
        order.setSaleFlights(saleFlightsSet);
        System.out.println("orderdetail---->" + order);
        return new ResponseData().success().data(order);
    }

    /**
     * 退单操作(取消付款后的订单)
     * 有订单和机票订单id和确定退的是去程还是回程
     *
     * @param user
     * @param oid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "back/{orderId}/flight/{flightId}", method = RequestMethod.DELETE)
    public ResponseData chargeback(@LoginUser User user, @PathVariable(value = "orderId") Integer oid
            , @PathVariable(value = "flightId") Integer fid) {
        Boolean rs = userOrderService.isRealUser(user, oid);
        if (!rs) {
            return new ResponseData().fail();
        }
        //判断修改订单状态和出发的时间是不是和当前时间相差三小时以上
        rs = userOrderService.isStateAndTime(oid, fid);
        if (!rs) {
            return new ResponseData().fail();
        }
        //开始修改订单状态，和把机票置为可买
        int result = userOrderService.chargeback(oid, fid);
        if (result <= 0) {
            return new ResponseData().fail();
        }
        return new ResponseData().success();
    }

    /**
     * 订单付款
     *
     * @param user
     * @param oid
     * @return
     */
    @ResponseBody
    @GetMapping("/pay/{orderId}")
    public String alipayOrder(@LoginUser User user, @PathVariable(value = "orderId") Integer oid) {
        Boolean rs = userOrderService.isRealUser(user, oid);
        if (!rs) {
            return "error";
        }
        //拿到订单所有信息
        UserOrder order = userOrderService.getOneOrderByOid(oid);
        if (!order.getState().equals("新订单")) {
            return "错误页面，该订单不是新订单";
        }
        //付款
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setOut_trade_no(order.getNum());
        alipayBean.setTotal_amount(order.gettPrice());
        alipayBean.setSubject("Ticket Pay");

        String serverUrl = AlipayConfig.gatewayUrl;
        String appId = AlipayConfig.app_id;
        String privateKey = AlipayConfig.merchant_private_key;
        String format = "json";
        String charset = AlipayConfig.charset;
        String alipayPublicKey = AlipayConfig.alipay_public_key;
        String signType = AlipayConfig.sign_type;
        String returnUrl = AlipayConfig.return_url;
        String notifyUrl = AlipayConfig.notify_url;
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(returnUrl);
        alipayRequest.setNotifyUrl(notifyUrl);
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
        try {
            return alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/return_url")
    public String return_url(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, String[]> requestParams = request.getParameterMap();
        Map<String, String> params = getParams(requestParams);
        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(out_trade_no);
            int num = userOrderService.payforOrderNum(out_trade_no);
            if (num > 0) {
                return "redirect:/order/list";
            } else {
                return "错误页面";
            }
        } else {
            return "错误页面";
        }
    }


    /**
     * 升舱后的差价
     *
     * @param user
     * @param oid
     * @param flightId
     * @param level
     * @return
     */
    @RequestMapping(value = "/upgrade/money", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData getPayMoney(@LoginUser User user, @RequestParam(value = "orderId") Integer oid,
                                    @RequestParam("flightId") Integer flightId, @RequestParam("level") String level) {
        //得到升舱后金额
        double money = userOrderService.getPayMoney(oid, flightId, level);
        Map<String, Double> price = new HashMap<>();
        price.put("money", money);
        return new ResponseData().success().data(price);
    }

    /**
     * 升舱
     *
     * @param user
     * @param oid
     * @param flightId
     * @param level
     * @return
     */
    @RequestMapping(value = "/upgrade", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData upgrade(@LoginUser User user, @RequestParam(value = "orderId") Integer oid,
                                @RequestParam("flightId") Integer flightId, @RequestParam("level") String level) {
        //得到升舱后后金额
        double money = userOrderService.getPayMoney(oid, flightId, level);
        if (money <= 0) {
            int num = userOrderService.upgradeOrder(oid, flightId, level);//0订单状态改变了，不能操作
            if (num > 0) {
                return new ResponseData().success();
            } else {
                return new ResponseData().fail();
            }
        }
        return new ResponseData().fail();
    }


    /**
     * 付款后升舱
     *
     * @param user
     * @param oid
     * @param fid
     * @param lv      升舱的座位等级 1 头等 2 商务 3经济
     * @param session
     * @return
     */
    @ResponseBody
    @GetMapping("/upgrade/{orderId}")
    public String ticketChanging(@LoginUser User user, @PathVariable(value = "orderId") Integer oid,
                                 @RequestParam("flightId") Integer fid, @RequestParam("level") String lv,
                                 HttpSession session) {
        Boolean rs = userOrderService.isRealUser(user, oid);
        if (!rs) {
            return "error";
        }
        session.setAttribute("oid", oid);
        session.setAttribute("fid", fid);
        session.setAttribute("lv", lv);
        //得到现在改签后金额
        double money = userOrderService.getPayMoney(oid, fid, lv);
        if (money <= 0) {
            int num = userOrderService.upgradeOrder(oid, fid, lv);//0订单状态改变了，不能操作
            if (num > 0) {
                return "改签成功";
            } else {
                return "改签失败";
            }
        }
        //付款
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setOut_trade_no(OrderCoderUtil.getOrderCode((long) user.getId()));
        alipayBean.setTotal_amount(money + "");
        alipayBean.setSubject("ticket changing Pay");

        String serverUrl = AlipayConfig.gatewayUrl;
        String appId = AlipayConfig.app_id;
        String privateKey = AlipayConfig.merchant_private_key;
        String format = "json";
        String charset = AlipayConfig.charset;
        String alipayPublicKey = AlipayConfig.alipay_public_key;
        String signType = AlipayConfig.sign_type;
        String returnUrl = AlipayConfig.return_url2;
        String notifyUrl = AlipayConfig.notify_url;
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(returnUrl);
        alipayRequest.setNotifyUrl(notifyUrl);
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
        try {
            return alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 获取支付宝链接参数
     *
     * @param requestParams
     * @return
     */
    private Map<String, String> getParams(Map<String, String[]> requestParams) {
        Map<String, String> params = new HashMap<>();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            try {
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            params.put(name, valueStr);
        }
        return params;
    }

    @RequestMapping("/return_url2")
    public String return_url2(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
        Map<String, String[]> requestParams = request.getParameterMap();
        Map<String, String> params = getParams(requestParams);

        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            //商户订单号
            int oid = (int) session.getAttribute("oid");
            int fid = (int) session.getAttribute("fid");
            String lv = (String) session.getAttribute("lv");
            session.removeAttribute("oid");
            session.removeAttribute("fid");
            session.removeAttribute("lvv");
            int num = userOrderService.upgradeOrder(oid, fid, lv);//0订单状态改变了，不能操作
            if (num > 0) {
                return "订单主页";
            } else {
                return "错误页面";
            }
        } else {
            return "错误页面";
        }
    }


    /**
     * 改签改时间
     *
     * @param user
     * @param oid
     * @param oldfid
     * @param newfid
     * @param lv
     * @param session
     * @return
     */
    @ResponseBody
    @GetMapping("/ticketChangingTime")
    public String ticketChangingTime(@LoginUser User user, @RequestParam("oid") Integer oid,
                                     @RequestParam("oldfid") Integer oldfid, @RequestParam("newfid") Integer newfid,
                                     @RequestParam("lv") String lv,
                                     HttpSession session) {
        Boolean rs = userOrderService.isRealUser(user, oid);
        if (!rs) {
            return "error";
        }
        session.setAttribute("oid", oid);
        session.setAttribute("oldfid", oldfid);
        session.setAttribute("newfid", newfid);
        session.setAttribute("lv", lv);
        //得到现在改签后金额
        double money = userOrderService.getPayMoneyForTime(oid, newfid, oldfid, lv);
        if (money < 0) {
            int num = userOrderService.ticketChangeTime(oid, newfid, oldfid, lv);//0订单状态改变了，不能操作
            if (num > 0) {
                return "改签成功";
            } else {
                return "改签失败";
            }
        }
        if (money > 0) {
            AlipayBean alipayBean = new AlipayBean();
            alipayBean.setOut_trade_no(OrderCoderUtil.getOrderCode((long) user.getId()));
            alipayBean.setTotal_amount(money + "");
            alipayBean.setSubject("ticket changing Pay");
            String serverUrl = AlipayConfig.gatewayUrl;
            String appId = AlipayConfig.app_id;
            String privateKey = AlipayConfig.merchant_private_key;
            String format = "json";
            String charset = AlipayConfig.charset;
            String alipayPublicKey = AlipayConfig.alipay_public_key;
            String signType = AlipayConfig.sign_type;
            String returnUrl = AlipayConfig.return_url3;
            String notifyUrl = AlipayConfig.notify_url;
            AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(returnUrl);
            alipayRequest.setNotifyUrl(notifyUrl);
            alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
            try {
                return alipayClient.pageExecute(alipayRequest).getBody();
            } catch (AlipayApiException e) {
                e.printStackTrace();
                return "error";
            }
        }
        return "error";
    }


    @RequestMapping("/return_url3")
    public String return_url3(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
        Map<String, String[]> requestParams = request.getParameterMap();
        Map<String, String> params = getParams(requestParams);

        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            //商户订单号
            int oid = (int) session.getAttribute("oid");
            int newfid = (int) session.getAttribute("newfid");
            int oldfid = (int) session.getAttribute("oldfid");
            String lv = (String) session.getAttribute("lv");
            session.removeAttribute("newfid");
            session.removeAttribute("oid");
            session.removeAttribute("oldfid");
            session.removeAttribute("lv");

            int num = userOrderService.ticketChangeTime(oid, newfid, oldfid, lv);//0订单状态改变了，不能操作
            if (num > 0) {
                return "订单主页";
            } else {
                return "错误页面";
            }
        } else {
            return "错误页面";
        }
    }


    /**
     * 单程购票提交订单
     *
     * @param user
     * @param order     订单信息
     * @param flightId  航班id
     * @param seatLevel 舱位等级
     * @return
     */
    @RequestMapping(value = "/{flightId}/{level}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData bookFlight(@LoginUser User user, @RequestBody Order order,
                                   @PathVariable(value = "flightId") int flightId,
                                   @PathVariable(value = "level") String seatLevel) {
        System.out.println("user--->" + user);
        System.out.println("order___---->" + order);
        System.out.println("flightId---->" + flightId);
        System.out.println("seatLevel---->" + seatLevel);
        if (user == null) {
            return new ResponseData().fail("请先登录！");
        }
        if (order.getContact() == null || order.getCphone() == null || "".equals(order.getContact()) ||
                "".equals(order.getCphone())) {
            return new ResponseData().fail("数据有误");
        }
        int i = orderService.addOrder(order, flightId, seatLevel, user.getId());
        if (i > 0) {//下单成功
            return new ResponseData().success("提交成功");
        }
        return new ResponseData().fail("提交失败");
    }

    /**
     * 往返购票提交订单
     *
     * @param user
     * @param order
     * @param flightId 去程航班id
     * @param level    去程舱位等级
     * @param returnId 返程航班id
     * @param returnLv 返程舱位等级
     * @return
     */
    @RequestMapping(value = "/{flightId}/{level}/{returnId}/{returnLv}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData roundBook(@LoginUser User user, @RequestBody Order order,
                                  @PathVariable(value = "flightId") int flightId,
                                  @PathVariable(value = "level") String level,
                                  @PathVariable(value = "returnId") int returnId,
                                  @PathVariable(value = "returnLv") String returnLv) {
        if (order.getContact() == null || order.getCphone() == null || "".equals(order.getContact()) ||
                "".equals(order.getCphone())) {
            return new ResponseData().fail("数据有误");
        }
        int i = orderService.addRoundOrder(order, flightId, level, returnId, returnLv, user.getId());
        if (i > 0) {//下单成功
            return new ResponseData().success("提交成功");
        }
        return new ResponseData().fail();
    }
}
