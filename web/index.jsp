<%@ page import="com.ybj.entity.News" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ybj.entity.Product" %>
<%@ page import="com.ybj.util.PageUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>易买网 - 首页</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<div id="header" class="wrap">
    <div id="logo"><img src="images/logo.gif"/></div>
    <div class="help"><a href="#" class="shopping">购物车</a><a href="login.jsp">登录</a><a href="register.jsp">注册</a><a
            href="guestbook.jsp">留言</a></div>
    <div class="navbar">
        <ul class="clearfix">
            <li class="current"><a href="#">首页</a></li>
            <li><a href="#">图书</a></li>
            <li><a href="#">百货</a></li>
            <li><a href="#">品牌</a></li>
            <li><a href="#">促销</a></li>
        </ul>
    </div>
</div>
<div id="childNav">
    <div class="wrap">
        <ul class="clearfix">
            <li class="first"><a href="#">音乐</a></li>
            <li><a href="#">影视</a></li>
            <li><a href="#">少儿</a></li>
            <li><a href="#">动漫</a></li>
            <li><a href="#">小说</a></li>
            <li><a href="#">外语</a></li>
            <li><a href="#">数码相机</a></li>
            <li><a href="#">笔记本</a></li>
            <li class="last"><a href="#">Investor Relations</a></li>
        </ul>
    </div>
</div>
<div id="main" class="wrap">
    <div class="lefter">
        <div class="box">
            <h2>商品分类</h2>
            <dl>
                <dt>图书音像</dt>
                <dd><a href="product-list.html">图书</a></dd>
            </dl>
        </div>
        <div class="spacer"></div>
        <div class="last-view">
            <h2>最近浏览</h2>
            <dl class="clearfix">
                <dt><img src="images/product/0_tiny.gif"/></dt>
                <dd><a href="product-view.html">法国德菲丝松露精品巧克力500g/盒</a></dd>
                <dt><img src="images/product/0_tiny.gif"/></dt>
                <dd><a href="product-view.html">法国德菲丝松露精品巧克力500g/盒</a></dd>
            </dl>
        </div>
    </div>
    <div class="main">
        <div class="price-off">
            <h2>商品列表</h2>
            <ul class="product clearfix">
                <%--<li>--%>
                <%--<dl>--%>
                <%--<dt><a href="product-view.html" target="_blank"><img src="images/product/1.jpg" /></a></dt>--%>
                <%--<dd class="title"><a href="product-view.html" target="_blank">法国德菲丝松露精品巧克力500g/盒</a></dd>--%>
                <%--<dd class="price">￥108.0</dd>--%>
                <%--</dl>--%>
                <%--</li>--%>
                <%
                    //获取商品列表集合
                    Object proobj = session.getAttribute("pu");
                    PageUtil pu = null;
                    if (proobj != null) {
                        pu = (PageUtil) proobj;
                        List<Product> proList = pu.getList();
                        //循环输出
                        for (int i = 0; i < proList.size(); i++) {
                            Product product = proList.get(i);
                %>
                <li>
                    <dl>
                        <dt><a href="productServlet?status=productView&pid=<%=product.getPid()%>" target="_blank"><img
                                src=images/product/<%=product.getFilename()%> /></a></dt>
                        <dd class="title"><a href="productServlet?status=productView&pid=<%=product.getPid()%>" target="_blank"><%=product.getPname()%>
                        </a></dd>
                        <dd class="price">￥<%=product.getPrice()%>
                        </dd>
                    </dl>
                </li>
                <%
                        }
                    } else {
                        //商品列表为空访问productServlet
                            //response.sendRedirect("productServlet");
                            request.getRequestDispatcher("productServlet").forward(request, response);
                    }
                %>

            </ul>
            <div class="pager">
                <ul class="clearfix">
                    <li class="current">1</li>
                    <li><a href="#">2</a></li>
                </ul>
                <%
                    if (pu != null) {
                %>
                <table align="center">
                    <tr>
                        <td><a href="productServlet?index=<%=pu.getFirst()%>">首页</a></td>
                        <td><a href="productServlet?index=<%=pu.getBack()%>">上一页</a></td>
                        <td><%=pu.getNow()%>/<%=pu.getTotal()%>
                        </td>
                        <td><a href="productServlet?index=<%=pu.getNext()%>">下一页</a></td>
                        <td><a href="productServlet?index=<%=pu.getLast()%>">尾页</a></td>
                    </tr>
                </table>
                <%
                    }
                    session.removeAttribute("pu");
                %>
            </div>

        </div>
        <div class="side">
            <div class="spacer"></div>
            <div class="news-list">
                <h4>新闻动态</h4>
                <ul>

                    <%--<li><a href="news-view.html" target="_blank">抢钱啦</a></li>--%>
                    <%
                        //java脚本 代码
                        Object newsobj = session.getAttribute("newsList");
                        if (newsobj != null) {
                            List<News> newsList = (List) newsobj;
                            //循环集合输出
                            for (int i = 0; i < newsList.size(); i++) {
                                News news = newsList.get(i);
                                if (news.getTitle().length() > 15) {
                    %>
                    <li><a href="newsServlet?status=newsView&nid=<%=news.getNid()%>" target="_blank"><%=news.getTitle().substring(0, 15)%>...</a></li>
                    <%
                                } else {
                    %>
                    <li><a href="newsServlet?status=newsView&nid=<%=news.getNid()%>" target="_blank"><%=news.getTitle()%>
                    </a></li>
                    <%
                                }
                            }
                        } else {
                            //不存在新闻集合访问Servlet
                                    //response.sendRedirect("newsServlet");
                            request.getRequestDispatcher("newsServlet").forward(request, response);
                        }
                    %>
                </ul>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    Copyright &copy; 2016 湖南软测信息技术有限公司 All Rights Reserved. 湘ICP证1001号
</div>
</body>
</html>
