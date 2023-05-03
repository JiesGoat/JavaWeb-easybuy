package com.ybj.comments.servlet;

import com.ybj.comments.service.CommentsService;
import com.ybj.entity.Comments;
import com.ybj.entity.Users;
import com.ybj.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 后台商品评论控制器
 * 1.继承HttpServlet 导包
 * 2.重写doGet doPost
 * 3.配置web.xml web->WEB-INF->lib
 */
public class CommentsManagerServlet extends HttpServlet {

    //调用业务层
    CommentsService cs = new CommentsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        String url = "manager/guestbook.jsp";
        Comments comments = new Comments();
        String status = req.getParameter("status");
        if (status == null) {


        } else {

            if (status.equals("toComments")) {                  //------------进入评论后台页面、传递管理员的值(从其他页面)
                Object adminObj = req.getSession().getAttribute("administrator");
                Users adminUsers = (Users) adminObj;
                req.getSession().setAttribute("administrator", adminUsers);
                url = "manager/Comments.jsp";


            } else if (status.equals("commentsPage")) {          //------------分页查询
                System.out.println("11111");
                //默认为第一页
                int index = 1;
                String str = req.getParameter("index");
                if (str != null) {
                    index = Integer.valueOf(str);
                }
                System.out.println("str = " + str + ",index = " + index);
                PageUtil pu = cs.queryPage(index);
                if (pu == null) {
                    System.out.println("pu == null");
                }
                //设置对分页象
                req.getSession().setAttribute("pu", pu);
                url = "manager/comments.jsp";


            } else if (status.equals("initModify")) {         //------------初始化要更新的新闻，进入comments-modify.jsp
                String str = req.getParameter("nid");
                if (str != null) {
                    comments = nd.selectById(
                            Integer.valueOf(req.getParameter("nid")));
                } else {
                    System.out.println("str == null");
                }
                req.getSession().setAttribute("modifyInitcomments", comments);
                url = "manager/comments-modify.jsp";


            } else if (status.equals("commentsModify")) {         //------------接收更新的新闻，进行更新
                comments = nd.selectById(
                        Integer.valueOf(req.getParameter("nid")));
                if (req.getParameter("title") != null) {
                    comments.setTitle(
                            new String(req.getParameter("title").getBytes("iso-8859-1"), "utf-8"));
                }
                if (req.getParameter("content") != null) {
                    comments.setContent(
                            new String(req.getParameter("content").getBytes("iso-8859-1"), "utf-8"));
                }
                if (ns.updateUser(comments)) {
                    System.out.println("comments" + comments.getNid() + "更新成功！");
                    url = "manager/comments.jsp";
                } else {
                    url = "manager/comments-modify.jsp";
                }


            } else if (status.equals("tocommentsAdd")) {         //------------进入新增新闻页面、传递管理员的值
                Object adminObj = req.getSession().getAttribute("administrator");
                Users adminUsers = (Users) adminObj;
                req.getSession().setAttribute("administrator", adminUsers);
                url = "manager/comments-add.jsp";


            } else if (status.equals("commentsAdd")) {          //------------新增新闻
                comments.setTitle(
                        new String(req.getParameter("title").getBytes("iso-8859-1"), "utf-8"));
                comments.setContent(
                        new String(req.getParameter("content").getBytes("iso-8859-1"), "utf-8"));
                if (ns.insertcomments(comments)) {
                    url = "manager/comments.jsp";
                } else {
                    url = "manager/comments-add.jsp";
                }


            } else if (status.equals("del")) {              //------------删除新闻信息
                comments = nd.selectById(Integer.valueOf(req.getParameter("nid")));
                ns.deleteUser(comments);
                url = "manager/comments.jsp";


            }

        }
        //System.out.println("status = " + status +",url = " + url);
        resp.sendRedirect(url);*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //转发给doGet
        doGet(req, resp);
    }
}
