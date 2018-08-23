Mapper动态代理

之前我们使用JDBC操作数据库
01.StudentDao   增删改查的接口
02.StudentDaoImpl   真正实现增删改查的类  有sql语句


现在我们使用mybatis
01.01.StudentDao         增删改查的接口
02.StudentDaoImpl        获取session和sql语句
03.StudentMapper.xml     有sql语句  真正实现增删改查


针对于上面我们的分析，mybatis框架自身就抛开了DaoImpl,
让我们可以直接定位到Mapper.xml文件中的sql语句！
我们就可以通过sql来操作DB！
这种实现方式 我们称之为Mapper动态代理！


怎么实现这种Mapper动态代理呢？
01.删除StudentDaoImpl

02.修改StudentMapper.xml文件中mapper节点的namespace
   为StudentDao接口的完整限定名

03.把StudentMapper.xml文件中所有节点的id
   改变成 StudentDao接口中的方法名
   这样就可以让我们接口中的方法和mapper文件中的sql产生映射关系

04.在需要使用的时候
  　之前用 session.insert(),update(),delete(),selectList().....
    现在用 session.getMapper(StudentDao.class)
    因为底层使用的是反射机制！我们在使用的时候,
    务必要传递接口的class对象！这样在类加载的时候，
    我们就可以得到StudentDao接口中所有的方法名称！
    又因为mapper.xml文件中的节点id和方法同名！
    所以我们在调用StudentDao接口中方法的时候，
    底层会默认调用mapper.xml文件中的执行节点中的sql语句！











