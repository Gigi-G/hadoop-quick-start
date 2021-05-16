# hadoop-3.2.2



## Install

Follow this link to install it: <a href="https://phoenixnap.com/kb/install-hadoop-ubuntu" target="_blank">https://phoenixnap.com/kb/install-hadoop-ubuntu</a>



## Example - Word Count

Copy files in HDFS:

```bash
hdfs dfs -mkdir /WordCount
hdfs dfs -copyFromLocal hadoop-quick-start/WordCount/ /WordCount/ 
```

Start Word Count:

```bash
hadoop jar hadoop-quick-start/hadoop-3.2.2/demo/target/demo-1.0-SNAPSHOT.jar com.wordcount.WordCount /WordCount/input /WordCount/output
```

