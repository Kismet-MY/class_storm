import time
import redis

ip = '127.0.0.1'
password = ''
r = redis.Redis(host=ip, password=password, port=6379, db=0, decode_responses=True)
start = 0
while True:
    end = int(r.get("order_num"))
    speed = end - start
    # print(speed)
    r.set('speed',speed)
    start = end
    time.sleep(1)


