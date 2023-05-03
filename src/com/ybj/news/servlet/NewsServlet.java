package com.ybj.news.servlet;

import com.ybj.entity.News;
import com.ybj.news.service.NewsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 新闻控制器
 * 1.继承HttpServlet 导包
 * 2.重写doGet doPost
 * 3.配置web.xml web->WEB-INF->lib
 */
public class NewsServlet extends HttpServlet {

    //调用业务层
    NewsService ns = new NewsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "index.jsp";
        String status = req.getParameter("status");
        if(status != null){
            if(status.equals("newsView")){              //------------新闻详情
                //获取编号、调用业务、设置对象、跳转页面
                int nid = Integer.valueOf(req.getParameter("nid"));
                News news = ns.findById(nid);
                req.getSession().setAttribute("news", news);
                url = "news-view.jsp";
            }


        } else {                                        //------------首页新闻热点
            List<News> newsList = ns.queryAll();
            req.getSession().setAttribute("newsList", newsList);
            //跳转到首页
            url = "index.jsp";
        }
        //转到url页面
        req.getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //转发请求到doGet
        doGet(req, resp);
    }
}
