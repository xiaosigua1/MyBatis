
框架：  io        多线程   网络编程
       数据库     jdbc     servlet
持久化技术：   io   序列化     数据库

MyBatis：是一个持久层的框架！ 它内部封装了jdbc技术！
          我们只关注sql语句的书写！
          不需要再话费精力去关注加载驱动，获取连接等操作！但是需要配置在xml文件中！


   前身：ibatis！ 我们使用mybatis的时候，底层的包 还叫ibatis！
      2013年源码上传了github


Hibernate是一个持久层全自动的ORM框架！     实体类的映射
可以不写sql语句，我们按照面向对象的思想来解决数据库中的问题！


Mybatis:持久层半自动的ORM框架！支持定制化sql！   sql语句的映射
ibatis的前身！


中文网址：http://www.mybatis.org/mybatis-3/zh/index.html

github源码：https://github.com/mybatis/mybatis-3/tree/master/src/main/java/org/apache/ibatis

 和Hibernate一样 都是 持久层的框架！

 Hibernate是一个ORM映射的持久层的框架！

 O： object
 R： relation
 M： mapping


区别：

  01.Hibernate是一个全自动完全面向对象的持久层框架！底层需要把我们对对象的操作转换成sql语句之后再执行！
     MyBatis是一个半自动的持久层框架！需要我们程序员手动书写sql语句！
  02.Hiberntae不能处理复杂的sql语句！
     MyBatis可以操作复杂的sql!
  03.我们可以把sql语句放进xml文件中，和我们的java代码相分离！便于我们后期维护！
  04.MyBatis简单易学，便于使用！




SqlSessionFactoryBuilder
01.加载核心配置文件！
02. 作用域放在方法体内！
03.一旦创建了SqlSessionFactory，这个SqlSessionFactoryBuilder就没用！

这个类可以在任何时候被实例化、使用和销毁。一旦创造了 SqlSessionFactory 就不需要
再保留它了。所以 SqlSessionFactoryBuilder 实例的最好的作用域是方法体内（即一个本地方法
变量）。能重用 SqlSessionFactoryBuilder 创建多个 SqlSessionFactory 实例，但最好不要把
时间、资源放在解析 XML 文件上，而是要从中解放出来做最重要事情。



SqlSessionFactory
01.SqlSession的会话工厂
02.在整个应用程序中  只存在一个对象即可！  单例！

一旦创建，SqlSessionFactory 将会存在于应用程序整个运行生命周期中。很少或根本
没有理由去销毁它或重新创建它。最佳实践是不要在一个应用中多次创建 SqlSessionFactory。
这样做会被视为“没品味”。所是 SqlSessionFactory 最好的作用域范围是一个应用的生命周期
范围。这可以由多种方式来实现，最简单的方式是使用 Singleton 模式或静态 Singleton 模式。
这些框架允许您创造一个管理器，用于管理 SqlSessionFactory 的生命周期。


SqlSession
01.增删改查的真正执行者！
02.不允许在线程中共享！
03.确保会话结束后，关闭会话！ 在finally{}中关闭！

每个线程都有一个 SqlSession 实例，SqlSession 实例是不被共享的，并且不是线程安全
的。因此最好的作用域是 request 或者 method。决不要用一个静态字段或者一个类的实例字段来
保存 SqlSession 实例引用。也不要用任何一个管理作用域，如 Servlet 框架中的 HttpSession，
来保存 SqlSession 的引用。如果您正在用一个 WEB 框架，可以把 SqlSession 的作用域看作类似
于 HTTP 的请求范围。也就是说，在收到一个 HTTP 请求，您可以打开一个 SqlSession，当您把
response 返回时，就可以把 SqlSession 关闭。关闭会话是非常重要的，您应该要确保会话在一
个 finally 块中被关闭。



使用Idea 开发第一个myBatis项目
01.创建maven项目
02.配置 pom文件  引入需要的jar
03.在src目录下的main下 创建 source文件  并把文件设置成 resouce文件
04.从文档中copy 核心配置文件 进行书写
    001.jdbc.properties
    002.sql语句所在的mapper.xml文件
    003.mapper.xml文件必须放在resource文件夹中
05.创建我们的接口 书写方法
06.创建需要的实体类 以及mapper.xml文件
    需要注意的是mapper.xml文件的根节点是 mapper 需要一个属性叫namespace
07.书写测试类 运行








需要的sql脚本


DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;




# 和 $的区别

01.$ 不安全 底层实现是statement对象

    select * from student  where id=${id}
    如果我们id传入的是11    编译之后
    select * from student  where id=11


   # 安全   底层实现是PreparedStatement对象
    select * from student  where id=#{id}
    如果我们id传入的是11    编译之后
    select * from student  where id=?

02.在sql语句需要排序的时候
     order  by  ${id}
     只有在需要排序的时候 使用$ .
     其他时候能用#绝对不用$









