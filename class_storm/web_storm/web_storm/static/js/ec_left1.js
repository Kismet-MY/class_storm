var ec_left1 = echarts.init(document.getElementById('l1'));
var ec_left1_option = {
    //标题样式
    title: {
        text: "图书类型前十排行榜",
        textStyle: {
            color: 'white',
        },
        left: 'left'
    },
    color: ['#61a0a8','#c23531','#2f4554',  '#d48265', '#91c7ae','#749f83', '#ca8622'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    xAxis: {
        type: 'category',
        axisLine: {  //这是x轴文字颜色
            lineStyle: {
                color: "#fff",
            }},
        axisLabel: { interval: 0, rotate: 30 },

        data: []

    },
    yAxis: {
        type: 'value',
        axisLine: {//这是y轴文字颜色
            lineStyle: {
                color: "#fff",
            }
        }
    },
    series: [{
        data: [],
        type: 'bar',
        barMaxWidth: "50%"
    }]
};
ec_left1.setOption(ec_left1_option);