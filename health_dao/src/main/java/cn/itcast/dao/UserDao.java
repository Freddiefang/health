package cn.itcast.dao;

import cn.itcast.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 通过用户名和用户密码查询用户ID
     * @param username
     * @return
     */
    User findUserByUsernameAndPassword(@Param("username") String username);
}
