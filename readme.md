## 项目说明
dota游戏分析统计

## 项目结构说明
### aiyo-front-admin(vue.js)
后台管理系统前端项目

### aiyo-front-web(vue.js)
发布的网站前端项目

### aiyo-api-admin(SpringBoot)
后台管理的服务端

### aiyo-api-web(SpringBoot)
网站主页的服务端

### aiyo-service-business(Vert.x)
网站的业务服务，如用户，登陆等等

### aiyo-service-data(Vert.x)
数据清洗，数据建模。定时任务处理数据，生成统计数据。提供统计数据的api
大数据技术spark，Hadoop。
### aiyo-services-steam(Vert.x)
核心数据来源，定时任务获取数据

### aiyo-static-base(jar包引用)
一些工具方法，实体类，通用的枚举，异常，结果集

### aiyo-static-tool(jar包引用)
一些中间件开发的封装

### document
文档
