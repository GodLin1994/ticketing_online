<link href="${base}/res/css/header.css" rel="stylesheet">
<!--顶部导航栏开始-->
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <nav class="navbar navbar-default" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span></button>
                    <a class="navbar-brand" href="${base}/">机票预订</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="${base}/">首页</a>
                        </li>
                        <li>
                            <a href="${base}/flight/上海/北京">查询购票</a>
                        </li>
                        <li class="dropdown">
                            <a href="${base}/order/list">订单查询</a>

                        </li>
                        <li>
<#--                            <a href="#">航班动态</a>-->
                        </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li v-if="userName != null>
                            {{userName}}
                        </li>
                        <li v-if="userName == null>
                            <a href="${base}/user/login">登录</a>
                        </li>
                    </ul>
                </div>

            </nav>
            <ul class="breadcrumb" style="background-color: #ffffff">
            <#list path as path>
                <li>
                    ${path}
                </li>
            </#list>
            </ul>
        </div>
    </div>
</div>
