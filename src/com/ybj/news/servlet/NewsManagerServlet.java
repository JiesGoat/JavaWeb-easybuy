package com.ybj.news.servlet;

import com.ybj.entity.News;
import com.ybj.entity.Users;
import com.ybj.news.dao.NewsDao;
import com.ybj.news.service.NewsService;
import com.ybj.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 新闻后台控制器
 * 1.继承HttpServlet 导包
 * 2.重写doGet doPost
 * 3.配置web.xml web->WEB-INF->lib
 */
public class NewsManagerServlet extends HttpServlet {

    //服务层对象
    NewsService ns = new NewsService();
    //数据层对象
    NewsDao nd = new NewsDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "manager/news.jsp";
        News news = new News();
        String status = req.getParameter("status");
        if (status == null) {


        } else {

            if (status.equals("toNews")) {                  //------------进入新闻后台页面、传递管理员的值(从其他页面)
                Object adminObj = req.getSession().getAttribute("administrator");
                Users adminUsers = (Users) adminObj;
                req.getSession().setAttribute("administrator", adminUsers);
                url = "manager/news.jsp";


            } else if (status.equals("newsPage")) {          //------------分页查询
                //默认为第一页
                int index = 1;
                String str = req.getParameter("index");
                if (str != null) {
                    index = Integer.valueOf(str);
                }
                PageUtil pu = ns.queryPage(index);
                //设置对分页象
                req.getSession().setAttribute("pu", pu);
                url = "manager/news.jsp";


            } else if (status.equals("initModify")) {         //------------初始化要更新的新闻，进入news-modify.jsp
                String str = req.getParameter("nid");
                if (str != null) {
                    news = nd.selectById(
                            Integer.valueOf(req.getParameter("nid")));
                } else {
                    System.out.println("str == null");
                }
                req.getSession().setAttribute("modifyInitNews", news);
                url = "manager/news-modify.jsp";


            } else if (status.equals("newsModify")) {         //------------接收更新的新闻，进行更新
                news = nd.selectById(
                        Integer.valueOf(req.getParameter("nid")));
                if (req.getParameter("title") != null) {
                    news.setTitle(
                            new String(req.getParameter("title").getBytes("iso-8859-1"), "utf-8"));
                }
                if (req.getParameter("content") != null) {
                    news.setContent(
                            new String(req.getParameter("content").getBytes("iso-8859-1"), "utf-8"));
                }
                if (ns.updateUser(news)) {
                    url = "manager/news.jsp";
                } else {
                    url = "manager/news-modify.jsp";
                }


            } else if (status.equals("toNewsAdd")) {         //------------进入新增新闻页面、传递管理员的值
                Object adminObj = req.getSession().getAttribute("administrator");
                Users adminUsers = (Users) adminObj;
                req.getSession().setAttribute("administrator", adminUsers);
                url = "manager/news-add.jsp";


            } else if (status.equals("newsAdd")) {          //------------新增新闻
                news.setTitle(
                        new String(req.getParameter("title").getBytes("iso-8859-1"), "utf-8"));
                news.setContent(
                        new String(req.getParameter("content").getBytes("iso-8859-1"), "utf-8"));
                if (ns.insertNews(news)) {
                    url = "manager/news.jsp";
                } else {
                    url = "manager/news-add.jsp";
                }


            } else if (status.equals("del")) {              //------------删除新闻信息
                news = nd.selectById(Integer.valueOf(req.getParameter("nid")));
                ns.deleteUser(news);
                url = "manager/news.jsp";


            }
        }
        resp.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //请求转发到doGet
        doGet(req, resp);
    }
}
