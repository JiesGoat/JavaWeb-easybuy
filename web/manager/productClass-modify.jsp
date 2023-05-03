<%@ page import="com.ybj.entity.Users" %>
<%@ page import="com.ybj.util.ConvertDate" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.ybj.entity.ProductCategory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="../images/logo.gif" /></div>
	<div class="help"><a href="../index.jsp">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="index.jsp">首页</a></li>
			<li><a href="user.jsp">用户</a></li>
			<li class="current"><a href="product.jsp">商品</a></li>
			<li><a href="order.jsp">订单</a></li>
			<li><a href="guestbook.jsp">留言</a></li>
			<li><a href="news.jsp">新闻</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="welcome wrap">
		<%
			//获取登录的管理员信息
			Object adminObj = session.getAttribute("administrator");
			Users admin = (Users)adminObj;
			if(admin != null){
		%>
		管理员<%=admin.getUname()%>您好，今天是<%=ConvertDate.dateToStr(new Date(),1)%>，欢迎回到管理后台。
		<%
			}
		%>
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>用户管理</dt>
				<dd><em><a href="user-add.jsp">新增</a></em><a href="user.jsp">用户管理</a></dd>
				<dt>商品信息</dt>
				<dd><em><a href="productClass-add.jsp">新增</a></em><a href="productClass.jsp">分类管理</a></dd>
				<dd><em><a href="product-add.jsp">新增</a></em><a href="product.jsp">商品管理</a></dd>
				<dt>订单管理</dt>
				<dd><a href="order.jsp">订单管理</a></dd>
				<dt>留言管理</dt>
				<dd><a href="guestbook.jsp">留言管理</a></dd>
				<dt>新闻管理</dt>
				<dd><em><a href="news-add.jsp">新增</a></em><a href="news.jsp">新闻管理</a></dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<h2>修改分类</h2>
		<div class="manage">
			<form action="../productCategoryServlet">
				<input type="hidden" name="status" value="productCategoryModify" />
				<table class="form">
					<%
						Object obj = session.getAttribute("modifyInitProductCategory");
						if(obj != null){
							ProductCategory pc  = (ProductCategory) obj;
					%>
					<tr>
						<td class="field">商品分类编号：</td>
						<td><input type="text" class="text" name="pcid" value="<%=pc.getPcid()%>" readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="field">父分类：</td>
						<td>
							<select name="parentid">
								<%
									if(pc.getParentid() == 1){
								%>
								<option value="1" selected="selected">吃的</option>
								<option value="2">用的</option>
								<option value="3">穿戴的</option>
								<%
									} else if(pc.getParentid() ==2){
								%>
								<option value="1">吃的</option>
								<option value="2" selected="selected">用的</option>
								<option value="3">穿戴的</option>
								<%
									} else if(pc.getParentid() ==3){
								%>
								<option value="1">吃的</option>
								<option value="2">用的</option>
								<option value="3" selected="selected">穿戴的</option>
								<%
									}
								%>
							</select>
						</td>
					</tr>
					<tr>
						<td class="field">分类名称：</td>
						<td><input type="text" class="text" name="pcname" value="<%=pc.getPcname()%>" /></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="更新" /></label></td>
					</tr>
				</table>
				<%
					} else {
							response.sendRedirect("../productCategoryServlet");
					}
				%>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2016 湖南软测信息技术有限公司 All Rights Reserved. 湘ICP证1001号
</div>
</body>
</html>
