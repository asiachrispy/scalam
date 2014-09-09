# 配置成YARN配置文件存放目录
# export YARN_CONF_DIR=/data1/software/hadoop/etc/hadoop/
SPARK_JAR=/data1/software/spark-1.0.0-bin-hadoop2/lib/spark-assembly-1.0.0-hadoop2.2.0.jar \
./spark-class org.apache.spark.deploy.yarn.Client \
–jar ins-bluewhale-scala-1.0.0.jar \
–class com.chris.scala.WC \
–args yarn-standalone \
–args hdfs://name1:9000/test/stderr.log \
–args hdfs://name1:9000/test/ \
–num-workers 1 \
–master-memory 1g \
–worker-memory 1g \
–worker-cores 1