package com.ybj.comments.dao;

import com.ybj.entity.Comments;
import com.ybj.util.BaseDao;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 评论数据层
 */
public class CommentsDao extends BaseDao {

    Scanner input = new Scanner(System.in);

    /**
     * 向comments表中增加一条记录
     */
    public void insert() {
        System.out.println("请输入一行的值（评论内容，评论时间，商品编号，买家昵称）：");
        Object[] obj = new Object[4];
        int i = 0;
        while (i < 4) {
            obj[i] = input.next();
            i++;
        }
        //第一步：加载驱动 第二步：获取连接对象
        conn = getConnection();
        //第三步：获取执行对象 第四步：获取结果（受影响的行数）
        String sql = "INSERT INTO comments(content, createtime, pid, nickname)VALUES(?, ?, ?, ?);";
        int row = executeUpdate(sql, obj);
        if (row != 0) {
            System.out.println("插入内容为“" + obj[0] + "”的商品评论成功！");
        } else {
            System.out.println("插入失败！");
        }
    }

    /**
     * 删除comments表中的一条记录
     */
    public void delete() {
        System.out.print("请输入要删除的评论的编号：");
        String cid = input.next();
        Object[] obj = {cid};
        //第一步：加载驱动  第二步：获取连接对象
        conn = getConnection();
        //第三步：获取执行对象    第四步：获取结果（受影响的行数）
        String sql = "DELETE FROM comments WHERE cid = ?;";
        int row = executeUpdate(sql, obj);
        //第五步：输出结果
        if (row != 0) {
            System.out.println("删除编号：" + cid + "成功！");
        } else {
            System.out.println("删除失败！");
        }
    }

    /**
     * 查询comments表中所有记录
     *
     * @return 记录列表
     */
    public List<Comments> selectAll() {
        List list = new ArrayList();
        Object[] obj = null;
        try {
            //第三步：获取执行对象    第四步：获取结果（结果集）
            String sql = "SELECT cid, content, createtime, pid, nickname FROM comments;";
            rs = executeQuery(sql, obj);
            //第五步：输出结果
            while (rs.next()) {
                Comments comments = new Comments();
                comments.setCid(rs.getInt("cid"));
                comments.setContent(rs.getString("content"));
                comments.setCreatetime(rs.getTimestamp("createtime"));
                comments.setPid(rs.getInt("pid"));
                comments.setNickname(rs.getString("nickname"));
                list.add(comments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * 修改comments表的的一条记录
     */
    public void update() {
        System.out.print("请输入评论编号：");
        String cid = input.next();
        System.out.print("\n请输入新内容:");
        String content = input.next();
        Object[] obj = {content, cid};
        //第一步：加载驱动  第二部：获取连接对象
        conn = getConnection();
        //第三步：获取执行对象 第四步：获取结果（受影响的行数）
        String sql = "UPDATE comments SET content = ? WHERE cid = ?;";
        int row = executeUpdate(sql, obj);
        //第五步：输出结果
        if (row != 0) {
            System.out.println("修改编号" + cid + "的评论内容成功！");
        } else {
            System.out.println("修改失败！");
        }
    }
}
