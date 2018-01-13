
## 介绍
aiyo-service-steam 重构项目



基础模块：（TODO）
* 单元测试
* 日志
* aop
* 调用链监控




orm框架
vertx-jooq





一些vert.x参考


Launcher，程序入口，负责调起Vert.x的环境。
MainVerticle，主Verticle，负责部署程序中其他的Verticle。
Verticle，程序处理逻辑，调用其他POJO/POGO。
POJO/POGO，普通类，供Verticle使用。


部署Verticle
跟Servlet类似，多个Verticle之间也会有依赖关系，存在先后部署的需要。

对于单个Verticle之间的依赖，如A依赖B，很简单，利用deployVerticle的回调就很好解决。因为代码简单，这里就不再单独列出，还是那句话，看文档。

对于依赖多个Verticle，如A依赖B和C，则需要有点技巧了：

第一也是最差的方式，就是采用callback hell方式，层层递进。
第二种方法采用rxJava，利用Observable的运算来完成。
第三种方式，利用Java的Atom对象，Atomic包



Vert.x RxJava是一个非常受欢迎的响应式编程扩展程序包，
3.4.X版本增强了对RxJava的支持，原本返回Observable的API全部更改为返回rx.Single，使其语义更加清晰