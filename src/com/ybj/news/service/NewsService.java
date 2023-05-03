package com.ybj.news.service;

import com.ybj.entity.News;
import com.ybj.news.dao.NewsDao;
import com.ybj.util.PageUtil;

import java.util.List;

/**
 * 新闻业务层
 */
public class NewsService {
    //创建对象
    NewsDao nd = new NewsDao();

    /**
     * 查询所有新闻
     */
    public List<News> queryAll() {
        List newsList = nd.selectAll();
        return newsList;
    }

    /**
     * 查询一页的新闻记录
     *
     * @param index 页起始下标
     * @return 一页新闻记录
     */
    public PageUtil queryPage(int index) {
        //获取总行数
        int count = nd.getCount();
        //分页
        PageUtil pu = new PageUtil(count, index, 4);
        //每页显示的数据
        List list = nd.selectAll(pu);
        pu.setList(list);
        return pu;
    }

    /**
     * 更新新闻信息
     *
     * @param news 新闻实体
     * @return 更新成功返回true，失败返回false
     */
    public boolean updateUser(News news) {
        boolean flag = false;
        int row = nd.updateUser(news);
        if (row != 0) {
            flag = true;
        } else {
            System.out.println("更新失败！");
        }
        return flag;
    }

    /**
     * 根据编号查询新闻
     *
     * @param nid 新闻编号
     * @return 新闻对象
     */
    public News findById(int nid) {
        return nd.selectById(nid);
    }

    /**
     * 新增新闻信息
     *
     * @param news 新闻实体
     * @return 增加成功返回true，失败返回false
     */
    public boolean insertNews(News news) {
        boolean flag = false;
        int row = nd.insertUser(news);
        if (row != 0) {
            flag = true;
        } else {
            System.out.println("编号重复！");
        }
        return flag;
    }

    /**
     * 删除新闻信息
     *
     * @param news 新闻实体
     * @return 删除成功返回true，失败返回false
     */
    public boolean deleteUser(News news) {
        boolean flag = false;
        int row = nd.deleteUser(news);
        if (row != 0) {
            flag = true;
        } else {
            System.out.println("删除失败！");
        }
        return flag;
    }
}
