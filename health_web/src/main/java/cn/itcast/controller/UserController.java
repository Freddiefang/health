package cn.itcast.controller;

import cn.itcast.constant.MessageConstant;
import cn.itcast.entity.Result;
import cn.itcast.pojo.Menu;
import cn.itcast.service.MenuService;
import cn.itcast.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;


    //获取当前登录用户的用户名
    @RequestMapping(value = "/getUsernameAndMenuList",method = RequestMethod.POST)
    public Result getUsernameAndMenuList(){
        try {
            //通过Map传输数据
            Map<String,Object> map = new HashMap<>();

            //查询用户的名字
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            map.put("username",user.getUsername());

            //查询菜单集合
            LinkedList<Menu> menuList = userService.getMenuList(user.getUsername());
            map.put("menuList",menuList);

            return new Result(true, MessageConstant.GET_MENU_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_MENU_FAIL);
        }
    }
}
