var myChart = echarts.init(document.getElementById("pcldetail"));

function createCharts(data){
var option = {
        xAxis: {
            type: 'category',
            data: []
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
myChart.setOption(option);

}
