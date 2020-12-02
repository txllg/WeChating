package com.example.wechating.utils;


import com.example.wechating.domain.User;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by txllg on 2020/11/29.
 */

public class JDBCUtils {

    public static Connection getConnection(){
        final Connection[] conn = new Connection[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                //获取mysql数据库连接
                try{//加载MySql的驱动类
                    Class.forName("com.mysql.jdbc.Driver") ;
                }catch(ClassNotFoundException e){
                    System.out.println("找不到驱动程序类 ，加载驱动失败！");
                    e.printStackTrace() ;
                }

                String url = "jdbc:mysql://localhost:3306/android?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8" ;
                String username = "root" ;
                String password = "root" ;
                try{
                    conn[0] = DriverManager.getConnection(url , username , password ) ;
                }catch(SQLException se){
                    System.out.println("数据库连接失败！");
                    se.printStackTrace() ;
                }
            }
        }).start();
        return conn[0];
    }

    public static int addUser(User user) throws SQLException {//添加用户
        List<User> users=new ArrayList();
        users=findAll();
        String username=user.getUsername();
        String password=user.getPassword();

        for(User e:users){
            if(username.equals(e.getUsername()))
                return 0;//用户名已经存在，添加失败
        }

        Connection conn=getConnection();
        PreparedStatement pst=conn.prepareStatement("insert into user(`username`,`password`) values(?,?)");
        pst.setString(1,username);
        pst.setString(2,password);
        pst.executeUpdate();
        return 1;//添加成功
    }

    public static List<User> findAll() throws SQLException {
        List<User> users=new ArrayList();

        Connection conn=getConnection();
        System.out.println("现在测试connection的值是否为空:"+conn);
        Statement stmt = conn.createStatement() ;
        ResultSet resultSet=stmt.executeQuery("select * from User");
        users= (List<User>) resultToList(resultSet,User.class);//将resultSet转化为List
        return users;
    }

    //resultToList方法
    public static List<User> resultToList(ResultSet resultSet, Class clazz) {
        //创建一个 T 类型的数组
        List<User> list = new ArrayList<>();
        try {
            //获取resultSet 的列的信息
            ResultSetMetaData metaData = resultSet.getMetaData();
            //遍历resultSet
            while (resultSet.next()) {
                //遍历每一列
                //通过反射获取对象的实例
                User t = (User)clazz.newInstance();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    //获取列的名字
                    String fName = metaData.getColumnLabel(i + 1);

                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        if (fName.equals(field.getName())) {
                            Object obj = resultSet.getObject(fName);
                            field.setAccessible(true);
                            //可以根据自己的需求添加几个
                            if (field.getType().getName().equals("java.lang.Integer")) {
                                field.set(t, Integer.parseInt(String.valueOf(obj)));
                            }
                            if (field.getType().getName().equals("java.lang.String")) {
                                field.set(t, String.valueOf(obj).equals("null") ? " ": String.valueOf(obj));
                            }
                            field.setAccessible(false);
                        }
                    }
                }
                //把赋值后的对象 加入到list集合中
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回list
        return list;
    }


}
