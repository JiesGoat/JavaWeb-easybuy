package com.ybj.productcategory.dao;

import com.ybj.entity.ProductCategory;
import com.ybj.util.BaseDao;
import com.ybj.util.PageUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类数据层
 */
public class ProductCategoryDao extends BaseDao {


    /**
     * 获得商品分类列表总行数
     *
     * @return 分类列表总行数
     */
    public int getCount() {
        int row = 0;
        String sql = "SELECT count(pcid) FROM productcategory;";
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
     * 通过pcid查询商品分类实体
     *
     * @param pcid 商品分类编号
     * @return 商品实体
     */
    public ProductCategory selectById(int pcid) {
        ProductCategory pc = null;
        String sql = "SELECT pcid, pcname, parentid FROM productcategory WHERE pcid = ?;";
        Object[] obj = {pcid};
        rs = executeQuery(sql, obj);
        try {
            if (rs.next()) {
                pc = new ProductCategory();
                pc.setPcid(rs.getInt("pcid"));
                pc.setPcname(rs.getString("pcname"));
                pc.setParentid(rs.getInt("parentid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return pc;
    }

    /**
     * 查询商品分类中的所有信息
     *
     * @return 返回商品分类的所有数据
     */
    public List<ProductCategory> selectAll() {
        List list = new ArrayList();
        Object[] obj = null;
        try {
            //第三步：获取执行对象    第四步：获取结果（结果集）
            String sql = "SELECT pcid, pcname, parentid FROM productcategory;";
            rs = executeQuery(sql, obj);
            //第五步：输出结果
            while (rs.next()) {
                ProductCategory pc = new ProductCategory();
                pc.setPcid(rs.getInt("pcid"));
                pc.setPcname(rs.getString("pcname"));
                pc.setParentid(rs.getInt("parentid"));
                list.add(pc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * 分页查询商品分类表
     *
     * @param pu 商品分页对象
     * @return 商品分类页
     */
    public List<ProductCategory> selectAll(PageUtil pu) {
        List list = new ArrayList();
        Object[] obj = {(pu.getNow() - 1) * pu.getPageSize(), pu.getPageSize()};
        try {
            //第三步：获取执行对象    第四步：获取结果（结果集）
            String sql = "SELECT pcid, pcname, parentid FROM productcategory LIMIT ?, ?;";
            rs = executeQuery(sql, obj);
            //第五步：输出结果
            while (rs.next()) {
                ProductCategory pc = new ProductCategory();
                pc.setPcid(rs.getInt("pcid"));
                pc.setPcname(rs.getString("pcname"));
                pc.setParentid(rs.getInt("parentid"));
                list.add(pc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * 尝试在productcategory表中删除一条产品信息
     *
     * @param productCategory 要删除的产品
     * @return 返回改变的行数
     */
    public int deleteProductCategory(ProductCategory productCategory) {
        String sql = "DELETE FROM productCategory WHERE pcid = ?;";
        Object[] obj = {productCategory.getPcid()};
        int row = executeUpdate(sql, obj);
        return row;
    }

    /**
     * 尝试在productCategory表中插入一条产品记录
     *
     * @param productCategory 要插入的产品实体
     * @return 返回改变的行数
     */
    public int insertProductCategory(ProductCategory productCategory) {
        String sql = "INSERT INTO productcategory(pcname, parentid)VALUE(?, ?)";
        Object[] obj = {productCategory.getPcname(), productCategory.getParentid()};
        int row = executeUpdate(sql, obj);
        return row;
    }

    /**
     * 尝试在productCategory表中插入一条产品记录
     *
     * @param productCategory 要插入的产品实体
     * @return 返回改变的行数
     */
    public int updateProductCategory(ProductCategory productCategory) {
        String sql = "UPDATE productcategory SET pcname=?, parentid=? WHERE pcid = ?;";
        Object[] obj = {productCategory.getPcname(), productCategory.getParentid(), productCategory.getPcid()};
        int row = executeUpdate(sql, obj);
        return row;
    }
}
