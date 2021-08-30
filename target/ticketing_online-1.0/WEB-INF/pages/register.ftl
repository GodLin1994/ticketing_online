<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>登录</title>
    <!-- Bootstrap -->
    <link href="${base}/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/res/css/login.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>

    <![endif]-->
</head>
<body>
<div class="container" id="app">
    <form class="form-signin" onsubmit="return false;">
        <h2 class="form-signin-heading">注册用户</h2>
        <label for="inputEmail" class="sr-only" >用户名</label>
        <input type="text" id="inputEmail" class="form-control" placeholder="用户名" required v-model="username">
        <label for="inputEmail" class="sr-only">手机号</label>
        <input type="text" id="inputEmail" class="form-control" placeholder="手机号" required v-model="mobile">
        <label for="inputEmail" class="sr-only">密码</label>
        <input type="password" id="inputEmail" class="form-control" placeholder="密码" required v-model="password">
        <label for="inputPassword" class="sr-only">确认密码</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="确认密码" required v-model="surePassword">

        <button class="btn btn-lg btn-primary btn-block" type="submit" @click="register">注册</button>
        <button class="btn btn-lg btn-primary btn-block login" type="button">已有帐号？去登陆</button>

    </form>

</div> <!-- /container -->
</body>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="${base}/res/js/sources/jquery-2.1.4.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="${base}/res/js/sources/bootstrap.js"></script>
<script src="${base}/res/js/sources/vuejs-2.5.16.js"></script>
<script src="${base}/res/js/sources/axios-0.18.0.js"></script>
<script src="${base}/res/js/register.js" id="testScript" base="${base}"></script>
<script>
    $(document).ready(function(){
        // 在这里写你的代码...
        $('.login').click(function () {
            window.location.href='${base}/user/login'
        })

    });
</script>
</html>