//基础路径
var base = document.getElementById('testScript').getAttribute('base');


//时间过滤器
Vue.filter('time', function (data, fomrmat) {
    return moment(data).format(fomrmat || 'HH:mm');
});


var app = new Vue({
    el: "#app",
    data() {
        return {
            //所有订单
            orders:[],
            //查询等待
            loading:1,
            //页码
            offset:1,
            //创建时间范围开始日期
            sDate:'',
            //创建时间截至日期
            eDate:'',
            //联系人姓名
            contact:'',
            //所有的订单状态
            states:['全部','新订单','已付款','已出票','已退单','已取消','已过期'],
            //选中的订单状态
            state:'全部',
            footerText:'查看更多订单'
        }
    },
    methods: {
        //获取订单列表
        getOrders:function(limit,offset){
            if (this.footerText === '没有更多订单了') {
                return;
            }
            var params={
                limit:limit,
                offset:offset
            };
            //联系人设置筛选条件
            if (this.contact !== '') {
                params['contact']=this.contact;
            }
            //订单状态筛选
            if (this.state!=='全部'){
                params['state']=this.state;
            }
            //创建时间筛选
            if (this.sDate !== ''&&this.eDate!=='') {
                params['cStart']=this.sDate;
                params['cEnd']=this.eDate;
            }
            var _t=this;
            this.loading=1;
            axios.get(base+'/order',{
                params:params
            }).then(function (value) {
                _t.loading=0;
                var length=value.data.data.length;
                if (length<limit){
                    _t.footerText='没有更多订单了';
                }
                for (var i = 0; i < length; i++) {
                    _t.orders.push(value.data.data[i])
                }
            });
        },
        //获取更多订单列表
        getMore:function () {
            this.offset+=1;
            this.getOrders(5,this.offset);
        },
        //重新查询订单
        search:function () {
            this.footerText='查看更多订单';
            this.orders=[];
            this.getOrders(5,1);
        },
        //重置筛选条件
        reset:function () {
            this.state='全部';
            this.contact='';
            this.sDate='';
            this.eDate='';
            this.orders=[];
            this.footerText='查看更多订单';
            this.getOrders(5,1);
        },
        //查看详情
        info:function(orderId){
            window.location.href=base+'/order/info/'+orderId;
        },
        //取消订单
        cancel:function (orderId) {
            axios.delete(base+'/order/'+orderId).then(function (value) {
                if (value.data.code === 200) {//取消成功
                    alert("取消成功")
                }else {
                    alert("取消失败")
                }
            })
        }
    },
    created: function () {
        //初始化订单列表
        this.getOrders(5,1);
    }
});