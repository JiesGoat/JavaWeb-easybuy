package com.ybj.productcategory.service;

import com.ybj.entity.ProductCategory;
import com.ybj.productcategory.dao.ProductCategoryDao;
import com.ybj.util.PageUtil;

import java.util.List;

/**
 * 商品分类服务层
 */
public class ProductCategoryService {

    ProductCategoryDao pcd = new ProductCategoryDao();

    /**
     * 获取商品分页
     *
     * @param index 起始行数
     * @return 商品页
     */
    public PageUtil queryAll(int index) {
        //获取总行数
        int count = pcd.getCount();
        //分页
        PageUtil pu = new PageUtil(count, index, 4);
        //每页显示的数据
        List list = pcd.selectAll(pu);
        pu.setList(list);
        return pu;
    }

    /**
     * 获取所有的商品分类
     *
     * @return 所有商品列表
     */
    public List<ProductCategory> queryAll() {
        return pcd.selectAll();
    }

    /**
     * 新增商品分类信息
     *
     * @param productCategory 商品分类实体
     * @return 增加成功返回true，失败返回false
     */
    public boolean insertProductCategory(ProductCategory productCategory) {
        boolean flag = false;
        int row = pcd.insertProductCategory(productCategory);
        if (row != 0) {
            flag = true;
        } else {
            System.out.println("编号重复！");
        }
        return flag;
    }

    /**
     * 更改商品分类信息
     *
     * @param productCategory 商品分类实体
     * @return 更新成功返回true，失败返回false
     */
    public boolean updateProductCategory(ProductCategory productCategory) {
        boolean flag = false;
        int row = pcd.updateProductCategory(productCategory);
        if (row != 0) {
            flag = true;
        } else {
            System.out.println("更新失败！");
        }
        return flag;
    }
}
