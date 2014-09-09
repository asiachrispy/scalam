#!/bin/bash

JAVA_HOME=/data1/software/java/jdk/
EXEC_CLASS="com.chris.scala.WC"

case $1 in
start)
    server_home="/data1/server/ins-bluewhake-task"
    LIB_DIR=/data1/server/ins-bluewhake-task/lib
    LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

    nohup $JAVA_HOME/java -server -Xms512M -Xmx512M -Xmn256M -Xss1024k -XX:PermSize=64M -XX:MaxPermSize=64M -XX:SurvivorRatio=6 -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=2 -XX:-CMSParallelRemarkEnabled -XX:+DisableExplicitGC -XX:CMSInitiatingOccupancyFraction=80 -XX:SoftRefLRUPolicyMSPerMB=0 -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Xloggc:$server_home/gc.log -Dname=SparkTask -classpath $LIB_JARS $EXEC_CLASS &
    ;;

status)
    echo `ps aux|grep "$EXEC_CLASS" |grep 'grep' -v|awk '{print $2}'`
    ;;

stop)
    pid=`ps aux|grep "$EXEC_CLASS" |grep 'grep' -v|awk '{print $2}'`
    kill -9 $pid
    ;;

esac