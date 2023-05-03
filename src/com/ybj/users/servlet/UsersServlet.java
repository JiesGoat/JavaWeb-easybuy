package com.ybj.users.servlet;

import com.ybj.entity.Users;
import com.ybj.users.dao.UsersDao;
import com.ybj.users.service.UsersService;
import com.ybj.util.ConvertDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 用户控制器
 * 1.继承HttpServlet 导包
 * 2.重写doGet doPost
 * 3.配置web.xml web->WEB-INF->lib
 */
public class UsersServlet extends HttpServlet {

    //服务层对象
    UsersService us = new UsersService();
    //数据层对象
    UsersDao ud = new UsersDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "index.jsp";
        //获取状态
        String status = req.getParameter("status");
        //用户数据层
        Users users = new Users();

        if (status != null) {
            if (status.equals("register")) {            //------------注册页面
                //获取表单数据
                users.setUid(req.getParameter("uid"));
                users.setUname(
                        new String(req.getParameter("uname").getBytes("iso-8859-1"), "utf-8"));
                users.setUpassword(req.getParameter("passWord"));
                users.setSex(
                        new String(req.getParameter("gender").getBytes("iso-8859-1"), "utf-8"));
                users.setBirthday(ConvertDate.strToDate(req.getParameter("birthday"), 1));
                users.setUcode(req.getParameter("identityCode"));
                users.setEmail(req.getParameter("email"));
                users.setMoblile(req.getParameter("mobile"));
                users.setAddress(
                        new String(req.getParameter("address").getBytes("iso-8859-1"), "utf-8"));
                users.setUstatus(0);//普通用户
                System.out.println(users.getUid() + "\t" + users.getUname() + "\t" + users.getUpassword());
                if (us.insertUser(users)) {
                    url = "login.jsp";
                } else {
                    url = "register.jsp";
                }
            }


        } else {                                        //------------登陆成功，管理员进入后台，用户进入主页
            //获取用户提交的编号和密码
            String uid = req.getParameter("uid");
            String upass = req.getParameter("passWord");
            users.setUid(uid);
            users.setUpassword(upass);
            Users u = us.isLogin(users);
            //如果登录成功到后台/首页
            if (u != null) {
                if (u.getUstatus() == 1) {//后台管理
                    //状态为1则是管理员，创建管理员用户
                    Users adminUser = ud.selectById(uid);
                    req.getSession().setAttribute("administrator", adminUser);
                    //设置跳转页面
                    url = "manager/user.jsp";
                } else {
                    url = "index.jsp";
                }
            } else {
                //如果失败返回登录页面
                url = "login.jsp";
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
