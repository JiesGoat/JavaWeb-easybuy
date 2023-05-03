package com.ybj.comments.servlet;

import com.ybj.comments.service.CommentsService;
import com.ybj.entity.Comments;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商品评论控制器
 * 1.继承HttpServlet 导包
 * 2.重写doGet doPost
 * 3.配置web.xml web->WEB-INF->lib
 */
public class CommentsServlet extends HttpServlet {

    //调用业务层
    CommentsService cs = new CommentsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Comments> comList = cs.queryAll();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //转发给doGet
        doGet(req, resp);
    }
}
