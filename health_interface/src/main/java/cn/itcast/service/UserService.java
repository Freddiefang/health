package cn.itcast.service;

import cn.itcast.pojo.Menu;
import cn.itcast.pojo.User;

import java.util.Set;

public interface UserService {
    /**
     * 获取用户名称
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 获取用户需要显示的菜单集合
     * @return
     * @param username
     */
    Set<Menu> getMenuList(String username);

}
