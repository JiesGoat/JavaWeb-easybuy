package com.ybj.product.service;

import com.ybj.entity.Product;
import com.ybj.product.dao.ProductDao;
import com.ybj.util.PageUtil;

import java.util.List;

/**
 * 商品业务层
 */
public class ProductService {

    //调用数据层
    ProductDao pd = new ProductDao();

    /**
     * 获取一页的商品信息
     *
     * @param index 起始下标
     * @return 商品页
     */
    public PageUtil queryAll(int index, int size) {
        //获取总行数
        int count = pd.getCount();
        //分页
        PageUtil pu = new PageUtil(count, index, size);
        //每页显示的数据
        List list = pd.selectAll(pu);
        pu.setList(list);
        return pu;
    }

    /**
     * 通过编号找到产品
     *
     * @param pid 产品id
     * @return 产品
     */
    public Product findById(int pid) {
        return pd.selectById(pid);
    }

    /**
     * 插入一个产品
     *
     * @param product 要插入的产品实体
     * @return 插入成功返回true, 失败返回false
     */
    public boolean insertProduct(Product product) {
        boolean flag = false;
        int row = 0;
        row = pd.insertProduct(product);
        if (row != 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 更新用户信息
     *
     * @param product 用户实体
     * @return 更新成功返回true，失败返回false
     */
    public boolean updateProduct(Product product) {
        boolean flag = false;
        int row = pd.updateProduct(product);
        if (row != 0) {
            flag = true;
        } else {
            System.out.println("更新失败！");
        }
        return flag;
    }
}
