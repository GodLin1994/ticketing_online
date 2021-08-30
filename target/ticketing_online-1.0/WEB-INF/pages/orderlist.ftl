<#assign base=request.contextPath />

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>我的订单</title>

    <!-- Bootstrap -->
    <link href="${base}/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/res/css/orderlist.css" rel="stylesheet">
    <link href="${base}/res/css/corptravel.css" rel="stylesheet">
    <link href="${base}/res/css/loading.css" rel="stylesheet">


    <style type="text/css">

        [v-cloak] {
            display: none;
        }
    </style>
</head>
<body>
<#include 'header.ftl'>

<div class="container bg-gray-eee mar-bottom-30" id="app" style="min-height: 680px;">

<#--搜索条件输入框-->
    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="apdiv" class="w90 text-right">创建时间</label>
                <input type="text" class="form-control input-sm" style=" width:120px; display:inline-block;"
                       placeholder="日期" id="startTime" v-model="sDate" onClick="WdatePicker({
                           maxDate:'#F{$dp.$D(\'endTime\')}',
                           onpicking:function(dp){
                               app.$data.sDate=dp.cal.getNewDateStr();
                           }})">
                &nbsp;至&nbsp;
                <input type="text" class="form-control input-sm" style=" width:120px; display:inline-block;"
                       placeholder="日期" id="endTime" v-model="eDate" onClick="WdatePicker({
                           minDate:'#F{$dp.$D(\'startTime\')}',
                           onpicking:function(dp){
                               app.$data.eDate=dp.cal.getNewDateStr();
                           }})">
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="contact" class="w90 text-right">联系人</label>
                <input v-model="contact" type="text" class="form-control input-sm" style=" width:200px;
                    display:inline-block;" id="contact"
                       placeholder="联系人姓名">
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="state" class="w90 text-right">订单状态</label>
                <select id="state" name="select" class="form-control input-sm" v-model="state" v-cloak
                        style=" width:200px; display:inline-block;" @change="search">
                    <option v-for="state in states" :value="state">{{state}}</option>
                </select>
            </div>
        </div>
    </div>

<#--按钮-->
    <div class="row">
        <div class="text-center mar-top-10 bor-top-solid-1 pad-top-10 mar-bottom-10">
            <button @click="search" type="button" class="btn btn-danger btn-sm mar-right-20"
                    :disabled="loading==1">查询</button>
            <button @click="reset" type="button" class="btn btn-default btn-sm mar-right-20">清空条件</button>
        </div>
    </div>


<#--结果列表头-->
    <div class="row" style="padding-top: 15px;font-weight: bolder" v-if="orders.length>0">
        <div class="text-center" style="width: 4%;float: left">&nbsp;&nbsp;</div>
        <div class="col-sm-4 text-center">创建时间</div>
        <div class="text-center" style="width: 10%;float: left">行程</div>
        <div class="col-sm-1 text-center">联系人</div>
        <div class="col-sm-2 text-center">联系电话</div>
        <div class="col-sm-1 text-center">结算总价</div>
        <div class="col-sm-1 text-center">订单状态</div>
        <div class="text-center">操作</div>
    </div>

<#--结果列表-->
    <div style="padding: 10px;" v-for="(order,index) in orders" v-cloak class="container">
        <div class="row box-shadow" style="background: #fff;padding-top: 20px;padding-bottom: 20px;min-height: 40px;">
            <div class="text-center" style="font-size: 12px;font-weight: bold;width: 4%;float: left">
                <template v-if="order.saleFlights.length>1">
                    往返
                </template>
                <template v-else>
                    单程
                </template>
            </div>
            <div class="text-center col-sm-4">
                <div class="col-sm-12">
                    订单号：
                    <span style="color: #0066cc">{{order.num}}</span>
                </div>
                <div class="col-sm-12">
                    {{order.cTime | time('YYYY-MM-DD HH:mm')}}
                </div>
            </div>
            <div class="text-center" style="width: 10%;float: left;">
                <template v-if="order.saleFlights.length>0">
                    <p>{{order.saleFlights[0].startCityName}}——{{order.saleFlights[0].endCityName}}</p>
                </template>
                <template v-else>
                    <p>已取消</p>
                </template>
            </div>
            <div class="text-center col-sm-1">
                <p>{{order.contact}}</p>
            </div>
            <div class="text-center col-sm-2">
                <p>{{order.cPhone}}</p>
            </div>
            <div class="price text-center col-sm-1">￥{{order.tPrice}}
            </div>
            <div class="text-center col-sm-1" :class="{'text-warning':order.state=='已取消'||order.state=='已过期'||
            order.state=='已退单'}">
                {{order.state}}
            </div>
            <div class="text-center col-sm-1">
                <a class="btn btn-sm btn-default" @click="info(order.id)"
                   v-show="order.state!='已取消'&&order.state!='已过期'&&order.state!='已退单'">查看详情
                </a>
<#--                <a class="btn btn-sm" :href="'${base}/order/pay/'+order.id" target="_blank"-->
<#--                   v-if="order.state=='新订单'">去支付-->
<#--                </a>-->
                <a class="btn btn-sm" v-if="order.state=='新订单'" @click="cancel(order.id)">取消订单</a>
            </div>
        </div>
    </div>



<#--没有查询到订单-->
    <div v-if="loading==0&&orders.length==0" class="text-center row" style="padding-top: 20px;min-height: 500px;">
        <div>
            没有符合条件的订单
        </div>
    </div>

<#--等待动画-->

    <div class="loading" v-if="loading==1">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
    </div>

    <#--查看更多-->
    <div class="row text-right">
        <a class="btn btn-default" href="javascript:void(0)" @click="getMore"
           v-if="orders.length>0&&footerText=='查看更多订单'" v-show="loading==0">{{footerText}}</a>
    </div>
</div>




<#--底部-->
<#include 'footer.ftl'>
</body>
<script src="${base}/res/js/sources/vuejs-2.5.16.js"></script>
<script src="${base}/res/js/sources/axios-0.18.0.js"></script>
<script src="${base}/res/js/sources/moment.js"></script>
<script id="testScript" src="${base}/res/js/orderlist.js" base="${base}"></script>
<script src="${base}/res/js/My97DatePicker/WdatePicker.js"></script>
