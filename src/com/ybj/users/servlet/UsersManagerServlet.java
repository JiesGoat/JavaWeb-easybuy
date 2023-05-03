package com.ybj.users.servlet;

import com.ybj.entity.Users;
import com.ybj.users.dao.UsersDao;
import com.ybj.users.service.UsersService;
import com.ybj.util.ConvertDate;
import com.ybj.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户后台控制器
 * 1.继承HttpServlet 导包
 * 2.重写doGet doPost
 * 3.配置web.xml web->WEB-INF->lib
 */
public class UsersManagerServlet extends HttpServlet {

    //服务层对象
    UsersService us = new UsersService();
    //数据层对象
    UsersDao ud = new UsersDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "user.jsp";
        //获取状态
        Users users = new Users();
        String status = req.getParameter("status");
        if (status == null) {


        } else { //不为空才进入


            if (status.equals("usersPage")) {           //------------分页查询
                //默认为第一页
                int index = 1;
                String str = req.getParameter("index");
                if (str != null) {
                    index = Integer.valueOf(str);
                }
                PageUtil pu = us.queryPage(index);
                if (pu == null) {
                    System.out.println("pu == null");
                }
                //设置对分页象
                req.getSession().setAttribute("pu", pu);
                url = "manager/user.jsp";


            } else if (status.equals("userAdd")) {      //------------新增用户
                System.out.println("222---新增用户");
                //从新增用户页面得到用户实体
                //获取表单数据
                users.setUid(req.getParameter("uid"));
                users.setUname(
                        new String(req.getParameter("uname").getBytes("iso-8859-1"), "utf-8"));
                users.setUpassword(req.getParameter("passWord"));
                users.setSex(
                        new String(req.getParameter("gender").getBytes("iso-8859-1"), "utf-8"));
                try {
                    users.setBirthday(
                            new Date(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("birthday")).getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                users.setUcode(req.getParameter("identityCode"));
                users.setEmail(req.getParameter("email"));
                users.setMoblile(req.getParameter("mobile"));
                users.setAddress(
                        new String(req.getParameter("address").getBytes("iso-8859-1"), "utf-8"));
                users.setUstatus(0);//普通用户

                if (us.insertUser(users)) {
                    url = "manager/user.jsp";
                } else {
                    url = "manager/user-add.jsp";
                }


            } else if (status.equals("initModify")) {           //------------初始化要更新的用户，进入users-modify.jsp
                users = ud.selectById(req.getParameter("uid"));
                req.getSession().setAttribute("modifyInitUser", users);
                url = "manager/user-modify.jsp";


            } else if (status.equals("userModify")) {           //------------更改用户信息
                //从更新用户信息页面得到用户实体
                //获取表单数据
                users = ud.selectById(req.getParameter("uid"));
                if (req.getParameter("uname") != null) {
                    users.setUname(
                            new String(req.getParameter("uname").getBytes("iso-8859-1"), "utf-8"));
                }
                if (req.getParameter("passWord") != null) {
                    users.setUpassword(req.getParameter("passWord"));
                }
                if (req.getParameter("gender") != null) {
                    users.setSex(
                            new String(req.getParameter("gender").getBytes("iso-8859-1"), "utf-8"));
                }
                if (req.getParameter("birthday") != null) {
                    users.setBirthday(ConvertDate.strToDate(req.getParameter("birthday"), 1));
                }
                if (req.getParameter("email") != null) {
                    users.setEmail(req.getParameter("email"));
                }
                if (req.getParameter("mobile") != null) {
                    users.setMoblile(req.getParameter("mobile"));
                }
                if (req.getParameter("address") != null) {
                    users.setAddress(
                            new String(req.getParameter("address").getBytes("iso-8859-1"), "utf-8"));
                }
                if (us.updateUser(users)) {
                    System.out.println("users" + users.getUid() + "更新成功！");
                    url = "manager/user.jsp";
                } else {
                    url = "manager/user-modify.jsp";
                }


            } else if (status.equals("del")) {               //------------删除新闻信息
                users = ud.selectById(req.getParameter("uid"));
                System.out.println("del = " + ud.selectById(req.getParameter("uid")));
                Object adminObj = req.getSession().getAttribute("administrator");
                Users adminUsers = (Users) adminObj;
                if (adminUsers.getUid().equals(users.getUid())) {
                    req.getSession().setAttribute("message", "不能删除当前登录的管理员用户！");
                    url = "error.jsp";
                } else {
                    us.deleteUser(users);
                    url = "manager/user.jsp";
                }
            }


        }
        //转到url页面
        resp.sendRedirect(url);
        //req.getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //请求转发到doGet
        doGet(req, resp);
    }
}
