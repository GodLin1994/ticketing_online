<#assign base=request.contextPath />

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>在线机票预订</title>

    <!-- Bootstrap -->
    <link href="${base}/res/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${base}/res/css/index.css">
    <link rel="stylesheet" href="${base}/res/css/itinerary.css">
    <link rel="stylesheet" href="${base}/res/css/chooseCity.css">

</head>
<body>
<!--头部-->
<#include 'header.ftl'>

<div class="container">

<#---->
    <div class="row clearfix" id="app1">
    <#--快捷查询-->
        <div class="col-md-4 column ">
            <form class="form-horizontal panel panel-default" style="padding: 10px" role="form">
            <#--选项栏-->
                <div class="form-group" style="padding: 10px;">
                    <ul class="nav nav-tabs nav-justified">
                        <li v-bind:class="{ active: isActive==0 }">
                            <a href="javascript:void(0)" @click="clickActive(0)">快捷购票</a>
                        </li>
                        <li v-bind:class="{ active: isActive==1 }">
                            <a href="javascript:void(0)" @click="clickActive(1)">航班动态</a>
                        </li>
                    </ul>
                </div>
            <#--快捷购票-->
                <div class="kuaijiegoupiao" :class="{display: isActive!=0}">
                    <div class="form-group">
                        <div class="col-sm-8" style="float: right;">
                            <label class="radio-inline">
                                <input type="radio" @click="oneOrRound(0)" name="optionsRadios" id="optionsRadios1"
                                       value="option1" checked>单程
                            </label>
                            <label class="radio-inline">
                                <input type="radio" @click="oneOrRound(1)" name="optionsRadios" id="optionsRadios2"
                                       value="option2">往返
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="citySelect" class="col-sm-4 control-label">出发城市</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="citySelect"/>
                            <span class="help-block"></span>
<#--                        请输入正确的城市名    <span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="citySelect1" class="col-sm-4 control-label">目的城市</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="citySelect1"/>
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-4 control-label">去程日期</label>
                        <div class="col-sm-8">
                            <input onclick="WdatePicker()" type="text" class="form-control" id="inputEmail3"/>
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-4 control-label">返程日期</label>
                        <div class="col-sm-8">
                            <input onclick="WdatePicker()" type="text" class="form-control" id="inputPassword3"
                                   v-bind:disabled="isRound==0"/>
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-10">
                            <button type="submit" class="btn btn-primary" @click="research('上海','北京')">搜索</button>
                        </div>
                    </div>
                </div>

            <#--航班动态-->
                <div class="kuaijiegoupiao" :class="{display: isActive!=1}">
                    暂时没有航班动态
                </div>

            </form>
        </div>

    <#--图片-->
        <div class="col-md-8 column">

        </div>
    </div>
</div>


<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="${base}/res/js/sources/jquery-2.1.4.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="${base}/res/js/sources/bootstrap.js"></script>

<script src="${base}/res/js/sources/vuejs-2.5.16.js"></script>
<script src="${base}/res/js/sources/axios-0.18.0.js"></script>
<script src="${base}/res/js/index.js"></script>
<script src="${base}/res/js/My97DatePicker/WdatePicker.js"></script>
<script src="${base}/res/js/sources/chooseCity.js"></script>


<script>
     var startCity=new Vcity.CitySelector({input:'citySelect'});
     var endCity= new Vcity.CitySelector({input:'citySelect1'});
    console.log(startCity.input)
    console.log(endCity.input)
    $(document).ready(function () {
        // 在这里写你的代码...


    });
</script>
</body>
</html>