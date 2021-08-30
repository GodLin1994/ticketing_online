//基础路径
let base = document.getElementById('testScript').getAttribute('base');
//出发城市
let startCity = document.getElementById('testScript').getAttribute('startCity');
//目的城市
let endCity = document.getElementById('testScript').getAttribute('endCity');
//出发日期
let startDate = document.getElementById('testScript').getAttribute('startDate');
//是否往返
let round = document.getElementById('testScript').getAttribute('round');
let backDate;
if (round) {
    backDate = document.getElementById('testScript').getAttribute('backDate');
}
//添加数组时不添加已存在的值
Array.prototype.pushNoRepeat = function () {
    for (let i = 0; i < arguments.length; i++) {
        let ele = arguments[i];
        if (this.indexOf(ele) === -1) {
            this.push(ele);
        }
    }
};

//时间过滤器
Vue.filter('time', function (data, fomrmat) {
    return moment(data).format(fomrmat || 'HH:mm');
});
Vue.filter('timeUtil', function (data, datafomrmat, fomrmat) {
    return moment(data, datafomrmat).format(fomrmat);
});

//快捷查询
let app = new Vue({
    el: "#app",
    data() {
        return {
            //0加载中  1加载完毕
            loading: 0,
            //0单程     1：去程   2：返程
            Round: 0,
            //已选的去程航班信息
            trip: {},
            //下拉框选中的值
            selected: '单程',
            //出发城市
            startCity: startCity,
            //目的城市
            endCity: endCity,
            //出发日期
            startDate: startDate,
            //返回日期
            backDate: '',
            //筛选的时间类型
            filter_dateType: [],
            //筛选的航空公司
            filter_airline: [],
            //所有的航空公司
            airlines: [],
            //筛选的起飞机场
            filter_airport: [],
            //所有的起飞机场
            airports: [],
            //筛选的机型
            filter_plane: [],
            //所有的机型
            planes: [],
            //航班信息
            flights: [],
            //筛选后符合条件的航班信息
            filter_flights: [],
            //0未展开:订票   1展开:收起
            seen: [],
            //7天的日期和星期
            prev_date: [],

        }
    },
    watch: {
        // 使用这个属性，可以监听 data 中指定数据的变化，
        // 然后触发 watch 中对应的 function 处理函数
        // 其中传参：第一个（newVal）是指改变后的值，第二个（oldVal）是原值
        backDate: function (newVal, oldVal) {
            if (newVal !== '') {
                this.selected = '往返';
            } else {
                this.selected = '单程';
                this.Round = 0;
            }
        },
        selected: function (newVal, oldVal) {
            if (newVal === '单程') {
                this.backDate = '';
                this.Round = 0;
            }
        },
        startDate: function (newVal, oldVal) {
            if (newVal !== '') {

            }
        },
        filter_airline: function (newval, oldval) {
            console.log('newval---->' + newval);
            console.log('oldval---->' + oldval);
            if (newval !== '') {
                this.filterAirlineFlights();
            }else{
                this.flights=this.filter_flights;
            }
        },
        filter_airport: function (newval, oldval) {
            console.log('newval---->' + newval);
            console.log('oldval---->' + oldval);
            if (newval !== '') {
                this.filterAirportFlights();
            }else{
                this.flights=this.filter_flights;
            }
        },
        filter_plane: function (newval, oldval) {
            console.log('newval---->' + newval);
            console.log('oldval---->' + oldval);
            if (newval !== '') {
                this.filterAirPlaneFlights();
            }else{
                this.flights=this.filter_flights;
            }
        }

    },
    methods: {

        //切换单程或往返
        change: function () {

        },
        //重新搜索
        research: function () {
            this.flush();
            this.flushTitle();
            if (this.backDate !== '') {//往返搜索

            }
            this.Round = 0;
            this.getFlights();

        },
        //显示或隐藏详细信息
        open: function (index) {
            if (this.seen[index] === 0) {
                this.$set(this.seen, index, 1);
            } else {
                this.$set(this.seen, index, 0);
            }
        },
        //获取航班信息
        getFlights: function () {
            this.loading = 0;
            let _t = this;
            let params = {};
            if (this.Round === 0) {//搜索去程或单程
                params = {
                    startCity: _t.startCity,
                    endCity: _t.endCity,
                    startDate: _t.startDate
                };
            } else if (this.Round === 1) {//搜索返程
                params = {
                    startCity: _t.endCity,
                    endCity: _t.startCity,
                    startDate: _t.backDate
                };
            }
            console.log('params--->');
            console.log(params);
            setTimeout(function () {
                axios.get(base + "/flight", {params}).then(function (response) {
                    if (_t.selected === '单程') {
                        _t.Round = 0;
                    } else if (_t.selected === '往返') {
                        _t.Round += 1;
                    }
                    //初始化条件
                    _t.seen = [];
                    _t.airlines = [];
                    _t.airports = [];
                    _t.planes = [];
                    _t.loading = 1;

                    _t.flights = response.data.data;
                    _t.filter_flights = response.data.data;
                    //符合条件的航班数量
                    let length = _t.flights.length;

                    for (let i = 0; i < length; i++) {
                        _t.airlines.pushNoRepeat(_t.flights[i].airlineName);
                        _t.airports.pushNoRepeat(_t.flights[i].startAirportName);
                        _t.planes.pushNoRepeat(_t.flights[i].planeModel);
                        _t.seen.push(0);

                    }
                });
            }, 500);

        },

        //更新model
        flush: function () {
            this.startCity = $("#startCity").val();
            this.endCity = $("#endCity").val();
            this.startDate = $("#startDate").val();
            this.backDate = $("#backDate").val();
        },
        //清除筛选条件
        cleanFilter: function () {
            this.filter_dateType = [];
            this.filter_plane = [];
            this.filter_airline = [];
            this.filter_airport = [];
        },
        //更新标题栏
        flushTitle: function () {
            let title = this.startCity + "到" + this.endCity + "机票预订";
            let newUrl = base + "/flight/" + this.startCity + "/" + this.endCity + "?startDate=" + this.startDate;
            if (this.selected === '往返' && this.backDate !== '') {
                newUrl += "&backDate=" + this.backDate;
            }
            history.pushState({}, '', newUrl);
            document.title = title;
        },
        //交换两城市
        changeCity: function () {
            let temp = this.startCity;
            this.startCity = this.endCity;
            this.endCity = temp;
        },
        //预定
        book: function (flight, level) {
            if (this.Round === 0) {//单程购票
                window.location.href = base + '/book/' + flight.flightId + '/' + level;
            } else if (this.Round === 1) {//设为去程
                this.trip = flight;
                this.trip['level'] = level;
                this.getFlights();
            } else if (this.Round === 2) {//往返购票
                window.location.href = base + '/book/round/' + this.trip.flightId + '/' + this.trip.level + '/' + flight.flightId + '/' + level;
            }
        },
        //日期滚动条
        prevDate: function () {

        },
        //移除条件
        removeFilter: function (type, index) {
            if (type === 'dateType') {
                this.filter_dateType.splice(index, 1)
            } else if (type === 'airline') {
                this.filter_airline.splice(index, 1)
            } else if (type === 'plane') {
                this.filter_plane.splice(index, 1)
            } else if (type === 'airport') {
                this.filter_airport.splice(index, 1)
            }
        },
        filterAirlineFlights: function () {
            let _t = this;
            this.flights = this.filter_flights.filter(function (flight) {
                console.log(flight);
                for (let i = 0; i < _t.filter_dateType.length; i++) {
                    console.log('filter_dateType-->');
                    console.log(_t.filter_dateType);
                    //return flight;
                }
                for (let i = 0; i < _t.filter_airline.length; i++) {
                    console.log('filter_airline-->');
                    console.log(_t.filter_airline);
                    if (_t.filter_airline[i] === flight.airlineName) {
                        return flight;
                    }
                }
                for (let i = 0; i < _t.filter_airport.length; i++) {
                    console.log('filter_airline-->');
                    console.log(_t.filter_airport);
                    if (_t.filter_airport[i] === flight.endAirportName) {
                        return flight;
                    }
                }
                for (let i = 0; i < _t.filter_plane.length; i++) {
                    console.log('filter_plane-->');
                    console.log(_t.filter_plane);
                    if (_t.filter_plane[i] === flight.planeModel) {
                        return flight;
                    }
                }

            })
        },
        filterAirportFlights: function () {
            let _t = this;
            this.flights = this.filter_flights.filter(function (flight) {

                for (let i = 0; i < _t.filter_airport.length; i++) {
                    console.log('filter_airline-->');
                    console.log(_t.filter_airport);
                    if (_t.filter_airport[i] === flight.endAirportName) {
                        return flight;
                    }
                }

            })
        },
        filterAirPlaneFlights: function () {
            let _t = this;
            this.flights = this.filter_flights.filter(function (flight) {
                console.log(flight);
                for (let i = 0; i < _t.filter_plane.length; i++) {
                    console.log('filter_plane-->');
                    console.log(_t.filter_plane);
                    if (_t.filter_plane[i] === flight.planeModel) {
                        return flight;
                    }
                }

            })
        }
    },
    created: function () {
        if (round === 'true') {
            this.selected = '往返';
            this.backDate = backDate;
        }
        this.getFlights();
        document.title = this.startCity + "到" + this.endCity + "机票预订";
    },
    computed: {
        //航班列表过滤器

    }
});