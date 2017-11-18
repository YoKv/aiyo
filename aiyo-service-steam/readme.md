
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
第三种方式，利用Java的Atom对象，示例代码（Groovy）如下：