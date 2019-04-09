package com.itheima;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest2 {
    private InputStream in ;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {

        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void  testFindOne(){
        User user = userDao.findById(41);
        System.out.println("user = " + user);
    }


    @Test
    public void testSave(){

        User user = new User();
        user.setUsername("modify User ");
        user.setAddress("北京市顺义区");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存操作之前："+user);
        //5.执行保存方法
        userDao.saveUser(user);
        System.out.println("保存操作之后："+user);

    }

    @Test
    public void testUpdateUser(){
        User user = userDao.findById(52);
        user.setAddress("北京市顺义区");
        int i = userDao.updateUser(user);
        System.out.println("i = " + i);
    }

    @Test
    public void testDeleteUser(){
        int i = userDao.deleteUser(54);
        System.out.println("i = " + i);
    }


    @Test
    public void  testFindByName(){
        //List<User> users = userDao.findByName("%王%");
        List<User> users = userDao.findByName("王");
        for(User user : users){
            System.out.println(user);
        }
    }


    @Test
    public void  testFindTotal(){
        int total = userDao.findTotal();
        System.out.println("total = " + total);

    }

    @Test
    public void  testFindByQueryVo(){
        QueryVo queryVo=new QueryVo();
        User user=new User();
        user.setUsername("%王%");
        queryVo.setUser(user);

        List<User> users = userDao.findByVo(queryVo);
        for (User user1 : users) {
            System.out.println("user1 = " + user1);
        }

    }

}
