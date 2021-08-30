<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <!-- Bootstrap -->
    <link href="${base}/res/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${base}/res/css/search.css">
    <link rel="stylesheet" href="${base}/res/css/itinerary.css">
    <link rel="stylesheet" href="${base}/res/css/chooseCity.css">
    <link rel="stylesheet" href="${base}/res/css/loading.css">
    <style type="text/css">

        [v-cloak] {
            display: none;
        }
    </style>
</head>
<body style="background: #f1f1f1">

<!--头部-->

<#include 'header.ftl'>

<div class="container" id="app" style="min-height: 680px;">
    <!--查询输入框组-->
    <div class="col-md-12 column panel panel-default" style="padding: 15px;">
        <div class="col-md-12">
            <form class="form-inline">
                <div class="form-group">
                    <div class="input-group">
                        <select class="form-control" v-model="selected" @change="change" title="">
                            <option>单程</option>
                            <option>往返</option>
                        </select>
                    </div>
                    <div class="input-group col-md-2">
                        <div class="input-group-addon">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-chufa"></use>
                            </svg>
                        </div>
                        <input type="text" value="${startCity}" v-model="startCity" class="form-control input"
                               id="startCity" placeholder="出发城市">
                    </div>
                    <a @click="changeCity">←换→</a>
                    <div class="input-group col-md-2">
                        <div class="input-group-addon">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-daoda"></use>
                            </svg>
                        </div>
                        <input type="text" value="${endCity}" v-model="endCity" class="form-control input"
                               id="endCity" placeholder="目的城市">
                    </div>
                    &nbsp;
                    <div class="input-group col-md-2">
                        <div class="input-group-addon">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-riqi1"></use>
                            </svg>
                        </div>
                        <input type="text" class="form-control input" id="startDate" placeholder="选择去程日期"
                               onclick="WdatePicker({
                               doubleCalendar:true,
                               maxDate:'%y-{%M+6}-%d',
                               minDate:'%y-%M-%d',
                               dateFmt:'yyyy/MM/dd',
                               onpicking:function(dp){
                               app.$data.startDate=dp.cal.getNewDateStr();
                               },
                               onclearing:function(){
                                   app.$data.startDate='';
                               }
                               })" v-model="startDate">
                    </div>
                    &nbsp;
                    <div class="input-group col-md-2">
                        <div class="input-group-addon">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-riqi1"></use>
                            </svg>
                        </div>
                        <input type="text" class="form-control input"
                               id="backDate" onclick="WdatePicker({
                               doubleCalendar:true,
                               minDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d\'}',
                               maxDate:'%y-{%M+6}-%d',
                               dateFmt:'yyyy/MM/dd',
                               onpicking:function(dp){
                               app.$data.backDate=dp.cal.getNewDateStr();
                               },
                               onclearing:function(){
                                   app.$data.backDate='';
                               }
                               })" placeholder="选择返程日期" v-model="backDate">
                    </div>
                    &nbsp;
                    <button type="button" class="btn btn-primary" @click="research">重新搜索</button>
                </div>

            </form>
        </div>
    </div>

    <!--结果列表-->
    <div class="row">
        <!--筛选条件-->
        <div class="col-lg-3" style="width: 20%;">
            <div style="padding: 5px;">
                筛选(共
                <span v-text="flights.length"></span>
                条航班信息)
                <input type="button" class="btn btn-default" @click="cleanFilter" value="清除全部">
            </div>
            <div class="bar-filter">
                <!--起飞时段-->
                <div class="bar-filter-group">
                    <#--条件名称-->
                    <a class="bar-filter-item">起飞时段
                        <span class="bar-filter-item-deco">
                            <b class="arr_gray_sm arr_right"></b>
                        </span>
                    </a>
                    <#--可选的条件-->
                    <div class="bar-filter-select">
                        <li class="bar-filter-option">
                            <label>
                                <input v-model="filter_dateType" type="checkbox" name="ddateType" value="上午(6-12点)">上午(6-12点)
                            </label>
                        </li>
                        <li class="bar-filter-option">
                            <label>
                                <input v-model="filter_dateType" type="checkbox" name="ddateType" value="下午(12-18点)">下午(12-18点)
                            </label>
                        </li>
                        <li class="bar-filter-option">
                            <label>
                                <input v-model="filter_dateType" type="checkbox" name="ddateType" value="晚上(18-24点)">晚上(18-24点)
                            </label>
                        </li>
                    </div>

                    <#--已选的条件-->
                    <ul class="bar-filter-echo" v-if="filter_dateType.length>0" v-cloak>
                        <li class="echo-item" v-for="(dateType,index) in filter_dateType">{{dateType}}
                            <a class="close" @click="removeFilter('dateType',index)">×</a>
                        </li>
                    </ul>
                </div>
                <!--航空公司-->
                <div class="bar-filter-group" v-if="airlines.length>0" v-cloak>
                    <a class="bar-filter-item">航空公司
                        <span class="bar-filter-item-deco">
                            <b class="arr_gray_sm arr_right"></b>
                        </span>
                    </a>
                    <div class="bar-filter-select">
                        <li class="bar-filter-option" v-for="(airline,index) in airlines">
                            <label>
                                <input v-model="filter_airline" type="checkbox" name="code" :value="airline">{{airline}}
                            </label>
                        </li>
                    </div>
                    <#--已选的条件-->
                    <ul class="bar-filter-echo" v-if="filter_airline.length>0" v-cloak>
                        <li class="echo-item" v-for="(airline,index) in filter_airline">{{airline}}
                            <a class="close" @click="removeFilter('airline',index)">×</a>
                        </li>
                    </ul>
                </div>
                <!--起飞机场-->
                <div class="bar-filter-group" v-if="airports.length>0" v-cloak>
                    <a class="bar-filter-item">起飞机场
                        <span class="bar-filter-item-deco">
                            <b class="arr_gray_sm arr_right"></b>
                        </span>
                    </a>
                    <div class="bar-filter-select">
                        <li class="bar-filter-option" v-for="(airport,index) in airports">
                            <label>
                                <input v-model="filter_airport" type="checkbox" name="code" :value="airport">{{airport}}
                            </label>
                        </li>
                    </div>
                    <#--已选的条件-->
                    <ul class="bar-filter-echo" v-if="filter_airport.length>0" v-cloak="">
                        <li class="echo-item" v-for="(airport,index) in filter_airport">{{airport}}
                            <a class="close" @click="removeFilter('airport',index)">×</a>
                        </li>
                    </ul>

                </div>
                <!--计划机型-->
                <div class="bar-filter-group" v-if="planes.length>0" v-cloak>
                    <a class="bar-filter-item">计划机型
                        <span class="bar-filter-item-deco">
                            <b class="arr_gray_sm arr_right"></b>
                        </span>
                    </a>
                    <div class="bar-filter-select">
                        <li class="bar-filter-option" v-for="(plane, index) in planes">
                            <label>
                                <input type="checkbox" v-model="filter_plane" name="code" :value="plane">{{plane}}
                            </label>
                        </li>
                    </div>
                    <#--已选的条件-->
                    <ul class="bar-filter-echo" v-if="filter_plane.length>0" v-cloak="">
                        <li class="echo-item" v-for="(plane,index) in filter_plane">{{plane}}
                            <a class="close" @click="removeFilter('plane',index)">×</a>
                        </li>
                    </ul>
                </div>
            </div>

        </div>

        <div class="col-sm-9" style=";margin-bottom:15px;">
            <#--去程或返程导航-->
            <div class="flight-searching" v-if="Round===1||Round===2" v-cloak>
                <span class="flight-searching-type">
                    【选择<template v-if="Round===1">去程</template><template v-if="Round===2">返程</template>】
                </span>
                <template v-if="Round===1">{{startCity}} ⇀ {{endCity}}</template>
                <template v-if="Round===2">{{endCity}} ⇀ {{startCity}}</template>
                <span class="flight-searching-date">
                    {{startDate| timeUtil('YYYY/MM/DD','M月D号')}}&nbsp;
                    {{startDate| timeUtil('YYYY/MM/DD','dddd')}}
                </span>
            </div>

            <#--已选去程航班-->
            <div class="flight-selected" v-if="Round===2&&trip!==null" v-cloak>
                <div class="flight-selected-item">
                    <div class="flight-selected-detail">
                        <span class="voyage-tag">去</span>
                        <span class="selected-flight-no">{{trip.flightNum}}</span>
                        <span class="selected-line"></span>
                        <span class="selected-depart">{{trip.startAirportName}}</span>⇀
                        <span class="selected-arrive">{{trip.endAirportName}}</span>
                        <span>({{trip.level}})</span>
                        <span class="selected-depart-time">出发：
                            <b>{{trip.flightStartTime | time('MM月DD日 HH:mm')}}</b>
                        </span>
                        <span class="selected-arrive-time">到达：
                            <b>{{trip.flightEndTime | time('MM月DD日 HH:mm')}}</b>
                        </span>
                    </div>
                    <a class="selected-modify" href="javascript:void(0)" @click="research">修改去程</a>
                </div>
            </div>

            <!-- 日期开始 -->
