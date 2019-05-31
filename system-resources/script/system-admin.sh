#!/bin/sh

AppName=system-admin.jar

#JVM参数
JVM_OPTS="-Dname=$AppName  -Duser.timezone=Asia/Shanghai -Xms512M -Xmx512M -XX:PermSize=256M -XX:MaxPermSize=512M -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDateStamps  -XX:+PrintGCDetails -XX:NewRatio=1 -XX:SurvivorRatio=30 -XX:+UseParallelGC -XX:+UseParallelOldGC"

if [ "$1" = "" ];
then
    echo -e "\033[0;31m 请输入操作名： \033[0m  \033[0;34m {start|stop|restart|status} \033[0m"
    exit 1
fi

if [ "$AppName" = "" ];
then
    echo -e "\033[0;31m 请输入应用名 \033[0m"
    exit 1
fi

function start()
{
    PID=`ps -ef |grep java|grep $AppName|grep -v grep|awk '{print $2}'`

	if [ x"$PID" != x"" ]; then
	    echo "$AppName 正在运行..."
	else
		nohup java -jar  $JVM_OPTS $AppName > /dev/null 2>&1 &
		echo "$AppName 启动成功..."
		echo " .----------------.  .----------------.  .----------------.  .----------------.
| .--------------. || .--------------. || .--------------. || .--------------. |
| |  ____  ____  | || |  _________   | || |      __      | || |  ____  ____  | |
| | |_  _||_  _| | || | |_   ___  |  | || |     /  \     | || | |_   ||   _| | |
| |   \ \  / /   | || |   | |_  \_|  | || |    / /\ \    | || |   | |__| |   | |
| |    \ \/ /    | || |   |  _|  _   | || |   / ____ \   | || |   |  __  |   | |
| |    _|  |_    | || |  _| |___/ |  | || | _/ /    \ \_ | || |  _| |  | |_  | |
| |   |______|   | || | |_________|  | || ||____|  |____|| || | |____||____| | |
| |              | || |              | || |              | || |              | |
| '--------------' || '--------------' || '--------------' || '--------------' |
 '----------------'  '----------------'  '----------------'  '----------------' "
	fi
}

function stop()
{
    echo "Stop $AppName"

	PID=""
	query(){
		PID=`ps -ef |grep java|grep $AppName|grep -v grep|awk '{print $2}'`
	}

	query
	if [ x"$PID" != x"" ]; then
		kill -TERM $PID
		echo "$AppName (pid:$PID) 已停止..."
		while [ x"$PID" != x"" ]
		do
			sleep 1
			query
		done
		echo "$AppName 已停止."
	else
		echo "$AppName 已经停止."
	fi
}

function restart()
{
    stop
    sleep 2
    start
}

function status()
{
    PID=`ps -ef |grep java|grep $AppName|grep -v grep|wc -l`
    if [ $PID != 0 ];then
        echo "$AppName 正在运行..."
    else
        echo "$AppName 已停止..."
    fi
}

case $1 in
    start)
    start;;
    stop)
    stop;;
    restart)
    restart;;
    status)
    status;;
    *)

esac
