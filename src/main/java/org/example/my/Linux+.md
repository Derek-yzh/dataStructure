## top uptime 
###查看整机

## vmstat -n 2 3   // mpstat -P ALL 2  // pidstat -u 1 -p 51
###查看cpu

## free -m 
###查看内存

## df -h
###查看硬盘

## iostat -xdk 2 3
###查看磁盘IO

## ifstat 1
###查看网络IO


# CPU占用过高定位分析思路
    1.先用top命令找出cpu占比最高的
    2.ps -ef 或者 jps 进一步定位，得知时一个怎么样的后台进程
    3.定位到具体线程或者代码  ps -mp 进程 -o THREAD,tid,time
    4.将需要的线程ID转换为16进制格式
    5.jstack 进程ID | grep tid(16进制线程ID小写英文) -A60(打印出前60行)