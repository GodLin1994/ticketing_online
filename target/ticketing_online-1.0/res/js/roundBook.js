//基础路径
let base=document.getElementById('roundBook.js').getAttribute('base');
//航班id
let flightId=document.getElementById('roundBook.js').getAttribute('flightId');
//舱位等级
let level=document.getElementById('roundBook.js').getAttribute('level');
//返程航班id
let returnId=document.getElementById('roundBook.js').getAttribute('returnId');
//返程舱位等级
let returnLv=document.getElementById('roundBook.js').getAttribute('returnLv');


//时间过滤器
Vue.filter('time',function (data,fomrmat) {
    return moment(data).format(fomrmat||'HH:mm');
});

let app=new Vue({
    el:"#app",
    data(){
        return{
            //当前的乘客
            passengers:[{name:'',idCard:''}],
            //联系人信息
            contact:{mobile:'',name:''},
            //去程航班
            startFlight:{},
            //返程航班
            backFlight:{},
            loading:0,
            ERROR:false,
        }
    },
    methods:{
        //添加一个乘客
        addPassengerNum:function () {
            if (this.passengers.length <9){
                this.passengers.push({name: '', idCard: ''});
            }
        },
        //删除指定乘客
        delPassenger:function (index) {
            if (this.passengers.length > 1) {
                this.passengers.splice(index,1);
            }
        },
        //获取航班信息
        getFlight:function () {
            let _t=this;
            //去程航班
            axios.get(base+'/flight/'+flightId)
                .then(function (value) {
                    _t.startFlight=value.data.data;
                    if (level === '头等舱') {
                        app.$set(app.startFlight, 'residue', app.startFlight.fareNum.firstNum);
                        app.$set(app.startFlight, 'price', app.startFlight.fareNum.firstPrice);
                    }else if (level === '商务舱') {
                        app.$set(app.startFlight, 'residue', app.startFlight.fareNum.businessNum);
                        app.$set(app.startFlight, 'price', app.startFlight.fareNum.businessPrice);
                    }else if (level === '经济舱') {
                        app.$set(app.startFlight, 'residue', app.startFlight.fareNum.economyNum);
                        app.$set(app.startFlight, 'price', app.startFlight.fareNum.economyPrice);
                    }
                    app.$set(app.startFlight, 'isDaySpan', app.isDaySpan(app.startFlight));
                    app.$set(app.startFlight, 'difference', app.getDifference(app.startFlight));
                });
            //返程航班
            axios.get(base+'/flight/'+returnId)
                .then(function (value) {
                    _t.backFlight=value.data.data;
                    if (returnLv === '头等舱') {
                        app.$set(app.backFlight, 'residue', app.backFlight.fareNum.firstNum);
                        app.$set(app.backFlight, 'price', app.backFlight.fareNum.firstPrice);
                    }else if (returnLv === '商务舱') {
                        app.$set(app.backFlight, 'residue', app.backFlight.fareNum.businessNum);
                        app.$set(app.backFlight, 'price', app.backFlight.fareNum.businessPrice);
                    }else if (returnLv === '经济舱') {
                        app.$set(app.backFlight, 'residue', app.backFlight.fareNum.economyNum);
                        app.$set(app.backFlight, 'price', app.backFlight.fareNum.economyPrice);
                    }
                    app.$set(app.backFlight, 'isDaySpan', app.isDaySpan(app.backFlight));
                    app.$set(app.backFlight, 'difference', app.getDifference(app.backFlight));
                });

        },
        //提交
        submit:function () {
            this.validate();
            if (this.isError()){
                this.loading=true;
                let _t=this;
                let params={
                    oppos:_t.passengers,
                    contact:_t.contact.name,
                    cphone:_t.contact.mobile
                };
                axios.post(base+'/order/'+flightId+'/'+level+'/'+returnId+'/'+returnLv,params)
                    .then(function (value) {
                        _t.loading=false;
                        if (value.data.code === 200) {
                            window.location.href=base+'/order/list';
                        }else {
                            alert("预定失败")
                        }
                    })
            }
        },
        //获取剩余舱位
        getFareCount:function () {
            let _t=this;
            axios.get(base+'/fare/count',{
                params:{
                    flightId:'',
                    seatLevel:''
                }
            }).then(function (value) {

                })
        },
        //航班时间是否跨天
        isDaySpan:function (flight) {
            let sTime=flight.flightStartTime;
            let h1=moment(sTime).format('HH');
            let eTime=flight.flightEndTime;
            let h2=moment(eTime).format('HH');
            return h2<h1;

        },
        //计算行程用时
        getDifference:function(flight){
            let sTime=flight.flightStartTime;
            let h1=moment(sTime).format('HH');
            let eTime=flight.flightEndTime;
            let h2=moment(eTime).format('HH');
            let s3=moment.duration(eTime-sTime);
            let hours = s3.get('hours');
            let mins = s3.get('minutes');
            return hours+'h'+mins+'m';
        },
        //数据校验
        validate:function () {
            let nameReg=/^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/;
            let idReg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
            let phoneReg=/^[1][3,4,5,7,8][0-9]{9}$/;
            this.passengers.forEach(function (value, index) {
                if (!nameReg.test(value.name)) {
                    //乘机人姓名校验
                    Vue.set(value, 'nameErr', true);
                }
                if (!idReg.test(value.idCard)) {
                    //乘机人身份证号码
                    Vue.set(value, 'idCardErr', true);
                }
            });
            //校验联系人姓名
            if (!nameReg.test(this.contact.name)){
                Vue.set(this.contact, 'nameErr', true);
            }
            //校验联系人电话
            if (!phoneReg.test(this.contact.mobile)){
                Vue.set(this.contact,'mobileErr',true);
            }
        },
        //是否有输入错误
        isError:function(){
            let contact=this.contact;
            let b=false;
            this.passengers.forEach(function (value, index) {
                if (value.idCardErr || value.nameErr) {
                    b=true;
                }
            });
            if (b){
                return false;
            }
            return !(contact.nameErr || contact.mobileErr);

        },
        //输入框点击
        clickIdCard:function (value) {
            Vue.set(value, 'idCardErr', false)
        },
        clickName:function (value) {
            Vue.set(value, 'nameErr', false)
        },
        click:function (value, errStr) {
            Vue.set(value, errStr, false)
        },

    },
    created:function () {
        this.getFlight();
    }
});

