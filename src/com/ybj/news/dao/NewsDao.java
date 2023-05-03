package com.ybj.news.dao;

import com.ybj.util.BaseDao;
import com.ybj.entity.News;
import com.ybj.util.PageUtil;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 新闻数据层
 */
public class NewsDao extends BaseDao {

    Scanner input = new Scanner(System.in);

    /**
     * 向news表里增加一条记录
     */
    public void insert() {
        System.out.println("请输入一行的值（新闻标题，内容，发布时间）：");
        Object[] obj = new Object[3];
        int i = 0;
        while (i < 3) {
            obj[i] = input.next();
            i++;
        }
        //第一步：加载驱动 第二步：获取连接对象
        conn = getConnection();
        //第三步：获取执行对象 第四步：获取结果（受影响的行数）
        String sql = "INSERT INTO news(title, content, createtime) VALUES( ?, ?, ?)";
        int row = executeUpdate(sql, obj);
        if (row != 0) {
            System.out.println("插入标题为“" + obj[0] + "”的记录成功！");
        } else {
            System.out.println("插入失败！");
        }
    }

    /**
     * 删除news表中的一行记录
     */
    public void delete() {
        System.out.print("请输入要删除的新闻的编号：");
        String _nid = input.next();
        Object[] obj = {_nid};
        //第一步：加载驱动  第二步：获取连接对象
        conn = getConnection();
        //第三步：获取执行对象    第四步：获取结果（受影响的行数）
        String sql = "DELETE FROM news WHERE nid = ?;";
        int row = executeUpdate(sql, obj);
        //第五步：输出结果
        if (row != 0) {
            System.out.println("删除编号：" + _nid + "成功！");
        } else {
            System.out.println("删除失败！");
        }
    }

    /**
     * 查询news表中所有记录
     *
     * @return 记录列表
     */
    public List<News> selectAll() {
        List list = new ArrayList();
        Object[] obj = null;
        try {
            //第三步：获取执行对象    第四步：获取结果（结果集）
            String sql = "SELECT nid, title, content, createtime FROM news;";
            rs = executeQuery(sql, obj);
            //第五步：输出结果
            while (rs.next()) {
                News news = new News();
                news.setNid(rs.getInt("nid"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setCreatetime(rs.getTimestamp("createtime"));
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * 查询news表一个分页的所有记录
     *
     * @param pu 要查询的分页
     * @return 查询到的分页记录
     */
    public List<News> selectAll(PageUtil pu) {
        List list = new ArrayList();
        Object[] obj = {(pu.getNow() - 1) * pu.getPageSize(), pu.getPageSize()};
        News news;
        try {
            //第三步：获取执行对象    第四步：获取结果（结果集）
            String sql = "SELECT nid, title, content, createtime FROM news LIMIT ?, ?;";
            rs = executeQuery(sql, obj);
            //第五步：输出结果
            while (rs.next()) {
                news = new News();
                news.setNid(rs.getInt("nid"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setCreatetime(rs.getTimestamp("createtime"));
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * 修改news表的一条记录
     */
    public void update() {
        System.out.print("请输入新闻编号：");
        String _nid = input.next();
        System.out.print("\n请输入新内容:");
        String _content = input.next();
        Object[] obj = {_content, _nid};
        //第一步：加载驱动  第二部：获取连接对象
        conn = getConnection();
        //第三步：获取执行对象 第四步：获取结果（受影响的行数）
        String sql = "UPDATE news SET content = ? WHERE nid = ?;";
        int row = executeUpdate(sql, obj);
        //第五步：输出结果
        if (row != 0) {
            System.out.println("修改编号" + _nid + "内容成功！");
        } else {
            System.out.println("修改失败！");
        }
    }

    /**
     * 获得news表的总行数
     *
     * @return news表总行数
     */
    public int getCount() {
        int row = 0;
        String sql = "SELECT count(nid) FROM news;";
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
     * 根据编号查询新闻记录
     *
     * @param nid 新闻编号
     * @return 新闻实体
     */
    public News selectById(int nid) {
        News news = null;
        String sql = "SELECT nid, title, content, createtime FROM news WHERE nid = ?;";
        Object[] obj = {nid};
        rs = executeQuery(sql, obj);
        try {
            //第三步：获取执行对象    第四步：获取结果（结果集）
            rs = executeQuery(sql, obj);
            //第五步：输出结果
            while (rs.next()) {
                news = new News();
                news.setNid(rs.getInt("nid"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setCreatetime(rs.getTimestamp("createtime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return news;
    }

    /**
     * 尝试在news表中插入一条新闻记录
     *
     * @param news 要插入的新闻实体
     * @return 返回改变的行数
     */
    public int insertUser(News news) {
        String sql = "INSERT INTO news(title, content, createtime)VALUE(?, ?, now())";
        Object[] obj = {news.getTitle(), news.getContent()};
        int row = executeUpdate(sql, obj);
        return row;
    }

    /**
     * 尝试在news表中删除一条新闻记录
     *
     * @param news 要删除的新闻实体
     * @return 返回改变的行数
     */
    public int deleteUser(News news) {
        String sql = "DELETE FROM news WHERE nid = ?;";
        Object[] obj = {news.getNid()};
        int row = executeUpdate(sql, obj);
        return row;
    }

    /**
     * 尝试在news表中更新一条信息
     *
     * @param news 要更新的新闻实体（用户的nid不能更新）
     * @return 返回改变的行数
     */
    public int updateUser(News news) {
        String sql = "UPDATE news SET title = ?, content = ?, createtime=now() WHERE nid = ?;";
        Object[] obj = {news.getTitle(), news.getContent(), news.getNid()};
        int row = executeUpdate(sql, obj);
        return row;
    }
}
