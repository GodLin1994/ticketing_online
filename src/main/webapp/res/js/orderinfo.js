//基础路径
var base = document.getElementById('testScript').getAttribute('base');
//订单id
var orderId = document.getElementById('testScript').getAttribute('orderId');


//时间过滤器
Vue.filter('time', function (data, fomrmat) {
    return moment(data).format(fomrmat || 'HH:mm');
});

var app=new Vue({
    el:"#app",
    data(){
        return{
            //订单的详细信息
            order:{saleFlights:[{saleOppos:[{}]}]},
            //加载中
            loading:1,

            //退单目标航班id
            backFlightId:0,
            //退款理由
            reason:'',

            //升舱目标航班id
            upgradeFlightId:0,
            //升舱价格
            upgradeMoney:0,
            //升舱舱位
            upgradeLevel:'头等舱',

            //改签前的航班
            changeFlight:{},
            //改签后日期
            changeDate:'',
            //改签后航班
            changeFlightId:0,
            //改签航班列表
            flights:[],

        }
    },
    computed: {
        //航班列表过滤器
        filterFlights: function () {
            let _t=this;
            return this.flights.filter(function (flight) {
                if (flight.flightId!==_t.changeFlight.flightId&&flight.startAirportName===_t.changeFlight.startAirportName
                    &&flight.endAirportName===_t.changeFlight.endAirportName){
                    return flight;
                }
            })
        }
    },
    methods:{
        //获取订单详情信息
        getOrderInfo:function () {
            let _t=this;
            this.loading=1;
            axios.get(base+'/order/'+orderId).then(function (value) {
                console.log(value.data);
                _t.order=value.data.data;
                _t.loading=0;
            })
        },
        //升舱按钮
        upgrade:function (flightId) {
            this.upgradeFlightId=flightId;
            this.getUpgradeMoney();
        },
        //改签按钮
        ticketChange:function (flight) {
            this.changeFlight=flight;
            this.changeDate=moment(flight.flightStartTime).format('YYYY/MM/DD');
        },
        //退款按钮
        back:function (flightId) {
            this.backFlightId=flightId;
        },
        //退款提交
        backSubmit:function(){
            axios.delete(base+'/order/back/'+this.order.id+'/flight/'+this.backFlightId).then(function (value) {
                if (value.data.code === 200) {
                    window.location.href=base+'/order/list';
                }
            })
        },
        //获取升舱价格
        getUpgradeMoney:function () {
            let _t=this;
            axios.get(base+'/order/upgrade/money',{
                params:{
                    orderId:this.order.id,
                    flightId:this.upgradeFlightId,
                    level:this.upgradeLevel
                }
            }).then(function (value) {
                if (value.data.code === 200) {
                    _t.upgradeMoney=value.data.data.money;
                }
            })
        },
        //升舱提交
        upgradeSubmit:function () {
            if (this.upgradeMoney <= 0) {
                //无需付款

            }else {
                //付款
                window.location.href=base+'/order/upgrade/'+this.order.id+'?flightId='+this.upgradeFlightId+'&level='+this.upgradeLevel;
            }
        },
    },
    created:function () {
        this.getOrderInfo();
    },
    watch:{
        // 使用这个属性，可以监听 data 中指定数据的变化，
        // 然后触发 watch 中对应的 function 处理函数
        // 其中传参：第一个（newVal）是指改变后的值，第二个（oldVal）是原值
        changeDate: function (newVal, oldVal) {
            let _t=this;
            if (newVal !== '') {
                let params={
                    startCity:this.changeFlight.startCityName,
                    endCity: this.changeFlight.endCityName,
                    startDate: this.changeDate
                };
                axios.get(base + "/flight", {
                    params
                }).then(function (response) {
                    if (response.data.code === 200) {
                        _t.flights=response.data.data;
                    }
                });
            }
        },


    },
});