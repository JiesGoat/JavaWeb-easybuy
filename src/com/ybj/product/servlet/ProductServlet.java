package com.ybj.product.servlet;

import com.ybj.product.service.ProductService;
import com.ybj.entity.Product;
import com.ybj.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 商品控制器
 * 1.继承HttpServlet 导包
 * 2.重写doGet doPost
 * 3.配置web.xml web->WEB-INF->lib
 */
public class ProductServlet extends HttpServlet {

    //调用业务层
    ProductService ps = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取状态
        String status = req.getParameter("status");
        //跳转到的页面
        String url = "index.jsp";

        if (status != null) {                     //------------商品详情
            //获取商品编号
            int pid = Integer.valueOf(req.getParameter("pid"));
            //调用业务层接受返回
            Product p = ps.findById(pid);
            //设置对象
            req.getSession().setAttribute("product", p);
            //设置跳转页面
            url = "product-view.jsp";


        } else {                                //------------分页查询
            //默认为第一页
            int index = 1;
            String str = req.getParameter("index");
            if (str != null) {
                index = Integer.valueOf(str);
            }
            PageUtil pu = ps.queryAll(index, 8);
            //设置对象
            req.getSession().setAttribute("pu", pu);
            //设置跳转页面
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
