package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户
     * @return
     */
    //@Select("select * from user ")
    List<User> findAll();


    User findById(Integer userId);

    int saveUser(User user);

    int updateUser(User user);

    int deleteUser(Integer userId);


    List<User> findByName(String username);

    int findTotal();

    List<User> findByVo(QueryVo vo);





}
