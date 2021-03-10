# Docker-Hadoop

## Table of contents

<!--ts-->

   * [Start](#start)
   * [Configure Environment](#configure-environment)
   * [Use Docker Hadoop](#use-docker-hadoop)
   * [Scripts](#scripts)
   * [Example](#example)

<!--te-->

## Start

First of all you have to install docker-hadoop following this guide:<br>
<a href="https://clubhouse.io/developer-how-to/how-to-set-up-a-hadoop-cluster-in-docker/">https://clubhouse.io/developer-how-to/how-to-set-up-a-hadoop-cluster-in-docker/</a>



## Configure Environment

If you want to configure your environment, you have to follow these steps:

1. Stop the docker containers:

   ```bash
   docker stop historyserver nodemanager1 resourcemanager datanode1 namenode
   ```

2. Go to your local **big-data-europe/docker-hadoop** and modify the `docker-compose.yml` file adding this two lines under *namenode*:

   ```dockerfile
   namenode:
   ...                          
         	- <YOUR-LOCAL-PATH>:/home/shared 
   ...
   ```

   In <YOUR-LOCAL-PATH> you can insert the local directory that contains your hadoop examples.

   An example of `docker-compose.yml` file is inside **./Examples**.

3. Remove the docker containers:

   ```bash
   docker rm historyserver nodemanager1 resourcemanager datanode1 namenode
   ```

4. Run this command:

   ```bash
   docker-compose up -d
   ```

5. Now you can use your examples with docker!



## Use Docker Hadoop

Use this command:

```bash
docker exec -it namenode bash
```

If you are on Ubuntu or you use WSL, you can create an alias of this command adding this line to the file **~/.bashrc**:

```bash
alias docker-hadoop='docker exec -it namenode bash'
```



## Scripts

- `start-docker-hadoop.sh`: to start docker hadoop containers;
- `stop-docker-hadoop.sh`: to start docker hadoop containers.

To use them everywhere follow these steps:

1. Create **hadoop-scripts** directory in **/usr/local**:

   ```bash
   sudo mkdir /usr/local/hadoop-scripts
   ```

2. Move the **Scripts** content in **/usr/local/hadoop-scripts**:

   ```bash
   sudo cp ./Scripts/* /usr/local/hadoop-scripts
   ```

3. Change the owner:

   ```bash
   sudo chown -R <your_user>:<your_group> /usr/local/hadoop-scripts
   ```

4. Add the execution permission:

   ```bash
   sudo chmod a+x /usr/local/hadoop-scripts/*
   ```

5. Add these lines at the end of the file **~/.bashrc**:

   ```bash
   export HADOOP_SCRIPTS_HOME=/usr/local/hadoop-scripts
   export PATH=$PATH:$HADOOP_SCRIPTS_HOME
   ```




## Example

Inside **./handoop** directory you can find a simple example called *WordCount*. First of all, you have to install **Maven**.

Then you can generate the jar file using the following command:

```bash
mvn package
```

Now you can use it!

