# 笔记：CentOS7下启动一个mysql5.6的Docker容器

## 虚拟机centOS7
安装docker省略
下载mysql5.6镜像，启动镜像
```
[root@localhost ~]# docker pull mysql:5.6

[root@localhost ~]# docker run --name mysql5.6v2 -p 3306:3306 \
 -e MYSQL_ROOT_PASSWORD=root -d mysql:5.6

9fbb4ef87bfe33b7634ae3e76fa24689d772d1b00e364ca50c74005bc5f1a383
[root@localhost ~]# docker exec -it mysql5.6v2 bash
root@9fbb4ef87bfe:/# mysql
ERROR 1045 (28000): Access denied for user 'root'@'localhost' (using password: NO)
root@9fbb4ef87bfe:/# mysql -u root -p
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 2
Server version: 5.6.43 MySQL Community Server (GPL)

Copyright (c) 2000, 2019, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'P@ssw0rd';
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'IDENTIFIED BY 'P@ssw0rd'' at line 1
mysql> show databases
    -> ;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
+--------------------+
3 rows in set (0.01 sec)

mysql> use mysql;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed

mysql> select user,host from user
    -> ;
+------+-----------+
| user | host      |
+------+-----------+
| root | %         |
| root | localhost |
+------+-----------+
2 rows in set (0.00 sec)

mysql> create user 'yuanchengUser'@'%' identified by 'P@ssw0rd'
    -> ;
Query OK, 0 rows affected (0.03 sec)

mysql> select user,host from user
    -> ;
+---------------+-----------+
| user          | host      |
+---------------+-----------+
| root          | %         |
| yuanchengUser | %         |
| root          | localhost |
+---------------+-----------+
3 rows in set (0.00 sec)

mysql> set password for 'yuanchengUser'@'%'=password('P@ssw0rd')
    -> ;
Query OK, 0 rows affected (0.00 sec)

mysql> exit ;
Bye


```

```
[root@localhost ~]# ifconfig
docker0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 172.17.0.1  netmask 255.255.0.0  broadcast 172.17.255.255
        inet6 fe80::42:8aff:fe39:e571  prefixlen 64  scopeid 0x20<link>
        ether 02:42:8a:39:e5:71  txqueuelen 0  (Ethernet)
        RX packets 0  bytes 0 (0.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 14  bytes 1766 (1.7 KiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

ens33: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet **192.168.30.128**  netmask 255.255.255.0  broadcast 192.168.30.255
        inet6 fe80::565c:17ea:b4e8:ce87  prefixlen 64  scopeid 0x20<link>
        ether 00:0c:29:f1:bd:0d  txqueuelen 1000  (Ethernet)
        RX packets 71501  bytes 105348724 (100.4 MiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 5806  bytes 403856 (394.3 KiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

lo: flags=73<UP,LOOPBACK,RUNNING>  mtu 65536
        inet 127.0.0.1  netmask 255.0.0.0
        inet6 ::1  prefixlen 128  scopeid 0x10<host>
        loop  txqueuelen 1000  (Local Loopback)
        RX packets 68  bytes 5916 (5.7 KiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 68  bytes 5916 (5.7 KiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

vethbaf1027: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet6 fe80::60b9:a2ff:fec9:6637  prefixlen 64  scopeid 0x20<link>
        ether 62:b9:a2:c9:66:37  txqueuelen 0  (Ethernet)
        RX packets 0  bytes 0 (0.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 8  bytes 656 (656.0 B)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

virbr0: flags=4099<UP,BROADCAST,MULTICAST>  mtu 1500
        inet 192.168.122.1  netmask 255.255.255.0  broadcast 192.168.122.255
        ether 52:54:00:a2:f4:97  txqueuelen 1000  (Ethernet)
        RX packets 0  bytes 0 (0.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 0  bytes 0 (0.0 B)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

```

虚拟机的IP地址为 192.168.30.128  docker配置的是 -p 3306:3306 所以映射的是虚拟机的ip
用主机ping一下试试
```
PS C:\WINDOWS\system32> ping 192.168.30.128

正在 Ping 192.168.30.128 具有 32 字节的数据:
来自 192.168.182.140 的回复: 无法访问目标主机。
来自 192.168.30.128 的回复: 字节=32 时间<1ms TTL=64
来自 192.168.30.128 的回复: 字节=32 时间<1ms TTL=64
来自 192.168.30.128 的回复: 字节=32 时间<1ms TTL=64

192.168.30.128 的 Ping 统计信息:
    数据包: 已发送 = 4，已接收 = 4，丢失 = 0 (0% 丢失)，
往返行程的估计时间(以毫秒为单位):
    最短 = 0ms，最长 = 0ms，平均 = 0ms
```

如果ping不通可以添加一下路由
```
PS C:\WINDOWS\system32> route add 192.168.30.0 mask 255.255.255.0 192.168.182.140
 操作完成!
```
然后就通啦

这时候打开主机的navicat连接
可能会出现连不上的问题，可能是虚拟机防火墙的问题
```
[root@localhost ~]# firewall-cmd --list-ports


[root@localhost ~]# 
[root@localhost ~]# firewall-cmd --zone=public --add-port=3306/tcp --permanent
Warning: ALREADY_ENABLED: 3306:tcp
success
[root@localhost ~]# firewall-cmd --list-ports

[root@localhost ~]# netstat -tln
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State      
tcp        0      0 0.0.0.0:111             0.0.0.0:*               LISTEN     
tcp        0      0 0.0.0.0:6000            0.0.0.0:*               LISTEN     
tcp        0      0 192.168.122.1:53        0.0.0.0:*               LISTEN     
tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN     
tcp        0      0 127.0.0.1:631           0.0.0.0:*               LISTEN     
tcp        0      0 127.0.0.1:25            0.0.0.0:*               LISTEN     
tcp6       0      0 :::3306                 :::*                    LISTEN     
tcp6       0      0 :::111                  :::*                    LISTEN     
tcp6       0      0 :::6000                 :::*                    LISTEN     
tcp6       0      0 :::22                   :::*                    LISTEN     
tcp6       0      0 ::1:631                 :::*                    LISTEN     
tcp6       0      0 ::1:25                  :::*                    LISTEN     
[root@localhost ~]# sudo firewall-cmd --add-port=3306/tcp
success
[root@localhost ~]# sudo systemctl stop firewalld

```
关掉防火墙，欧克连上了


### spring boot项目部署

将项目打成jar包，命令
```
mvn clean package
```

构建一个Dockerfile
```
FROM java:8
VOLUME /tmp
ADD asset-api1-0.3.0.jar app.jar
RUN bash -c "touch /app.jar"
EXPOSE 1213
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
```

把Dockerfile和打好的jar包放在当前目录下
```
docker build -t 镜像名 .
```

镜像构造完成后，启动容器
```
docker run -it -p 6666:8080 --name 容器名 镜像名 bash
```
其中-p表示端口映射  6666代表容器暴露在外面的端口   8080是容器里程序跑在了哪个端口
意思就是通过 主机ip:6666 就可以访问到  docker容器里的8080端口

大功告成！









