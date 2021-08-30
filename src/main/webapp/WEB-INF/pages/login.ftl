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

</head>
<body>
<div class="container" id="app">
    <form class="form-signin">
        <h2 class="form-signin-heading">用户登录</h2>
        <label for="inputusername" class="sr-only">用户名/手机号</label>
        <input type="text" id="inputusername" class="form-control" placeholder="用户名/手机号" required v-model="username">
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="密码" required v-model="password">

        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
        </div>
        <button @click="login" class="btn btn-lg btn-primary btn-block" type="button">登   录</button>
        <button @click="register" class="btn btn-lg btn-primary btn-block register" type="button">没有账号？点我注册</button>
    </form>

</div> <!-- /container -->
</body>

<script src="${base}/res/js/sources/jquery-2.1.4.min.js"></script>
<script src="${base}/res/js/sources/bootstrap.js"></script>
<script src="${base}/res/js/sources/vuejs-2.5.16.js"></script>
<script src="${base}/res/js/sources/axios-0.18.0.js"></script>
<script src="${base}/res/js/login.js" id="testScript" base="${base}"></script>
</html>