<#--            <div style=" background:#f9fcff;border:1px solid #EBEBEB;">-->
<#--                <div class="arrow-left"><a href="javascript:void(0)" style="">&nbsp;</a></div>-->
<#--                <div class="arrow-right"><a href="javascript:void(0)" style="">&nbsp;</a></div>-->
<#--                <ul class="nav nav-tabs nav-justified ">-->
<#--                                        <li role="presentation" class="active"><a href="javascript:void(0)">01-11<br>周一</a></li>-->
<#--                                        <li role="presentation"><a href="javascript:void(0)">01-12<br>周二</a></li>-->
<#--                                        <li role="presentation"><a href="javascript:void(0)">01-13<br>周三</a></li>-->
<#--                                        <li role="presentation"><a href="javascript:void(0)">01-14<br>周四</a></li>-->
<#--                                        <li role="presentation"><a href="javascript:void(0)">01-15<br>周五</a></li>-->
<#--                                        <li role="presentation"><a href="javascript:void(0)">01-16<br>周六</a></li>-->
<#--                                        <li role="presentation"><a href="javascript:void(0)">01-17<br>周日</a></li>-->
<#--                </ul>-->
<#--            </div>-->
            <!-- 日期结束 -->

            <#--没有找到航班信息-->
            <div class="searchresult_content" style="padding-top: 8px;" v-if="flights.length==0&&loading==1" v-cloak>
                <div class="searchresult_error">
                    <div class="base_alert11">
                        <span class="ico_alert">&nbsp;</span>
                        <div class="alert_content"><h3>很抱歉，没有找到符合筛选条件的航班。</h3>
                        </div>
                    </div>
                </div>
            </div>

            <!--结果列表表头-->
            <div class="sort-controls clearfix">
                <ul class="sorts sort-guideline">
                    <li class="default-sort">航班信息</li>
                    <li data-ubt="c_sort_dtime_down" class="dtime-sort"><a>起飞时间<i class="ico "></i></a></li>
                    <li data-ubt="c_sort_atime_down" class="atime-sort"><a>到达时间<i class="ico "></i></a></li>
                    <li data-ubt="c_sort_sale_down" class="sale-sort"><a>价格</a></li>
                </ul>
            </div>

            <#--等待动画-->
            <div class="loading" v-show="loading==0">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>

            <#--航班列表-->
            <div v-cloak v-show="loading==1" :key="flight.flightId" v-for="(flight,index) in flights"
                 class="search_box search_box_tag search_box_light search_box_spread Label_Flight">
                <div class="search_table_header">
                    <div class="inb logo">
                        <div class="logo-item flight_logo">
                            <div>
                                <span>
                                    <strong>
                                        <img :src="'//pic.c-ctrip.com/flight_intl/airline_logo/32x32/'+flight.airlineShorthand+'.png'"
                                             class="pubFlights-logo">
                                        <i v-text="flight.airlineName"></i>
                                    </strong>
                                    <span v-text="flight.flightNum"></span>
                                </span>
                            </div>
                        </div>
                        <div>
                            <span class="direction_black_border low_text" v-text="flight.planeModel"></span>
                        </div>
                    </div>
                    <div class="inb right">
                        <div class="time_box">
                            <strong class="time">{{flight.flightStartTime | time}}</strong>
                        </div>
                        <div class="airport" v-text="flight.startAirportName"></div>
                    </div>
                    <div class="inb center">
                        <div class="arrow"></div>
                    </div>
                    <div class="inb left">
                        <div class="time_box">
                            <strong class="time">{{flight.flightEndTime | time}}</strong>
                        </div>
                        <div class="airport" v-text="flight.endAirportName"></div>
                    </div>
                    <div class="inb price child_price lowest_price">
                        <div v-show="seen[index]==0">
                            <span class="base_price02">
                                <dfn>¥</dfn>
                                {{flight.fareNum.economyPrice}}
                            </span><i>起</i>
                            <br>
                        </div>
                    </div>
                    <div class="inb book middle">
                        <button :class="seen[index]==0?'arrow_down':'arrow_up'" class="btn_book" @click="open(index)">
                            <i v-if="seen[index]==0">
                                <template v-if="Round===0||Round===2">
                                    订票
                                </template>
                                <template v-if="Round===1">
                                    预定
                                </template>
                            </i>
                            <i v-if="seen[index]==1">收起</i>
                            <b></b>
                        </button>
                    </div>
                    <div class="inb special"></div>
                </div>
                <div class="search_table search_table-list" v-show="seen[index]==1">
                    <s class="arrow-down-white"><i></i></s>

                    <#--舱位开始-->
                    <template v-for="index in 3">
                        <div class=" search_table_inner search-table-inner">
                            <div class="search_table_type search_table_type-multiple">
                                <div class="tag_name"></div>
                            </div>
                            <div class="cabin_item_v2">
                                <div class="cabin_item_table_content">
                                    <table>
                                        <tbody>
                                        <tr>
                                            <td class="border_box policy_display" style="width: 320px;">
                                            <span style="display: inline-block; cursor: help;"
                                                  class="light-blue new_tag">
                                                <span class="c-react-frame"><br><br></span>
                                            </span>
                                            </td>
                                            <td class="border_box sale_tags" style="width: 180px;">
                                                <span class="tag-list"></span>
                                            </td>
                                            <td class="border_box book_btn_channel" style="width: 100px;" rowspan="1">
                                                <div class="book_btn_channel_content">
                                                    <div class="price_area">
                                                        <div class="lowest_show_price">
                                                            <div class="price_content">
                                                                <span class="show_price" v-if="index==1"><dfn>¥</dfn>{{flight.fareNum.economyPrice}}</span>
                                                                <span class="show_price" v-if="index==2"><dfn>¥</dfn>{{flight.fareNum.businessPrice}}</span>
                                                                <span class="show_price" v-if="index==3"><dfn>¥</dfn>{{flight.fareNum.firstPrice}}</span>

                                                                <div class="cabin_type">
                                                                    <div v-if="index==1">经济舱</div>
                                                                    <div v-if="index==2">商务舱</div>
                                                                    <div v-if="index==3">头等舱</div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="border_box">
                                                <button class="btn_book" v-if="index==1" @click="book(flight,'经济舱')">
                                                    <template v-if="Round===0||Round===2">预定</template>
                                                    <template v-if="Round===1">设为去程</template>
                                                </button>
                                                <button class="btn_book" v-if="index==2" @click="book(flight,'商务舱')">
                                                    <template v-if="Round===0||Round===2">预定</template>
                                                    <template v-if="Round===1">设为去程</template>
                                                </button>
                                                <button class="btn_book" v-if="index==3" @click="book(flight,'头等舱')">
                                                    <template v-if="Round===0||Round===2">预定</template>
                                                    <template v-if="Round===1">设为去程</template>
                                                </button>
                                            </td>
                                            <td class="border_box" style="min-width: 80px;padding-left: 5px"
                                                rowspan="1">
                                                <span class="deep-red" v-if="index==1&&flight.fareNum.economyNum<10">
                                                    {{flight.fareNum.economyNum}}张
                                                </span>
                                                <span class="deep-red" v-if="index==2&&flight.fareNum.businessNum<10">
                                                    {{flight.fareNum.businessNum}}张
                                                </span>
                                                <span class="deep-red" v-if="index==3&&flight.fareNum.firstNum<10">
                                                    {{flight.fareNum.firstNum}}张
                                                </span>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </template>

                    <#--舱位结束-->
                </div>
            </div>
        </div>
    </div>
