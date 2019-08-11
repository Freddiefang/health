package cn.itcast.service.impl;

import cn.itcast.dao.MenuDao;
import cn.itcast.dao.PermissionDao;
import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import cn.itcast.pojo.Menu;
import cn.itcast.pojo.Permission;
import cn.itcast.pojo.Role;
import cn.itcast.pojo.User;
import cn.itcast.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private MenuDao menuDao;

    @Override
    public User findUserByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        if(user == null){
            return null;
        }
        Set<Role> roles = roleDao.findRoleByUserId(user.getId());
        if(roles == null || roles.size() == 0){
            return user;
        }
        user.setRoles(roles);
        for (Role role : roles) {
            Set<Permission> permissions = permissionDao.findPermissionByRoleId(role.getId());
            role.setPermissions(permissions);
        }
        return user;
    }

    /**
     * 获取用户需要显示的菜单集合
     * @return
     * @param username
     * @param password
     */
    @Override
    public Set<Menu> getMenuList(String username, String password) {
        LinkedHashSet<Menu> menus = new LinkedHashSet<>();
        //通过用户名和用户密码查询用户ID
        User user = userDao.findUserByUsernameAndPassword(username,password);
        //通过用户ID查询角色ID
        Integer userId = user.getId();
        Set<Role> roleSet = roleDao.findRoleByUserId(userId);
        if(roleSet != null){
            for (Role role : roleSet) {
                Integer roleId = role.getId();
                //通过角色ID查询，角色所具有的菜单选项
                List<Integer> menuIdList = menuDao.findMenuIdByRoleId(roleId);
                if (menuIdList != null && menuIdList.size() > 0){
                    for (Integer menuId : menuIdList) {
                        //查询菜单项
                        Menu menu = menuDao.findMenuByMenuId(menuId);
                        menus.add(menu);
                    }
                }
            }
        }
        return menus;
    }


}
