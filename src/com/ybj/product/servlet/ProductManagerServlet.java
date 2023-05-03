package com.ybj.product.servlet;

import com.ybj.entity.Product;
import com.ybj.entity.Users;
import com.ybj.product.dao.ProductDao;
import com.ybj.product.service.ProductService;
import com.ybj.users.dao.UsersDao;
import com.ybj.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后台商品控制器
 * 1.继承HttpServlet 导包
 * 2.重写doGet doPost
 * 3.配置web.xml web->WEB-INF->lib
 */
public class ProductManagerServlet extends HttpServlet {

    //调用业务层
    ProductService ps = new ProductService();
    //调用数据层
    ProductDao pd = new ProductDao();

    UsersDao ud = new UsersDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "manager/product.jsp";
        String status = req.getParameter("status");
        Product product = new Product();

        if (status == null) {


        } else {
            if (status.equals("toProduct")) {                   //------------进入商品后台页面，传递管理员的值
                req.getSession().setAttribute("adminUser", req.getParameter("administrator"));
                url = "manager/product.jsp";


            } else if (status.equals("productPage")) {          //------------商品分页
                int index = 1;
                String str = req.getParameter("index");
                if (str != null) {
                    index = Integer.valueOf(str);
                }
                PageUtil pu = ps.queryAll(index, 2);
                req.getSession().setAttribute("pu", pu);
                url = "manager/product.jsp";


            } else if (status.equals("toProductAdd")) {         //------------进入商品新增页面
                String uid = req.getParameter("administrator");
                Users adminUser = ud.selectById(uid);
                req.getSession().setAttribute("administrator", adminUser);
                url = "manager/product-add.jsp";


            } else if (status.equals("productAdd")) {           //------------商品新增
                product.setPname(
                        new String(req.getParameter("pname").getBytes("iso-8859-1"), "utf-8"));
                product.setPdescription(
                        new String(req.getParameter("pdescription").getBytes("iso-8859-1"), "utf-8"));
                product.setCid(Integer.valueOf(req.getParameter("cid")));
                product.setPrice(Double.valueOf(req.getParameter("price")));
                product.setStock(Integer.valueOf(req.getParameter("stock")));
                product.setChildid(0);//默认为0
                product.setFilename(req.getParameter("filename"));
                if (ps.insertProduct(product)) {
                    url = "manager/product.jsp";
                } else {
                    url = "manager/product-add.jsp";
                }


            } else if (status.equals("productView")) {          //------------商品详情
                int pid = Integer.valueOf(req.getParameter("pid"));
                product = pd.selectById(pid);
                req.getSession().setAttribute("product", product);
                url = "manager/product-view.jsp";


            } else if (status.equals("initModify")) {           //------------初始化商品修改页面
                int pid = Integer.valueOf(req.getParameter("pid"));
                product = pd.selectById(pid);
                req.getSession().setAttribute("modifyInitProduct", product);
                url = "manager/product-modify.jsp";


            } else if (status.equals("productModify")) {        //------------商品修改
                int pid = Integer.valueOf(req.getParameter("pid"));
                product = pd.selectById(pid);
                if (req.getParameter("pname") != null) {
                    product.setPname(
                            new String(req.getParameter("pname").getBytes("iso-8859-1"), "utf-8"));
                }
                if (req.getParameter("pdescription") != null) {
                    product.setPdescription(
                            new String(req.getParameter("pdescription").getBytes("iso-8859-1"), "utf-8"));
                }
                if (req.getParameter("cid") != null) {
                    product.setCid(Integer.valueOf(req.getParameter("cid")));
                }
                if (req.getParameter("price") != null) {
                    product.setPrice(Double.valueOf(req.getParameter("price")));
                }
                if (req.getParameter("stock") != null) {
                    product.setStock(Integer.valueOf(req.getParameter("stock")));
                }
                if (req.getParameter("filename") != null) {
                    product.setFilename(req.getParameter("filename"));
                }
                ps.updateProduct(product);
                url = "manager/product.jsp";


            } else if (status.equals("del")) {                  //------------删除商品信息
                product = pd.selectById(Integer.valueOf(req.getParameter("pid")));
                pd.deleteProduct(product);
                url = "manager/product.jsp";


            }
        }
        //转到url页面
        resp.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //转发请求到doGet
        doGet(req, resp);
    }
}
