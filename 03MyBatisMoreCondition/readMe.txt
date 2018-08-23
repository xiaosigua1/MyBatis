现在前台有个表单！
表单中有三项条件 来查询 符合条件的学生！
01.根据学生姓名查询
02.根据老师姓名查询
03.根据年级名称查询

针对于上述的情况，我们发现三个查询条件 不在同一个表中！

第一种情况：

  把学生姓名，老师姓名，年级名称封装成一个Map集合
  Map<String.Object>  map=new  HashMap<String,Object>();
  //把三个条件放进map
  map.put("studentName",学生姓名);
  map.put("teacherName",老师姓名);
  map.put("gradeName",年级名称);
  //调用dao层方法
  dao.xxx(map);

  然后去mapper.xml文件中执行sql语句

  select s.name,s.id,s.age  from  student s,teacher t,grade g
  where s.tId=t.id and s.gId=g.id
  and s.name=#{studentName}   //开始使用map中的key
  and t.name=#{teacherName}
  and g.name=#{gradeName}


第二种情况：

   用户可以传递几个参数，我们在方法定义时就书写几个形参！

   在接口中书写方法

   List<Student> selectStduentsByCondition(String stuName,String teacherName,String gradeName);

  在mapper.xml文件中书写sql

 select s.name,s.id,s.age  from  student s,teacher t,grade g
  where s.tId=t.id and s.gId=g.id
  and s.name=#{0}   //开始使用参数的下标
  and t.name=#{1}
  and g.name=#{2}



总结#｛｝中可以存放的内容

01. 当参数是对象的时候，存放的是对象的属性
02. 存放map时的注意点
    001.当参数是map集合时，存放的是map的key
    002.如果map的value是对象时，存放的是对象的属性
03.如果传递一个参数，存放的是占位符
04.如果传递多个参数，存放的是参数对应的下标，从0开始
