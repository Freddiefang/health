package cn.itcast.dao;

import cn.itcast.pojo.Menu;

import java.util.List;

public interface MenuDao {

    /**
     * 通过角色ID查询，角色所具有的菜单选项
     * @param roleId
     * @return
     */
    List<Integer> findMenuIdByRoleId(Integer roleId);

    /**
     * 查询菜单项
     * @param menuId
     * @return
     */
    Menu findMenuByMenuId(Integer menuId);
}