</div>

<#include "footer.ftl">
startdate::::${startCity}
startdate::::${startDate?string('yyyy/MM/dd')}
</body>
<script src="//at.alicdn.com/t/font_951432_op201lt8syf.js"></script>
<script src="${base}/res/js/sources/jquery-2.1.4.min.js"></script>
<script src="${base}/res/js/sources/bootstrap.js"></script>
<script src="${base}/res/js/My97DatePicker/WdatePicker.js"></script>
<script src="${base}/res/js/sources/vuejs-2.5.16.js"></script>
<script src="${base}/res/js/sources/axios-0.18.0.js"></script>
<script src="${base}/res/js/sources/chooseCity.js"></script>
<script src="${base}/res/js/sources/moment.js"></script>
<script id="testScript" src="${base}/res/js/search.js"
        startCity="${startCity}" endCity="${endCity}" startDate="${startDate?string('yyyy/MM/dd')}"
        round="${round?string ("true","false")}"
        <#if round>
            backDate="${backDate?string('yyyy/MM/dd')}"
        </#if>
        base="${base}">
</script>


<script>
    //绑定城市选择点击事件
    new Vcity.CitySelector({input: 'startCity'});
    new Vcity.CitySelector({input: 'endCity'});
    //初始化数据
    app.startCity = '${startCity}';
    app.endCity = '${endCity}';

    $(document).ready(function () {
        // 在这里写你的代码...


    });
</script>
</html>