import time

import redis
from flask import Flask
from flask import render_template
# import utils
from flask import jsonify
temp=[0]
app = Flask(__name__)
# 创建连接
conn = redis.Redis(host='127.0.0.1', password='', port=6379, db=0, decode_responses=True)


@app.route('/', methods=['get', 'post'])
def hello_world():
    return render_template("main.html")


@app.route('/time', methods=['get', 'post'])
def get_time():  # 获取时间 右上角
    time_str = time.strftime("%Y{}%m{}%d{} %X")
    return time_str.format("年", "月", "日")


@app.route('/c1', methods=['get', 'post'])
def get_num():  # 获取销售金额 订单数量 客户数量
    total = int(float(conn.get("total_money")))
    order = conn.get("order_num")
    recipient = conn.zcard("recipient")
    speed= int(conn.get('order_num'))-int(temp[0])
    temp[0]=conn.get('order_num')
    return jsonify({"total": int(total), "order": order, "recipient": recipient,"speed":speed})


@app.route('/c2', methods=['get', 'post'])
def get_receive_place():  # 获取收货省份前十排行 地图
    place = []
    for m, n in conn.zrevrange("total_receiveplace", 0, -1, withscores=True):
        if m == "内蒙":
            m = "内蒙古"
        elif m == "黑龙":
            m = "黑龙江"
        place.append({'name': m, 'value': int(n)})
    return jsonify({'data': place})


@app.route('/r1', methods=['get', 'post'])
def get_book_name():  # 获取图书名称前十排行
    data = conn.zrevrange("10_book_name", 0, 10, withscores=True)
    bookName, score = [], []
    for m, n in data:
        bookName.append(str(m))
        score.append(int(n))
    for i in range(len(bookName)):
        bookName[i] = bookName[i][1:12]
    return jsonify({"name": bookName[0:10], "score": score[0:10]})


@app.route('/r2', methods=['get', 'post'])
def get_payment():  # 获取付款方式排序
    payment = []
    for m, n in conn.zrevrange("total_methodofpayment", 0, -1, withscores=True):
        payment.append({'value': int(n), 'name': m})
    return jsonify({'data': payment})


@app.route('/l1', methods=['get', 'post'])
def get_book_type():  # 获取图书类型前十排行
    data = conn.zrevrange("10_book_type", 0, 10, withscores=True)
    bookType, score = [], []
    for m, n in data:
        bookType.append(str(m))
        score.append(int(n))
    return jsonify({"type": bookType[0:10], "score": score[0:10]})


@app.route('/l2', methods=['get', 'post'])
def get_book_press():  # 获取图书出版社前十排行
    data = conn.zrevrange("10_book_press", 0, 10, withscores=True)
    bookPress, score = [], []
    for m, n in data:
        bookPress.append(str(m))
        score.append(int(n))
    return jsonify({"press": bookPress[0:10], "score": score[0:10]})

# @app.route('/', methods=['get', 'post'])
if __name__ == '__main__':
    app.run()
