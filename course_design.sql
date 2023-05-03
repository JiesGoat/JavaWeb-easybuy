#easebuy数据库创建


#用户表
drop table if exists users;
CREATE TABLE users  (
  uid varchar(10) NOT NULL COMMENT '用户编号' PRIMARY KEY,
  uname varchar(20)  NOT NULL COMMENT '用户名',
  upassword varchar(20) NOT NULL COMMENT '密码',
  sex varchar(1)  NOT NULL COMMENT '性别',
  birthday datetime NOT NULL COMMENT '出生日期',
  ucode varchar(60)  NOT NULL COMMENT '身份证',
  email varchar(80)  NOT NULL COMMENT '邮箱',
  mobile varchar(11)  NOT NULL COMMENT '手机号',
  address varchar(200)  NOT NULL COMMENT '地址',
  ustatus int(6) NOT NULL COMMENT '状态'
);

#商品表
drop table if exists product;
CREATE TABLE product  (
  pid int PRIMARY KEY AUTO_INCREMENT	COMMENT '商品编号',
  pname varchar(20)  NOT NULL COMMENT '名称',
  pdescription varchar(100) DEFAULT NULL COMMENT '描述',
  price decimal(10,2)  NOT NULL COMMENT '价格',
  stock int(255) NOT NULL COMMENT '库存',
  cid int(11)  NOT NULL COMMENT '分类id',
  childid int(11)  DEFAULT NULL COMMENT '二级分类id',
  filename varchar(200)  NOT NULL COMMENT '文件名'
);

#商品类型
drop table if exists productcategory;
CREATE TABLE productcategory  (
  pcid int PRIMARY KEY AUTO_INCREMENT	COMMENT '类型编号',
  pcname varchar(20)  NOT NULL COMMENT '名称',
  parentid int(10) NOT NULL COMMENT '父级编号'
);

#新闻表
drop table if exists news;
CREATE TABLE news (
  nid int PRIMARY KEY AUTO_INCREMENT	COMMENT '新闻编号',
  title varchar(40)  NOT NULL COMMENT '标题',
  content varchar(10000) NOT NULL COMMENT '内容',
  createtime datetime DEFAULT	NULL COMMENT '发布时间'
);

#订单表
drop table if exists orders;
CREATE TABLE orders (
  oid int PRIMARY KEY AUTO_INCREMENT	COMMENT '订单编号',
  uid varchar(10)	NOT NULL COMMENT '用户编号',
	uname varchar(20)  NOT NULL COMMENT '收件人',
  address varchar(200)  NOT NULL COMMENT '收件地址',
	createtime datetime NOT NULL COMMENT '下单时间',
	cost decimal(10,2) NOT NULL COMMENT '总价',
	ostatus int(255) NOT NULL COMMENT '1下单 2审核通过 3配货 4送货中 5收获并确认',
	otype int(255) NOT NULL COMMENT '1：货到付款 2：网上支付'
);

#订单详细表
drop table if exists orderdetail;
CREATE TABLE orderdetail (
  odid int PRIMARY KEY AUTO_INCREMENT	COMMENT '详细编号',
  oid int	NOT NULL COMMENT '订单编号',
	pid int  NOT NULL COMMENT '商品编号',
  quantity int NOT NULL COMMENT '数量',
	cost decimal(10,2) NOT NULL COMMENT '单价'
);

#商品评论表
drop table if exists comments;
CREATE TABLE comments (
  cid int PRIMARY KEY AUTO_INCREMENT	COMMENT '评论编号',
	content varchar(200) NOT NULL COMMENT '内容',
	createtime datetime NOT NULL COMMENT '时间',
	pid int NOT NULL COMMENT '商品编号',
	nickname varchar(10) NOT NULL COMMENT '昵称'
);

#商品表中商品分类cid与商品类型表中的pcid
ALTER TABLE  product ADD CONSTRAINT FK_product_cid FOREIGN  KEY (cid) REFERENCES productcategory(pcid);
#订单表中用户编号uid与用户表中的uid
ALTER TABLE  orders ADD CONSTRAINT FK_orders_uid FOREIGN  KEY (uid) REFERENCES users(uid);
#订单详细表中订单编号oid与订单表中的编号oid
ALTER TABLE  orderdetail ADD CONSTRAINT FK_orderdetail_oid FOREIGN  KEY (oid) REFERENCES orders(oid);
#订单详细表中商品编号pid与商品表中的编号pid
ALTER TABLE  orderdetail ADD CONSTRAINT FK_orderdetail_pid FOREIGN  KEY (pid) REFERENCES product(pid);
#商品评论表中编号pid与商品表中编号pid
ALTER TABLE  comments ADD CONSTRAINT FK_comments_pid FOREIGN  KEY (pid) REFERENCES product(pid);
