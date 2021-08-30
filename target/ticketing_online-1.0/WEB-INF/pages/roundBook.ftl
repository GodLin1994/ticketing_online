<#assign base=request.contextPath />


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>预定机票</title>

    <!--<link href="${base}/res/css/bootstrap.min.css" rel="stylesheet">-->
    <link href="${base}/res/css/book.css" rel="stylesheet">
    <style type="text/css">

        [v-cloak] {
            display: none;
        }
    </style>
</head>
<body>
<!--头部-->
<#include 'header-book.ftl'>

<div class="container" id="app" style="min-height: 680px">
    <div class="main">
        <!--提醒信息-->
        <div class="hint-board" v-if="startFlight.residue<10||backFlight.residue<10">
            <div class="basic-hint">
                <i class="ico-attention"></i>
                目前该舱位仅剩
                <template v-text="startFlight.residue?backFlight.residue:startFlight.residue<backFlight.residue"></template>
                个座位，请尽快完成预订。
            </div>
        </div>

        <!--乘机人信息-->
        <div class="passenger-box" v-cloak>
            <div class="box-tit">
                <h2>乘客</h2>
                <div class="notice-tips"></div>
            </div>
            <!--乘机人信息-->
            <div class="basic-card" v-for="(passenger,index) in passengers" :key="index">
                <div class="passenger-info">
                    <div class="form">
                        <!--姓名-->
                        <div class="form-line">
                            <input class="form-input" type="text" id="p_name_0" v-model="passenger.name"
                                   placeholder="姓名，请与登机证件姓名保持一致" @click="clickName(passenger)">
                            <div class="form-error-msg c-errorstyle_15" v-if="passenger.nameErr" style=" display: block;">
                                <i class="ico-error"></i>
                                <span>请按照登机所持证件填写中文姓名</span>
                            </div>
                        </div>
                        <!--身份证号码-->
                        <div class="form-line passenger-identity">
                            <div class="form-item">
                                <div class="form-select c-dropdown_2">
                                    <div class="form-select-txt">
                                        <span>身份证</span>
                                        <i class="ico-caret-down"></i>
                                    </div>
                                </div>
                                <div class="form-error-msg hide c-errorstyle_15">
                                    <i class="ico-error"></i>
                                    <span></span>
                                </div>
                            </div>&nbsp;
                            <div class="form-item">
                                <input id="p_card_no_0" placeholder="登机证件号码" v-model="passenger.idCard"
                                       class="form-input J_input text-transform-uppercase c-formatter_3 c-input_4"
                                       type="text" autocomplete="off" maxlength="18" @click="clickIdCard(passenger)">
                                <div class="form-error-msg" v-if="passenger.idCardErr">
                                    <i class="ico-error"></i>
                                    <span>请输入正确的证件号码</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <a href="javascript:void (0);" v-if="passengers.length>1" class="delete c-ubt_11" @click="delPassenger(index)">
                        <i class="close">×</i>
                        <span>删除</span></a>
                    <div class="passenger-num"><i :class="'ico-num-'+(index+1)"></i></div>
                </div>
            </div>


            <!--添加乘机人-->
            <div class="add-passenger" style="display:inline-block">
                <a @click="addPassengerNum" class="button basic c-ubt_11" style="cursor: pointer;"
                   :class="{disabled:passengers.length>8}">+ 添加乘客</a>
            </div>

        <#--最多添加9名乘客-->
            <div class="form-error-msg c-errorstyle_15" :class="{hide:passengers.length<9}"
                 style="display: inline-block;">
                <i class="ico-error"></i>
                <span>您最多只能预订9位乘客，您已订满，无法继续添加乘客。</span>
            </div>
        </div>

        <!--联系人-->
        <div class="contact-box contact-box-v2" v-cloak>
            <div class="box-tit"><h2>联系人</h2></div>
            <div class="basic-card">
                <div class="form-line">
                    <div class="form-item contact-phone">
                        <div class="form-select">
                            <div class="form-select-txt">
                                <label>
                                    <input type="text" style="cursor: pointer; border: none; width: 137px;
                                    overflow: hidden;" class="c-countryCode_16" autocomplete="off"
                                           readonly="readonly" value="手机号">
                                </label>
                                <i class="ico-caret-down"></i>
                            </div>
                        </div>
                        <input id="I_contact_phone" type="text" maxlength="11" autocomplete="off"
                               v-model="contact.mobile" @click="click(contact,'mobileErr')"
                               class="form-input c-formatter_3 c-input_4" placeholder="手机号，接收航变信息">
                        <div class="form-error-msg c-errorstyle_15" v-if="contact.mobileErr" style="display: block;">
                            <i class="ico-error"></i>
                            <span>请填写正确的手机号码，以便接收信息</span>
                        </div>
                    </div>

                    <div class="form-item contact-mail">
                        <input id="I_contact_name" type="text" maxlength="50" placeholder="联系人姓名" v-model="contact.name"
                               autocomplete="off" class="form-input" @click="click(contact,'nameErr')">
                    <#--<label for="I_contact_name" class="form-input-hint">联系人姓名</label>-->
                        <div class="form-error-msg c-errorstyle_15" v-if="contact.nameErr">
                            <i class="ico-error"></i>
                            <span>请填写正确的联系人姓名</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <#--提交按钮-->
        <div class="action-box" v-cloak>
            <a @click="submit" id="J_saveOrder" :class="{hide:loading}" href="javascript:void(0);" class="button" style="display: inline-block;">
                下一步
            </a>
            <a href="javascript:void(0);" class="button disabled" :class="{hide:!loading}">
                <i class="ico-loading-flight"></i>正在提交...
            </a>
        </div>
    </div>
    <!--航班信息-->
    <div class="sidebar" v-cloak>
        <div class="sidebar" id="J_sidebar" style="">
            <div class="sidebar-cont" id="J_sidebar_cont" style="position: static;">
            <#--去程航班信息-->
                <div class="flight-info " style="border-bottom:none;padding-bottom:0" v-cloak>
                    <div class="flight-city">
                        <span class="basic-tag">去</span>
                        <div class="flt-date">
                            {{startFlight.flightStartTime | time()}}
                            <span class="week">{{startFlight.flightStartTime | time('dddd')}}</span>
                        </div>
                        <div class="flt-depart ">{{startFlight.startCityName}}</div>&nbsp;
                        <div class="flt-arrow">
                            <i class="ico-arrow"></i>
                        </div>&nbsp;
                        <div class="flt-arrive ">{{startFlight.endCityName}}</div>
                    </div>
                    <div class="flight-tit">
                        <span class="flt-airline">
                            <img alt="" :src="'//pic.c-ctrip.com/flight_intl/airline_logo/32/'+startFlight.airlineShorthand+'.png'"
                                 width="16" height="16">{{startFlight.airlineName}} {{startFlight.flightNum}}
                        </span>
                        <span class="plane-type">{{startFlight.planeModel}}</span>
                        <span class="flt-seat">${level}</span>
                    </div>
                    <div class="flight-detail">
                        <div class="flt-depart">
                            <span class="time">{{startFlight.flightStartTime | time}}</span>
                            <span class="airport">{{startFlight.startAirportName}}</span>
                        </div>
                        <div class="flt-arrow flt-non-stop">
                            <div class="cost-time">
                                <i class="ico-clock"></i>{{startFlight.difference}}
                            </div>
                            <div class="timeline">
                                <i class="ico-airport"></i>
                                <i class="dot"></i>
                                <i class="dot dot-end"></i>
                            </div>
                        </div>
                        <div class="flt-arrive">
                            <span class="time">
                                {{startFlight.flightEndTime | time}}
                                <span class="abbr" v-if="startFlight.daySpan">
                                    <small>+1天</small>
                                    </span>
                            </span>
                            <div id="arriveTime_1_1" style="display: none">
                                到达时间为第2天，2019年06月16日 00时45分
                            </div>
                            <span class="airport">{{startFlight.endAirportName}}</span>
                        </div>
                    </div>
                    <#--返程航班信息-->
                    <div class="flight-info " style="padding-top: 0;" v-cloak>
                        <div class="separation-line"></div>
                        <div class="flight-city">
                            <span class="basic-tag">返</span>
                            <div class="flt-date">
                                {{backFlight.flightStartTime | time()}}
                                <span class="week">{{backFlight.flightStartTime | time('dddd')}}</span>
                            </div>
                            <div class="flt-depart ">{{backFlight.startCityName}}</div>&nbsp;
                            <div class="flt-arrow">
                                <i class="ico-arrow"></i>
                            </div>&nbsp;
                            <div class="flt-arrive ">{{backFlight.endCityName}}</div>
                        </div>
                        <div class="flight-tit">
                        <span class="flt-airline">
                            <img alt="" :src="'//pic.c-ctrip.com/flight_intl/airline_logo/32/'+backFlight.airlineShorthand+'.png'"
                                 width="16" height="16">{{backFlight.airlineName}} {{backFlight.flightNum}}
                        </span>
                            <span class="plane-type">{{backFlight.planeModel}}</span>
                            <span class="flt-seat">${returnLv}</span>
                        </div>
                        <div class="flight-detail">
                            <div class="flt-depart">
                                <span class="time" v-cloak>{{backFlight.flightStartTime | time}}</span>
                                <span class="airport">{{backFlight.startAirportName}}</span>
                            </div>
                            <div class="flt-arrow flt-non-stop">
                                <div class="cost-time">
                                    <i class="ico-clock"></i>{{backFlight.difference}}
                                </div>
                                <div class="timeline">
                                    <i class="ico-airport"></i>
                                    <i class="dot"></i>
                                    <i class="dot dot-end"></i>
                                </div>
                            </div>
                            <div class="flt-arrive">
                            <span class="time" v-cloak>
                                {{backFlight.flightEndTime | time}}
                                <span class="abbr" v-if="backFlight.daySpan">
                                    <small>+1天</small>
                                    </span>
                            </span>
                                <div id="arriveTime_1_1" style="display: none">
                                    到达时间为第2天，2019年06月16日 00时45分
                                </div>
                                <span class="airport">{{backFlight.endAirportName}}</span>
                            </div>
                        </div>
                    </div>
                </div>

            <#--价格统计-->
                <div class="flight-cost">
                    <div class="cost-detail">
                        <div class="cost-row">
                            <div class="cost-tit">
                                <span>去程成人</span>
                            </div>
                            <div class="corner">
                                <span class="price"><dfn>¥</dfn>{{startFlight.price}}</span>
                                <span class="num">x {{passengers.length}}</span>
                            </div>
                        </div>
                        <div class="cost-row">
                            <div class="cost-tit">
                                <span>返程成人</span>
                            </div>
                            <div class="corner"><span class="price"><dfn>¥</dfn>{{backFlight.price}}</span>
                                <span class="num">x {{passengers.length}}</span>
                            </div>
                        </div>
                        <div class="cost-row">
                            <div class="cost-tit">
                                <span>机建</span>
                            </div>
                            <div class="corner">
                                <span class="price"><dfn>¥</dfn>100</span><span class="num">x 1</span>
                            </div>
                        </div>
                        <div class="cost-row">
                            <div class="cost-tit"><span>燃油税</span>
                            </div>
                            <div class="corner">免费</div>
                        </div>
                    </div>
                    <div class="total-price">
                        <span><dfn>¥</dfn>{{(backFlight.price+startFlight.price)*passengers.length+100}}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<#include "footer.ftl">
</body>

<script src="${base}/res/js/sources/vuejs-2.5.16.js"></script>
<script src="${base}/res/js/sources/axios-0.18.0.js"></script>
<script src="${base}/res/js/sources/moment.js"></script>
<script src="${base}/res/js/roundBook.js" id="roundBook.js" base="${base}" flightId="${flightId}" level="${level}"
        returnId="${returnId}" returnLv="${returnLv}"></script>

</html>