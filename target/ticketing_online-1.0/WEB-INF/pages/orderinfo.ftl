<#assign base=request.contextPath />

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>订单详情</title>

    <!-- Bootstrap -->
    <link href="${base}/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/res/css/corptravel.css" rel="stylesheet">

    <style type="text/css">

        [v-cloak] {
            display: none;
        }
    </style>
</head>
<body>
<#include 'header.ftl'>
<div id="app" class="container" v-cloak>
    <div class="container bg-gray-eee box-shadow mar-bottom-30" style="padding-right:0px; padding-left:0px;">
        <div class="rightCon" v-cloak>
            <h3>订单信息</h3>
            <table border="0" cellspacing="0" cellpadding="0" class="table font12 v-align-top bor-bottom-solid-1">
                <tr style="background:#F5F5F5;" v-cloak>
                    <th>
                        行程
                        <template v-if="order.saleFlights.length>1">
                            (往返)
                        </template>
                        <template v-else>
                            (单程)
                        </template>
                    </th>
                    <th>起飞时间/机场</th>
                    <th>到达时间/机场</th>
                    <th>航班号</th>
                    <th>舱位</th>
                    <th>操作</th>
                </tr>
                <tr v-for="(flight,index) in order.saleFlights" :key="flight.id" v-cloak>
                    <td style="font-size: 16px;">
                        <span v-if="order.saleFlights.length>1" style="font-size: 12px;font-weight: bold;">
                            <template v-if="index==0">
                                (去程)
                            </template>
                            <template v-if="index==1">
                                (返程)
                            </template>
                        </span>
                        <strong style="color:#0093dd;">{{flight.startCityName}}</strong>
                        <span style="font-size: 12px;">至</span>
                        <strong style="color:#0093dd;">{{flight.endCityName}}</strong>
                    </td>
                    <td>
                        <p>{{flight.flightStartTime | time('YYYY-MM-DD HH:mm ')}}
                            <span style="font-size: 13px;">
                                {{flight.startAirportName}}
                            </span>
                        </p>
                    </td>
                    <td>
                        <p>{{flight.flightEndTime | time('YYYY-MM-DD HH:mm ')}}
                            <span style="font-size: 13px;">
                                {{flight.endAirportName}}
                            </span>
                        </p>
                    </td>
                    <td>（{{flight.airlineName}}）{{flight.flightNum}}</td>
                    <td>{{flight.saleOppos[0].lv}}</td>
                    <td>
                        <button type="button" class="btn btn-sm btn-primary" @click="ticketChange(flight)"
                                :disabled="order.state!='已付款'" data-toggle="modal" data-target="#change">
                            改签
                        </button>

                        <button type="button" :disabled="flight.saleOppos[0].lv=='头等舱'||order.state!='已付款'"
                                @click="upgrade(flight.flightId)"
                                class="btn btn-sm btn-primary" data-toggle="modal" data-target="#myModal">升舱
                        </button>
                        <button type="button" class="btn btn-sm btn-primary" v-if="order.state=='已付款'"
                                @click="back(flight.flightId)" data-toggle="modal" data-target="#back">
                            退款
                        </button>
                    </td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="0" class="table font12  v-align-top bor-bottom-solid-1">
                <tr style="background:#F5F5F5;">
                    <th>下单时间</th>
                    <th>订单编号</th>
                    <th>订单状态</th>
                    <th class=" bg-gray-f5 text-right">结算总价</th>
                </tr>
                <tr class="">
                    <td>{{order.cTime | time('YY-MM-DD HH:mm')}}</td>
                    <td>{{order.num}}</td>
                    <td style="font-size: 16px;color: #0066cc">{{order.state}}</td>
                    <td align="right" style="color:#ff0253; font-size:14px; font-weight:bold;">￥{{order.tPrice}}</td>
                </tr>

            </table>

            <h3>乘客信息
                <small class="mar-left-10">联系人：{{order.contact}}（{{order.cPhone}}）</small>
            </h3>
            <table border="0" cellspacing="0" cellpadding="0" class="table font12  v-align-top bor-bottom-solid-1">
                <tr class=" bg-gray-f5">
                    <th>乘客姓名</th>
                    <th>证件类型</th>
                    <th>证件号码</th>
                    <th>票面价</th>
                    <th>舱位等级</th>
                </tr>
                <tr class="" v-for="(oppo,index) in order.saleFlights[0].saleOppos" v-cloak>
                    <td>{{oppo.opname}}</td>
                    <td>身份证</td>
                    <td>{{oppo.idCard}}</td>
                    <td style="color:#ff0253; font-size:14px; font-weight:bold;">￥{{oppo.price}}</td>
                    <td>{{oppo.lv}}</td>

                </tr>
            </table>


            <div class="text-center">
                <input type="button" value="返回" class="btn btn-default" onClick="history.go(-1);"></div>
            <div class="clearfix"></div>
        </div>
    </div>


    <!-- 升舱窗口 -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         style="padding-top: 200px;">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">升舱</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <label for="exampleInputName2">原舱位</label>
                            <span type="text" class="form-control" id="exampleInputName2">
                                {{order.saleFlights[0].saleOppos[0].lv}}
                            </span>
                        </div>
                        <div class="col-sm-6">
                            <label for="exampleInputEmail2">升舱后舱位</label>
                            <select class="form-control" id="exampleInputEmail2" v-model="upgradeLevel"
                                    @change="getUpgradeMoney"
                                    v-if="order.saleFlights[0].saleOppos[0].lv!=='头等舱'">
                                <option>头等舱</option>
                                <option v-if="order.saleFlights[0].saleOppos[0].lv=='经济舱'">商务舱</option>
                            </select>
                        </div>
                        <div class="col-sm-6"></div>
                        <div class="col-sm-6" style="padding-top: 10px;" align="right">
                            升舱后价格：
                            <span style="color: rgb(255, 2, 83); font-size: 14px; font-weight: bold;padding-right: 30px;">
                                ￥{{upgradeMoney}}
                            </span>
                        </div>

                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" @click="upgradeSubmit">确认升舱</button>
                </div>
            </div>

        </div>
    </div>

    <!-- 退款窗口 -->
    <div class="modal fade" id="back" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         style="padding-top: 200px;">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">退款</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <div>
                                <div class="form-group">
                                    <label for="exampleInputName2">退款理由</label>
                                    <select id="exampleInputName2" class="form-control" v-model="reason">
                                        <option>不想要了</option>
                                        <option>信息填错了</option>
                                        <option>快给老子退款</option>
                                    </select>
                                </div>
                            </div>
                            <div style="padding-top: 10px">
                                <textarea class="form-control" rows="3" title="" placeholder="填写你的理由详情"></textarea>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" @click="backSubmit">发起退款</button>
                </div>
            </div>

        </div>
    </div>

    <!-- 改签窗口 -->
    <div class="modal fade" id="change" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         style="padding-top: 200px;">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">改签</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-4">
                            <label for="date">选择改签日期</label>
                            <input type="text" class="form-control" id="date" placeholder="请选择日期" v-model="changeDate"
                                   onclick="WdatePicker({
                                   doubleCalendar:true,
                                   minDate:'%y-%M-%d',
                                   maxDate:'%y-{%M+6}-%d',
                                   dateFmt:'yyyy/MM/dd',
                                   onpicking:function(dp){
                                   app.$data.changeDate=dp.cal.getNewDateStr();
                                   },
                                 })">
                        </div>
                        <div class="col-sm-4">
                            <template v-if="filterFlights.length>0">
                                <label for="exampleInputEmail2">请选择改签航班</label>
                                <select id="exampleInputEmail2" class="form-control" v-model="changeFlightId">
                                    <option v-for="(flight,index) in filterFlights" :value="flight.flightId">
                                        {{flight.flightStartTime | time('HH:mm')}}-{{flight.flightEndTime | time('HH:mm')}}
                                    </option>
                                </select>
                            </template>
                            <template v-else>
                                <label>请选择改签航班</label>
                                <span class="form-control">当天没有航班了</span>
                            </template>
                        </div>
                        <div class="col-sm-4" v-if="filterFlights.length>0">
                            <label for="seatlv">请选择舱位</label>
                            <select id="seatlv" class="form-control">
                                <option>头等舱</option>
                                <option>商务舱</option>
                                <option>经济舱</option>

                            </select>
                        </div>
                        <div class="col-sm-6"></div>
                        <div class="col-sm-6" style="padding-top: 10px;" align="right">
                            改签后价格：
                            <span style="color: rgb(255, 2, 83); font-size: 14px; font-weight: bold;padding-right: 30px;">
                                ￥{{upgradeMoney}}
                            </span>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" @click="backSubmit">确认改签</button>
                </div>
            </div>

        </div>
    </div>
</div>

<#include 'footer.ftl'>
</body>


<script src="${base}/res/js/sources/vuejs-2.5.16.js"></script>
<script src="${base}/res/js/sources/axios-0.18.0.js"></script>
<script src="${base}/res/js/sources/moment.js"></script>
<script src="${base}/res/js/My97DatePicker/WdatePicker.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous">
</script>
<script id="testScript" src="${base}/res/js/orderinfo.js" base="${base}" orderId="${orderId}"></script>
<script>

</script>
