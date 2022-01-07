function get_time() {
    $.ajax({
        url: "/time",
        method:"post",
        timeout: 10000, //超时时间设置为10秒；
        success: function (data) {
            $("#tim").html(data)
        },
        error: function (xhr, type, errorThrown) {

        }
    });
}

function get_c1_data() {
    $.ajax({
        url: "/c1",
        method:"post",
        success: function (data) {
            $(".num h1").eq(0).text(data.total);
            $(".num h1").eq(1).text(data.order);
            $(".num h1").eq(2).text(data.recipient);
            $(".num h1").eq(3).text(data.speed);
        },
        error: function (xhr, type, errorThrown) {

        }
    })
}

function get_c2_data() {
    $.ajax({
        url: "/c2",
        method:"post",
        success: function (data) {
            ec_center_option.series[0].data = data.data;
            ec_center.setOption(ec_center_option)
        },
        error: function (xhr, type, errorThrown) {

        }
    })
}

function get_l1_data() {
    $.ajax({
        url: "/l1",
        method:"post",
        success: function (data) {
            ec_left1_option.xAxis.data = data.type;
            ec_left1_option.series[0].data = data.score;
            ec_left1.setOption(ec_left1_option);
        }
    })
}

function get_l2_data() {
    $.ajax({
        url: "/l2",
        method:"post",
        success: function (data) {
            ec_left2_option.xAxis.data = data.press;
            ec_left2_option.series[0].data = data.score;
            ec_left2.setOption(ec_left2_option);
        }
    })
}

function get_r1_data() {
    $.ajax({
        url: "/r1",
        method:"post",
        success: function (data) {
            ec_right1_option.xAxis.data = data.name;
            ec_right1_option.series[0].data = data.score;
            ec_right1.setOption(ec_right1_option);
        }
    })
}

function get_r2_data() {
    $.ajax({
        url: "/r2",
        method:"post",
        success: function (data) {
            ec_right2_option.series[0].data = data.data;
            ec_right2.setOption(ec_right2_option);
        }
    })
}

// get_time();
// get_c1_data();
// get_c2_data();
// get_l1_data();
// get_l2_data();
// get_r1_data();
// get_r2_data();

setInterval(get_time, 1000);
setInterval(get_c1_data, 1000);
setInterval(get_c2_data, 1000);
setInterval(get_l1_data, 1000);
setInterval(get_l2_data, 1000);
setInterval(get_r1_data, 1000);
setInterval(get_r2_data, 1000);
