//基础路径
var base = document.getElementById('testScript').getAttribute('base');

var app = new Vue({
    el: "#app",
    data() {
        return {
            //用户名
            username: '',
            //密码
            password: '',
            //手机号
            mobile: '',
            //确认密码
            surePassword: ''
        }

    },
    userName: '',
    methods: {
        Submit:function(e){
            console.log(e);
            e.preventDefault();
        },
        //注册
        register: function () {
            var _t = this;
            var params = {
                username: this.username,
                password: this.password,
                mobile: this.mobile,
                surePassword: this.surePassword,
            };
            console.log('base--->' + base);
            //alert(base)
            axios.post(base + "/user/register", params).then(function (response) {
                console.log(response);
                if (response.data.code === 200) {
                    window.location.href = base;
                    //this.userName = response.data.message;
                } else {
                    alert(response.data.message);
                }
            });
        },
    }
});