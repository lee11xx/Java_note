==12.21==

1.内网互通原则

关闭防火墙。（linux windows） 

linux windows 手机，连同一个wifi和频段（192.168.1.x）

请求地址不是 localhost

-------

技术架构图 功能

------

数据库选型：MariaDB / MySQL 5.7

----

分包分模块处理。



==12.22==

目前感觉还是不错，很多地方用到了更新的东西（插件）。

比如验证字段 Validation、实体类 日志 Lombok

------

异常处理用切面统一处理。参数校验出来的异常也用切面处理，精简代码。

----

Redis 作计数。如果用 MySQL，比如统计粉丝数量，一个人有百万粉丝，影响性能。



==12.23==

分布式存储技术选型：支持重命名，安装操作简单，可扩展性（erlang，python）。

用 minIO 实现上传文件功能。

https://www.cnblogs.com/fangpengchengbupter/p/7826565.html

---------

首页显示，搜索后的显示（SQL都是查询，后者需要用到模糊查询）。

----

前端相关：

保存到相册，复制链接，

二维码的设计：扫码后获得 vlogId…，然后调用相关接口。

------

> 评价

前端内容有点多。虽然也学到了前端，却背离了学习这套课程的主要目的。 io,nio,aio的比较，对比，实例部分，优缺点实例部分 ，想多看看的，但是没有。 整个课程netty部分就是大概讲了下概念，结合了下websocket。其实铺的并不开。只局限在了这个场景里。为了实现这么个聊天工具，还是有所得失的。 包括真的微信方面有更多后端值得注意的点，其实我更想听这部分铺出来。

吗耶，都是在讲前端，整个课程后端不到三分之一，更不用说netty了，本来就是来学习netty的，没什么用。

至少要提供一些学习资源吧



## 整体框架

背景：短视频在未来都是风口，这一块整体的蛋糕很大。

参考小红书和抖音去做的。

跨端跨平台的 uniapp（前端）+ Java（后端）

`前后端分离的开发模式`

-----

功能模块

视频，消息，留言，粉丝，用户

----

