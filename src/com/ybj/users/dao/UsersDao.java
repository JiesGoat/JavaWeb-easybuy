package com.ybj.users.dao;

import com.ybj.entity.Users;
import com.ybj.util.BaseDao;
import com.ybj.util.PageUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 用户数据层
 */
public class UsersDao extends BaseDao {
    Scanner input = new Scanner(System.in);

    /**
     * 向users表中增加一条记录
     */
    public void insert() {
        System.out.println("请输入一行的值（用户编号，用户名称，用户密码，性别，出生日期，身份证，邮箱，手机号，地址，状态）：");
        Object[] obj = new Object[10];
        int i = 0;
        while (i < 10) {
            obj[i] = input.next();
            i++;
        }
        //第一步：加载驱动 第二步：获取连接对象
        conn = getConnection();
        //第三步：获取执行对象 第四步：获取结果（受影响的行数）
        String sql = "INSERT INTO users(uid, uname, upassword, sex, birthday, ucode, email, mobile, address, ustatus) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int row = executeUpdate(sql, obj);
        if (row != 0) {
            System.out.println("插入编号为" + obj[0] + "的记录成功！");
        } else {
            System.out.println("插入失败！");
        }
    }

    /**
     * 删除user表中的一行数据
     */
    public void delete() {
        System.out.print("请输入要删除的用户的编号：");
        String _uid = input.next();
        Object[] obj = {_uid};
        //第一步：加载驱动  第二步：获取连接对象
        conn = getConnection();
        //第三步：获取执行对象    第四步：获取结果（受影响的行数）
        String sql = "DELETE FROM users WHERE uid = ?;";
        int row = executeUpdate(sql, obj);
        //第五步：输出结果
        if (row != 0) {
            System.out.println("删除编号：" + _uid + "成功！");
        } else {
            System.out.println("删除失败！");
        }
    }

