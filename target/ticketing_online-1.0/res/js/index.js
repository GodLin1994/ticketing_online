

var base='';


//快捷查询
var search = new Vue({
    el: "#app1",
    data() {
        return {
            //0： 快捷购票   1：航班动态
            isActive: 0,
            //0单程     1：往返
            isRound:0

        }
    },
    methods: {
        //切换快捷购票或航班动态 0： 快捷购票   1：航班动态
        clickActive: function (index) {
            if (this.isActive !== index) {
                console.log(index);
                this.isActive=index;
            }
        },
        //切换单程或往返 0：单程   1：往返
        oneOrRound:function (index) {
            if (index === 0) {
                this.isRound=0;
            }else if (index === 1) {
                this.isRound=1;
            }
        },
        //搜索
        research: function (startCity,endCity) {
                console.log(this.Round);
                if (this.Round === 0) {//单程购票
                    window.location.href = base + '/flight/' + '上海' + '/' + '北京';
                }else if (this.Round === 1) {//设为去程

                }else if (this.Round === 2) {//往返购票
                    //window.location.href=base+'/book/round/'+this.trip.flightId+'/'+this.trip.level+'/'+flight.flightId+'/'+level;
                }
            },
    }
});