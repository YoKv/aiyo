## 项目说明
dota游戏分析统计

## 项目结构说明

### aiyo-front-web(vue.js)
发布的网站前端项目

### aiyo-api-web(Vert.x)
网站主页的服务端

### aiyo-admin(node.js 前后端一个项目)
后台管理:缓存管理，一些中间件后台管理的壳子，服务器进程管理（如关闭vertx定时任务）,
客户信息维护,手动启动一次更新

### aiyo-service-business(Spring Boot)
网站的业务服务，如用户，登陆等等

### aiyo-service-data(Spring Boot)
提供dota数据 使用es做查询
数据清洗，数据建模。定时任务处理数据，生成统计数据。提供统计数据的api
大数据技术spark，Hadoop。

### aiyo-services-steam(Vert.x) 基本完成，缺基于MQ的服务调用
核心数据来源，定时任务获取数据

### aiyo-static-base(jar包引用)
一些工具方法，实体类，通用的枚举，异常，结果集

### document
文档