    /**
     * 查询users表里面的所有数据
     */
    public List<Users> selectAll() {
        List list = new ArrayList();
        Object[] obj = null;
        Users users;
        try {
            //第三步：获取执行对象    第四步：获取结果（结果集）
            String sql = "SELECT uid, uname, upassword, sex, birthday, ucode, email, mobile, address, ustatus FROM users;";
            rs = executeQuery(sql, obj);
            //第五步：输出结果
            while (rs.next()) {
                users = new Users();
                //System.out.println(uid + "\t" + uname + "\t" + upassword + "\t" + sex + "\t" + birthday + "\t" + ucode + "\t"
                //+ email + "\t" + mobile + "\t" + address + "\t" + ustatus);
                users.setUid(rs.getString("uid"));
                users.setUname(rs.getString("uname"));
                users.setUpassword(rs.getString("upassword"));
                users.setSex(rs.getString("sex"));
                users.setBirthday(rs.getDate("birthday"));
                users.setUcode(rs.getString("ucode"));
                users.setEmail(rs.getString("email"));
                users.setMoblile(rs.getString("mobile"));
                users.setAddress(rs.getString("address"));
                users.setUstatus(rs.getInt("ustatus"));
                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * 查询users表一个的分页
     *
     * @param pu 要查询的分页
     * @return 用户页
     */
    public List<Users> selectAll(PageUtil pu) {
        List list = new ArrayList();
        Object[] obj = {(pu.getNow() - 1) * pu.getPageSize(), pu.getPageSize()};
        Users users;
        try {
            //第三步：获取执行对象    第四步：获取结果（结果集）
            String sql = "SELECT uid, uname, upassword, sex, birthday, ucode, email, mobile, address, ustatus FROM users LIMIT ?, ?;";
            rs = executeQuery(sql, obj);
            //第五步：输出结果
            while (rs.next()) {
                users = new Users();
                users.setUid(rs.getString("uid"));
                users.setUname(rs.getString("uname"));
                users.setUpassword(rs.getString("upassword"));
                users.setSex(rs.getString("sex"));
                users.setBirthday(rs.getDate("birthday"));
                users.setUcode(rs.getString("ucode"));
                users.setEmail(rs.getString("email"));
                users.setMoblile(rs.getString("mobile"));
                users.setAddress(rs.getString("address"));
                users.setUstatus(rs.getInt("ustatus"));
                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * 修改users表中的用户密码
     */
    public void update() {
        System.out.print("请输入用户编号：");
        String _uid = input.next();
        System.out.print("\n请输入新密码:");
        String _upassword = input.next();
        Object[] obj = {_upassword, _uid};
        //第一步：加载驱动  第二部：获取连接对象
        conn = getConnection();
        //第三步：获取执行对象 第四步：获取结果（受影响的行数）
        String sql = "UPDATE users SET upassword = ? WHERE uid = ?;";
        int row = executeUpdate(sql, obj);
        //第五步：输出结果
        if (row != 0) {
            System.out.println("修改编号" + _uid + "密码成功！");
        } else {
            System.out.println("修改失败！");
        }
    }

    /**
     * 通过输入的uid查询用户实体
     *
     * @param uid 用户登录输入的uid
     * @return 返回查询到的用户实体
     */
    public Users selectById(String uid) {
        Users users = null;
        //1.定义SQL语句
        String sql = "SELECT uid, uname, upassword, sex, birthday, ucode, email, mobile, address, ustatus FROM users WHERE uid = ?;";
        //2.组装数组参数
        Object[] obj = {uid};
        //3.调用方法接受返回结果
        rs = executeQuery(sql, obj);
        //4.返回结果
        try {
            if (rs.next()) {
                //创建对象
                users = new Users();
                //设置值
                users.setUid(rs.getString("uid"));
                users.setUname(rs.getString("uname"));
                users.setUpassword(rs.getString("upassword"));
                users.setSex(rs.getString("sex"));
                users.setBirthday(rs.getDate("birthday"));
                users.setUcode(rs.getString("ucode"));
                users.setEmail(rs.getString("email"));
                users.setMoblile(rs.getString("mobile"));
                users.setAddress(rs.getString("address"));
                users.setUstatus(rs.getInt("ustatus"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return users;
    }

    /**
     * 获得用户列表总行数
     *
     * @return 用户列表总行数
     */
    public int getCount() {
        int row = 0;
        String sql = "SELECT count(uid) FROM users;";
        Object[] obj = null;
        rs = executeQuery(sql, obj);
        try {
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return row;
    }

    /**
     * 尝试向users表中插入一条用户信息
     *
     * @param users 要插入的用户
     * @return 返回改变的行数
     */
    public int insertUser(Users users) {
        String sql = "INSERT INTO users(uid, uname, upassword, sex, birthday, ucode, email, mobile, address, ustatus) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {users.getUid(), users.getUname(), users.getUpassword(), users.getSex(), users.getBirthday(), users.getUcode(),
                users.getEmail(), users.getMoblile(), users.getAddress(), users.getUstatus()};
        int row = executeUpdate(sql, obj);
        return row;
    }

    /**
     * 尝试在users表中删除一条用户信息
     *
     * @param users 要删除的用户
     * @return 返回改变的行数
     */
    public int deleteUser(Users users) {
        String sql = "DELETE FROM users WHERE uid = ?;";
        Object[] obj = {users.getUid()};
        int row = executeUpdate(sql, obj);
        return row;
    }

    /**
     * 尝试在users表中更新一条信息
     *
     * @param users 要更新的用户（用户的uid和ucode不能更新）
     * @return 返回改变的行数
     */
    public int updateUser(Users users) {
        String sql = "UPDATE users SET uname = ?, upassword = ?, sex = ?, birthday = ?, email = ?, mobile = ?, address = ?" +
                "WHERE uid = ?;";
        Object[] obj = {users.getUname(), users.getUpassword(), users.getSex(), users.getBirthday(), users.getEmail(),
                users.getMoblile(), users.getAddress(), users.getUid()};
        int row = executeUpdate(sql, obj);
        return row;
    }

}
