var ec_right2 = echarts.init(document.getElementById('r2'));
var ec_right2_option = {
  tooltip: {
    trigger: 'item'
  },
  legend: {
    top: '5%',
    left: 'center'
  },
  color:['#ffeb3b','#58d6ff','#ffbef1'],
  series: [
    {
      name: '付款方式',
      type: 'pie',
      radius: ['40%', '70%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 10,
        borderColor: '#c4ffe9',
        borderWidth: 2,
          normal: {
              label: {
                  textStyle: {
                    color:'white',
                      // fontSize: 14,
                      // fontWeight:'bolder'
                  }
              },
              labelLine : {
                  lineStyle:{
                    color:'white'
                  }
              }
          }
      },
      label: {
        show: false,
        position: 'center'
      },
      emphasis: {
        label: {
          show: true,
          fontSize: '40',
          fontWeight: 'bold'
        }
      },
      labelLine: {
        show: false
      },
      data: []
    }
  ]
};

ec_right2.setOption(ec_right2_option);