![image-20221224160222768](https://cdn.jsdelivr.net/gh/lee11xx/picgo-img/typora/202212241602615.png)

### 前端

![image-20221224160917545](https://cdn.jsdelivr.net/gh/lee11xx/picgo-img/typora/202212241609625.png)

前后端分离的开发模式。

好处：各部门并行开发，后端代码一套就通用了。

![image-20221224161224004](https://cdn.jsdelivr.net/gh/lee11xx/picgo-img/typora/202212241612121.png)

----

uniapp 实现跨平台，一套代码开发更多应用。

以往 h5，ios，Android 都要分别招一个人，现在如果是项目初期，可以减少到一个人的用人成本。（中小型，初创公司试水的时候去用）。



### 后端

#### 用户模块



#### 短视频业务模块

1.传统上传流程

![image-20230124112339800](https://cdn.jsdelivr.net/gh/lee11xx/picgo-img/typora/202301241123806.png)

问题

![image-20230124112658940](https://cdn.jsdelivr.net/gh/lee11xx/picgo-img/typora/202301241126053.png)

2.CDN 上传流程

![image-20230124112945615](https://cdn.jsdelivr.net/gh/lee11xx/picgo-img/typora/202301241129677.png)

对比

![image-20230124112915569](https://cdn.jsdelivr.net/gh/lee11xx/picgo-img/typora/202301241129655.png)







































## 项目记录

### month 1

2 + 2 + 2 + 4 = 10

#### 12.24-12.25

==12.24==

内网互通原则

关闭防火墙。（linux windows） 

linux windows 手机，连同一个wifi和频段（192.168.1.x）

请求地址不是 localhost



==12.25==

解决 bug。HBuilder 安装手机基座失败。

重启手机即可。与远程控制程序无关。

内网互通，连接热点一样可以电脑和手机互通，没必要非得都是192.168.22.x。

**留坑** app.vue 的 ip 写的 Windows 网卡上的，不是连接热点的 ip，可能需要改。

----

一些后端开发工具，软件。

----

user 表 id 是 varchar，后续分库分表要用字符串。

使用弱外键（物理外键，查询慢。删除表耦合度大）

> 数据库表的设计还是没看太懂。等功能做完了再看一遍

----

分模块管理。

记得 install



#### 12.26-1.1

==12.26==

添加 包之间的依赖关系：com.api -> com.service -> com.mapper -> com.model -> com.common

直接 copy 文件内容导致的 bug

https://www.cnblogs.com/kingsonfu/p/10342222.html



==12.27==

逆向生成工具 生成 pojo，mapper 接口，mapper.xml。

整合 mybatis：pom.xml 添加相关依赖，application.yml 配置信息，启动类 Application.java 添加包扫描。

bug：逆向生成工具再生成一次，copy 到对应的包，解决。

postman 和 knife4j，采用后者调试接口，实现接口文档。

----

SpringBoot 整合腾讯云验证码



#### 1.2-1.8

==1.4==

内网互通留下来的问题。

校园网本身的设置就是 ip 隔离，可以用电脑连校园网，开热点，手机连这个热点。

受启发，也可以手机开热点，电脑连热点。

两者都需要在前端把服务端（后端）ip 改成现在热点的 ip。

cmd：ipconfig，里面每个 ip 都可以访问到本电脑的后端代码。

----

Linux 安装了 Redis，位置：/usr/local

Redis passs: lxc010409

Redis 配置文件修改 bind 0.0.0.0 反而连接不上了，还是不动。



==1.5==

Redis 限制单个 ip 60秒发一次验证码，并存验证码。

> rdm 连接不上 Redis 服务器，把 vpn 代理先关掉就可以了。

----

优雅地封装异常。

----

为了后续的分库分表，要有一个全局的主键 id。

自增主键无法保证这一点。



#### 1.9 - 1.15

==1.10==

实现用户基本信息查询，每次点个人主页都会刷新粉丝数，点赞数，获赞数。

> 编辑资料更新了个人信息，但是 updated_time 没有修改。有空填坑。



==1.11==

分布式存储结构，分布式对象存储。

分布式技术选型：安装简单和快，迁移成本，支持自定义文件命名（重命名），社区活跃度，维护成本，是否商用

----

minIO ：启动脚本 ./start-minio.sh



==1.14==

minIO 新版本分享的图片访问不了，降个版本。

历史版本下载链接：https://dl.min.io/server/minio/release/

----

picgo 上传图片失败：github 生成的 token 没有勾选 repo 权限。



==1.15==

学 docker，确实是个不错的工具。

从 docker 下稳定版的 minIO，官网没找到，只有最新的很蛋疼。



### month 2

3 + 

#### 1.16 - 1.22

==1.16==

docker 安装稳定版minIO。

https://juejin.cn/post/6988340287559073799#heading-8

----

充电线的问题，新买的充电线用手机连电脑连不上，换老的可以。

整合 minIO 和 SpringBoot，测试接口，上传图片成功。



==1.17==

`$ git push origin main
fatal: unable to access 'https://github.com/lee11xx/Java_note.git/': Failed to connect to github.com port 443 after 21144 ms: Timed out`

https://blog.csdn.net/Hodors/article/details/103226958

疑问：

```java
public Users updateUserInfo(UpdatedUserBO updatedUserBO) {
    // 因为是个 BO 对象，要做一个值拷贝到 User 对象
    Users pendingUser = new Users();
    BeanUtils.copyProperties(updatedUserBO, pendingUser);

    int result = usersMapper.updateByPrimaryKeySelective(pendingUser);
    if (result != 1) {
        GraceException.display(ResponseStatusEnum.USER_UPDATE_ERROR);
    }

    return getUser(updatedUserBO.getId());
}
```



==1.19==

dockerhub 上搜 minio 出来都不是官方的，也没找到官方认证的镜像。

还是参考了一下别人的博客，才知道下错了。

https://blog.csdn.net/weixin_43888891/article/details/122021704

docker search minio 和 dockerhub 上搜 minio 得出来的结果不一样。前者是 stars 排序，而后者是下载排序，还是用前者吧，被坑了。

> 还是多参考博客吧，给官网整死了。

`共享文件时，生成的链接访问不了`

```c++
docker run -p 9000:9000 -p 9090:9090 \
 --net=host \
 --name minio \
 -d --restart=always \
 -e "MINIO_ACCESS_KEY=username" \
 -e "MINIO_SECRET_KEY=password123456" \
 -e "MINIO_SERVER_URL= http://宿主机ip:宿主机映射的9000端口" \
 -v /data:/data \
 minio/minio server \
 /data --console-address ":9090" -address ":9000"
```

```c++
docker run -p 9000:9000 -p 9111:9111 \
 --name minio \
 -d --restart=always \
 -e "MINIO_ACCESS_KEY=lee" \
 -e "MINIO_SECRET_KEY=lxc010409" \
 -e "MINIO_SERVER_URL=http://192.168.0.111:9000" \
 -v /usr/local/minio/data:/data \
 minio/minio server \
 /data --console-address ":9111" -address ":9000"
```

----

最后回到原点，能上传图片和修改用户头像、背景的 URL，但是访问不了，还是透明图片。

应该是因为 minio 上传图片的 api，调用后生成的分享链接是一个下载链接，新版本也预览不了。



#### 1.23 - 1.29

==1.23==

内网互通原则。

前端没问题，手机能显示头像和背景的图片。但是访问不到 centos 的地址，故而也访问不到 minio 生成的图片链接。

> centos7 链接 wifi

走不通，达到目的就行。故而转换问题：如何让手机和 centos 在同一网段，可以网络配置改为桥接模式，勾选 复制物理连接网络状态。



==1.24==

修改头像和背景 成功。

限制上传的文件大小。

完结 用户业务模块。

----

短视频业务模块。













