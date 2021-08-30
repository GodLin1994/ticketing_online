//基础路径
let base=document.getElementById('book.js').getAttribute('base');
//航班id
let flightId=document.getElementById('book.js').getAttribute('flightId');
//舱位等级
let level=document.getElementById('book.js').getAttribute('level');

//时间过滤器
Vue.filter('time',function (data,fomrmat) {
    return moment(data).format(fomrmat||'HH:mm');
});

let app=new Vue({
    el:"#app",
    data(){
        return{
            //当前的乘客
            passengers:[{name:'',idCard:'',mobileErr:false,nameErr:false}],
            //航班信息
            flight:{},
            //联系人
            contact:{mobile:'',name:'',mobileErr:false,nameErr:false},
            //剩余座位数量
            residue:0,
            //航班出发时间到到达时间是否跨天
            daySpan:false,
            //航班用时
            difference:'',
            //舱位价格
            price:0,
            //机建费用
            jijian:50,
            //提交等待
            loading:false,
            //数据校验
            ERROR:false
        }
    },
    methods:{
        //添加一个乘客
        addPassengerNum:function () {
            if (this.passengers.length <9){
                this.passengers.push({name: '', idCard: '',idCardErr:false,nameErr:false});
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
            axios.get(base+'/flight/'+flightId)
                .then(function (value) {
                    _t.flight=value.data.data;
                    _t.isDaySpan();
                    _t.getFareCount();
                });
        },
        //获取剩余舱位
        getFareCount:function () {
            if (level === '头等舱') {
                this.residue=this.flight.fareNum.firstNum;
            }else if (level === '商务舱') {
                this.residue=this.flight.fareNum.businessNum;
            }else if (level === '经济舱') {
                this.residue=this.flight.fareNum.economyNum;
            }
        },
        //获取舱位价格
        getPrice:function(){
            let _t=this;
            console.log('flightId--->'+flightId)
            axios.get(base+'/fare/'+flightId+'/'+level)
                .then(function (value) {
                _t.price=value.data.data;
            })
        },
        //时间计算
        isDaySpan:function () {
            let sTime=this.flight.flightStartTime;
            let h1=moment(sTime).format('HH');
            let eTime=this.flight.flightEndTime;
            let h2=moment(eTime).format('HH');
            this.daySpan=h2<h1;

            let s3=moment.duration(eTime-sTime);
            let hours = s3.get('hours');
            let mins = s3.get('minutes');
            this.difference=hours+'h'+mins+'m';

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
                axios.post(base+'/order/'+flightId+'/'+level,params)
                    .then(function (value) {
                        console.log(value);
                        _t.loading=false;
                        if (value.data.code === 200) {
                            window.location.href=base+'/order/list';
                        }else {
                            alert(value.data.message)
                        }
                    })
            }
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
        }
    },
    computed: {


    },
    created:function () {
        //获取航班信息
        this.getFlight();
        this.getPrice();
    }
});

