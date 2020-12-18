package com.example.wechating;

import com.example.wechating.domain.Friends;
import com.example.wechating.domain.User;
import com.example.wechating.utils.JDBCUtils;
import com.example.wechating.utils.UserUtil;

import org.junit.Test;
import org.litepal.LitePal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.wechating.utils.JDBCUtils.findAll;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testFindAll() throws Exception {
        List<User> users=new ArrayList();//测试findAll方法
        users= UserUtil.findAllUser();

        for(User e:users)
            System.out.println(e);
    }

    //测试添加用户方法
    @Test
    public void testAddUser() throws SQLException {
        User user=new User("txl3","123");
        int res= JDBCUtils.addUser(user);
        if(res==1)
            System.out.println("添加成功");
        else if(res==0)
            System.out.println("添加失败");
    }

    @Test
    public void test2(){
        Friends f=new Friends("haha");
        System.out.println(f.toString());
    }
}