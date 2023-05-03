package com.ybj.product.dao;

import com.ybj.entity.Product;
import com.ybj.util.BaseDao;
import com.ybj.util.PageUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 商品数据层实现类
 */
public class ProductDao extends BaseDao {

    Scanner input = new Scanner(System.in);

    /**
     * 向product表中增加一条商品记录
     */
    public void insert() {
        System.out.println("请输入一行的值（商品名称，商品介绍，商品价格，库存，商品类型编号，子编号，文件编号）：");
        Object[] obj = new Object[7];
        int i = 0;
        while (i < 7) {
            obj[i] = input.next();
            i++;
        }
        //第一步：加载驱动 第二步：获取连接对象
        conn = getConnection();
        //第三步：获取执行对象 第四步：获取结果（受影响的行数）
        String sql = "INSERT INTO product(pname, pdescription, price, stock, cid, childid, filename)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?);";
        int row = executeUpdate(sql, obj);
        if (row != 0) {
            System.out.println("插入名称为“" + obj[0] + "”的记录成功！");
        } else {
            System.out.println("插入失败！");
        }
    }

    /**
     * 删除product表中的一条记录
     */
    public void delete() {
        System.out.print("请输入要删除的商品的编号：");
        String _pid = input.next();
        Object[] obj = {_pid};
        //第一步：加载驱动  第二步：获取连接对象
        conn = getConnection();
        //第三步：获取执行对象    第四步：获取结果（受影响的行数）
        String sql = "DELETE FROM product WHERE pid = ?;";
        int row = executeUpdate(sql, obj);
        //第五步：输出结果
        if (row != 0) {
            System.out.println("删除编号：" + _pid + "成功！");
        } else {
            System.out.println("删除失败！");
        }
    }

    /**
     * 查询product表里面的所有记录
     *
     * @return
     */
    public List<Product> selectAll() {
        List list = new ArrayList();
        Object[] obj = null;
        try {
            //第三步：获取执行对象    第四步：获取结果（结果集）
            String sql = "SELECT pid, pname, pdescription, price, stock, cid, childid, filename FROM product;";
            rs = executeQuery(sql, obj);
            //第五步：输出结果
            while (rs.next()) {
                Product product = new Product();
                product.setPid(rs.getInt("pid"));
                product.setPname(rs.getString("pname"));
                product.setPdescription(rs.getString("pdescription"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setCid(rs.getInt("cid"));
                product.setChildid(rs.getInt("childid"));
                product.setFilename(rs.getString("filename"));
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * 查询product表里面的一页记录
     *
     * @param pu 分页对象
     * @return 一页产品
     */
    public List<Product> selectAll(PageUtil pu) {
        List list = new ArrayList();
        Object[] obj = {(pu.getNow() - 1) * pu.getPageSize(), pu.getPageSize()};
        //第三步：获取执行对象    第四步：获取结果（结果集）
        String sql = "SELECT pid, pname, pdescription, price, stock, cid, childid, filename FROM product LIMIT ?, ?;";
        rs = executeQuery(sql, obj);
        try {
            //第五步：输出结果
            while (rs.next()) {
                Product product = new Product();
                product.setPid(rs.getInt("pid"));
                product.setPname(rs.getString("pname"));
                product.setPdescription(rs.getString("pdescription"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setCid(rs.getInt("cid"));
                product.setChildid(rs.getInt("childid"));
                product.setFilename(rs.getString("filename"));
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * 修改product表中的一条商品的介绍
     */
    public void update() {
        System.out.print("请输入用户编号：");
        String _pid = input.next();
        System.out.print("\n请输入新商品介绍:");
        String _pdescription = input.next();
        Object[] obj = {_pdescription, _pid};
        //第一步：加载驱动  第二部：获取连接对象
        conn = getConnection();
        //第三步：获取执行对象 第四步：获取结果（受影响的行数）
        String sql = "UPDATE product SET pdescription = ? WHERE pid = ?;";
        int row = executeUpdate(sql, obj);
        //第五步：输出结果
        if (row != 0) {
            System.out.println("修改商品编号" + _pid + "介绍成功！");
        } else {
            System.out.println("修改失败！");
        }
    }

    /**
     * 根据编号查询产品信息
     *
     * @param pid 产品编号
     * @return 产品实体
     */
    public Product selectById(int pid) {
        Product product = null;
        String sql = "SELECT pid, pname, pdescription, price, stock, cid, childid, filename FROM product WHERE pid = ?;";
        Object[] obj = {pid};
        rs = executeQuery(sql, obj);
        try {
            if (rs.next()) {
                product = new Product();
                product.setPid(rs.getInt("pid"));
                product.setPname(rs.getString("pname"));
                product.setPdescription(rs.getString("pdescription"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setCid(rs.getInt("cid"));
                product.setChildid(rs.getInt("childid"));
                product.setFilename(rs.getString("filename"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return product;
    }

    /**
     * 获得商品列表总行数
     *
     * @return 商品列表总行数
     */
    public int getCount() {
        int row = 0;
        String sql = "SELECT count(pid) FROM product;";
        Object[] obj = null;
        rs = executeQuery(sql, obj);
        try {
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return row;
    }

    /**
     * 尝试向product表中插入一条商品记录
     *
     * @param product 要插入的商品
     * @return 返回改变的行数
     */
    public int insertProduct(Product product) {
        String sql = "INSERT INTO product(pname, pdescription, price, stock, cid, childid, filename) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {product.getPname(), product.getPdescription(), product.getPrice(), product.getStock(),
                product.getCid(), product.getChildid(), product.getFilename()};
        int row = executeUpdate(sql, obj);
        return row;
    }

    /**
     * 尝试在users表中更新一条记录
     *
     * @param product 要更新的用户（用户的uid和ucode不能更新）
     * @return 返回改变的行数
     */
    public int updateProduct(Product product) {
        String sql = "UPDATE product SET pname=?, pdescription=?, price=?, stock=?, cid=?, childid=?, filename=? " +
                "WHERE pid=?";
        Object[] obj = {product.getPname(), product.getPdescription(), product.getPrice(), product.getStock(), product.getCid(),
                product.getChildid(), product.getFilename(), product.getPid()};
        int row = executeUpdate(sql, obj);
        return row;
    }

    /**
     * 尝试在product表中删除一条产品信息
     *
     * @param product 要删除的产品
     * @return 返回改变的行数
     */
    public int deleteProduct(Product product) {
        String sql = "DELETE FROM product WHERE pid = ?;";
        Object[] obj = {product.getPid()};
        int row = executeUpdate(sql, obj);
        return row;
    }
}
