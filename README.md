#d-mock
在调用方法时用自己定义的mockBean返回给服务。

##1.目前用到的包
mock-bean-core: 核心包，存放注解、http请求工具、mockBean的创建类  
mock-bean-dao:  数据库生成工具包，参考mybatis-generator  
mock-bean-server: mockBean的服务端代码
mock-bean-app: mockBean的客户端调用example

##2.项目使用
2.1 数据库运行sql语句，在server、app、dao中修改库名、用户名、密码等  
2.2 在数据库中插入mockBean的内容。（app_name:应用名，分配给调用方。bean_id:bean的id,确保数据库里面的和mockBean注解的value一致。
bean_name:目前不校验。class_name:mockBean所属的class。method_name:mock的方法名。method_val:mock的返回值 json格式）  
2.3 调用方配置mockBean配置文件，包括address,host,appName  
2.4 调用方创建@Bean对象: MockBeanAop  
2.5 运行server  
2.6 运行app

##3.todo
1. mockBean界面配置化  
2. http链接，响应非常的慢  
3. ...  
