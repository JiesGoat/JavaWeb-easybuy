package com.ybj.users.service;

import com.ybj.entity.Users;
import com.ybj.users.dao.UsersDao;
import com.ybj.util.PageUtil;

import java.util.List;

/**
 * 用户业务层
 */
public class UsersService {

    //调用数据层
    UsersDao ud = new UsersDao();

    /**
     * 用户登录是否成功
     * @param users 用户实体
     * @return 如果存在返回该对象，否则返回null;
     */
    public Users isLogin(Users users) {
        //接受数据返回类型 users：用户输入
        Users u = ud.selectById(users.getUid());
        //判断密码是否相等
        if (u != null) {
            if (u.getUpassword().equals(users.getUpassword())) {
                return u;
            }
        }
        return null;
    }

    /**
     * 查询所有用户信息
     */
    public void queryAll() {
        List list = ud.selectAll();
        System.out.println(list.size());
    }

    /**
     * 新增用户信息（注册和新后台新增）
     * @param users 用户实体
     * @return 增加成功返回true，失败返回false
     */
    public boolean insertUser(Users users) {
        boolean flag = false;
        int row = ud.insertUser(users);
        if (row != 0) {
            flag = true;
        } else {
            System.out.println("编号重复！");
        }
        return flag;
    }

    /**
     * 删除用户信息
     * @param users 用户实体
     * @return 删除成功返回true，失败返回false
     */
    public boolean deleteUser(Users users) {
        boolean flag = false;
        int row = ud.deleteUser(users);
        if (row != 0) {
            flag = true;
        } else {
            System.out.println("删除失败！");
        }
        return flag;
    }

    /**
     * 更新用户信息
     * @param users 用户实体
     * @return 更新成功返回true，失败返回false
     */
    public boolean updateUser(Users users) {
        boolean flag = false;
        int row = ud.updateUser(users);
        if (row != 0) {
            flag = true;
        } else {
            System.out.println("更新失败！");
        }
        return flag;
    }

    /**
     * 获取用户分页
     * @param index 分页起始下标
     * @return 用户分页
     */
    public PageUtil queryPage(int index) {
        //获取总行数
        int count = ud.getCount();
        //分页
        PageUtil pu = new PageUtil(count, index, 4);
        //每页显示的数据
        List list = ud.selectAll(pu);
        pu.setList(list);
        return pu;
    }

}
