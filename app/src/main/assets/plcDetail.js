var myChart = echarts.init(document.getElementById("pcldetail"));

function createCharts(data,units){

var option = {
        tooltip: {
                trigger: 'axis',
                formatter: '{b0}:{c0}'+units,
                axisPointer: {
                    animation: false
                }
            },
        xAxis: {
            type: 'time',
            data: [],
            splitLine: {
                        show: false
                    }
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [],
            type: 'line',
            smooth: true
        }]
    };
    var xAxisData = [];
    var seriesData = [];
    var nums = count(data);
    var hasData = true;
    if(nums > 0){
        for(var i=0;i<nums;i++){
            xAxisData.push(data[i].date);
            seriesData.push(data[i].value);
        }
    }else{
     hasData = false;
    }
    option.xAxis[0].data = xAxisData;
    option.series[0] = seriesData;
    myChart.setOption(option);
    if(!hasData){
            myChart.showLoading({
                  text: '暂无数据！',
                  color: '#c23531',
                  textColor: '#000',
                  maskColor: 'rgba(255, 255, 255, 0.8)',
                  zlevel: 0
                });
    }

}
//获取对象或者数组对长度
function count(o){
    var t = typeof o;
    if(t == 'string'){
            return o.length;
    }else if(t == 'object'){
            var n = 0;
            for(var i in o){
                    n++;
            }
            return n;
    }
    return false;
}
