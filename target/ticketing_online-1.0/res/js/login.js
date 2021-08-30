//基础路径
var base = document.getElementById('testScript').getAttribute('base');

var app=new Vue({
    el:"#app",
    data(){
        return{
            //用户名
            username:'',
            //密码
            password:'',
            //是否记住密码
            remember:false

        }

    },
    userName:'',
    methods:{
        //登陆
        login:function () {
            var _t=this;
            var params={
                username:this.username,
                password:this.password
            };
            console.log('base--->'+base);
            axios.post(base + "/user/login",params)
                .then(function (response) {
                if (response.data.code === 200) {
                    window.location.href=base;
                    //this.userName=response.data.message;
                }else {
                    alert(response.data.message);
                }
            });
        },
        //注册
        register:function () {
            window.location.href=base+'/user/register'
        }
    }
});