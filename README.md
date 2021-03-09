# hadoop-scripts

## Table of contents

<!--ts-->

   * [Start](#start)
   * [Configure Environment](#configure-environment)
   * [Use Docker Hadoop](#use-docker-hadoop)

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

2. Go to your local **big-data-europe/docker-hadoop** and modify the `docker-compose.yml` file adding this two lines under *nodemanager1*:

   ```dockerfile
   nodemanager1:
   ...
   	volumes:                            
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
docker exec -it nodemanager1 bash
```

