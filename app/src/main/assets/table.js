function createTableDiv(data){
    var divHtml = "";
    var nums = count(data);
    if(nums > 0){
        for(var i=0;i<nums;i++){
            divHtml += '<div id="chart_'+data[i].instanceId+'" instanceId="'+data[i].instanceId+'" chartType="'+data[i].chartType+'" tableName="'+instanceName+'" class="col-xs-12" style="height:300px;"></div>';
        }
    }else{
        divHtml = '暂无自定义报表！';
    }
    $('#tabList').html(divHtml);
    if(nums > 0){
        for(var i=0;i<nums;i++){
            getChartData(data[i].instanceId);
        }

    }

}
function createChart(instanceId,data){
    var myChart = echarts.init(document.getElementById("chart_"+instanceId));
    var chartType= $('#chart_'+instanceId).attr('chartType');

    var tableName = $('#chart_'+instanceId).attr('tableName');
        var option = {
                title: {
                    text: tableName,
                    left:'center'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data:[],
                    top:'25'
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'time',
                    boundaryGap: false,
                    data: []
                },
                yAxis: {
                    type: 'value'
                },
                series: []
            };
            var legendData = [];
            var xAxisData = [];
            var seriesData = [];
            var num = count(data);
            if(num > 0){
                for(var i=0;i<num;i++){
                    var lineName = data[i].projectName+data[i].formName+data[i].fieldName;
                    legendData.push(lineName);
                    var item = data[i].datas;
                    var itemNum = count(item);

                    if(itemNum > 0){
                        var seriesItem = {name:lineName,type:"line",data:[]};
                        if(chartType == 1){
                            seriesItem.stack = '堆积';
                        }else if(chartType == 2){
                            seriesItem.type = 'bar';
                        }

                        for(var j=0;j<itemNum;j++){
                            if(j == 0){
                                xAxisData.push(item[j].date);
                            }
                            seriesItem.data.push(item[j].value);
                        }
                        seriesData.push(seriesItem);
                    }
                }
                option.legend.data = legendData;
                option.xAxis.data = xAxisData;
                option.series = seriesData;
                myChart.setOption(option);
                myChart.hideLoading();
            }else{
                noData(myChart);
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
function noData(chart){
    chart.showLoading({
                      text: '暂无数据！',
                      color: '#c23531',
                      textColor: '#000',
                      maskColor: 'rgba(255, 255, 255, 0.8)',
                      zlevel: 0
                    });
}