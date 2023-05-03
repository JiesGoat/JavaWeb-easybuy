package com.ybj.productcategory.servlet;

import com.ybj.entity.ProductCategory;
import com.ybj.entity.Users;
import com.ybj.productcategory.dao.ProductCategoryDao;
import com.ybj.productcategory.service.ProductCategoryService;
import com.ybj.users.dao.UsersDao;
import com.ybj.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商品分类控制器
 */
public class ProductCategoryServlet extends HttpServlet {

    //商品分类业务层
    ProductCategoryService pcs = new ProductCategoryService();
    //商品分类数据层
    ProductCategoryDao pcd = new ProductCategoryDao();
    //用户数据层
    UsersDao ud = new UsersDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "manager/productClass.jsp";
        String status = req.getParameter("status");
        ProductCategory productCategory = new ProductCategory();

        if (status == null) {


        } else {
            if (status.equals("toProductClass")) {                //------------第一次进入productClass.jsp(来自user.jsp)
                String uid = req.getParameter("administrator");
                Users adminUser = ud.selectById(uid);
                req.getSession().setAttribute("administrator", adminUser);
                url = "manager/productClass.jsp";


            } else if (status.equals("productCategoryPage")) {    //------------分页查询显示
                int index = 1;
                String str = req.getParameter("index");
                if (str != null) {
                    index = Integer.valueOf(str);
                }
                PageUtil pu = pcs.queryAll(index);
                req.getSession().setAttribute("pu", pu);
                url = "manager/productClass.jsp";


            } else if (status.equals("del")) {                  //------------删除商品分类信息
                productCategory = pcd.selectById(Integer.valueOf(req.getParameter("pcid")));
                pcd.deleteProductCategory(productCategory);
                url = "manager/productClass.jsp";


            } else if (status.equals("getAll")) {                 //------------得到所有商品分类类型(来自product-add.jsp)
                List<ProductCategory> pcList = pcs.queryAll();
                req.getSession().setAttribute("pcList", pcList);
                url = "manager/product-add.jsp";


            } else if (status.equals("initModify")) {           //------------进入分类修改页面，并初始化
                String str = req.getParameter("pcid");
                if(str != null){
                    productCategory = pcd.selectById(Integer.valueOf(str));
                }
                req.getSession().setAttribute("modifyInitProductCategory", productCategory);
                url = "manager/productClass-modify.jsp";


            } else if(status.equals("productCategoryModify")){     //------------分类修改
                productCategory = pcd.selectById(
                        Integer.valueOf(req.getParameter("pcid")));
                if (req.getParameter("pcname") != null) {
                    productCategory.setPcname(
                            new String(req.getParameter("pcname").getBytes("iso-8859-1"), "utf-8"));
                }
                if (req.getParameter("parentid") != null) {
                    productCategory.setParentid(Integer.valueOf(req.getParameter("parentid")));
                }
                if (pcs.updateProductCategory(productCategory)) {
                    //System.out.println("news" + news.getNid() + "更新成功！");
                    url = "manager/productClass.jsp";
                } else {
                    url = "manager/productClass-modify.jsp";
                }


            } else if (status.equals("toProductClassAdd")) {         //------------进入新增分类页面、传递管理员的值
                Object adminObj = req.getSession().getAttribute("administrator");
                Users adminUsers = (Users) adminObj;
                req.getSession().setAttribute("administrator", adminUsers);
                url = "manager/productClass-add.jsp";


            } else if (status.equals("productCategoryAdd")) {          //------------新增分类
                productCategory.setPcname(
                        new String(req.getParameter("pcname").getBytes("iso-8859-1"), "utf-8"));
                productCategory.setParentid(Integer.valueOf(req.getParameter("parentid")));
                if (pcs.insertProductCategory(productCategory)) {
                    url = "manager/productClass.jsp";
                } else {
                    url = "anager/productClass-add.jsp";
                }


            }
        }
        resp.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
