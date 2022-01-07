var ec_left2 = echarts.init(document.getElementById('l2'));
var ec_left2_option = {
    //标题样式
    title: {
        text: "图书出版社前十排行榜",
        textStyle: {
            color: 'white',
        },
        left: 'left'
    },
    color: ['#dbd21b'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    xAxis: {
        type: 'category',
        axisLine: {//这是y轴文字颜色
            lineStyle: {
                color: "#fff",
            }
        },
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
ec_right1.setOption(ec_left2_option);