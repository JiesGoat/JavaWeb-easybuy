package com.ybj.util;

import java.sql.*;

/**
 * 数据库工具类
 */
public class BaseDao {

    public ResultSet rs = null;//结果集
    public PreparedStatement pstmt = null;//执行对象
    public Connection conn = null;//连接对象

    /**
     * 加载驱动，建立连接
     *
     * @return 连接对象
     */
    public Connection getConnection() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/course_design?useUnicode=true&characterEncoding=UTF-8";
        String userName = "ybj";
        String password = "root2003";
        //数据库连接步骤：alt + enter（提示）
        try {
            //第一步：加载驱动
            Class.forName(driver);
            //第二部：获取连接对象
            conn = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 逆向关闭所有对象
     *
     * @param rs    结果集
     * @param pstmt 执行对象
     * @param conn  连接对象
     */
    public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行增、删、改操作
     *
     * @param sql 执行语句
     * @param obj 参数数组
     * @return 影响的行数
     */
    public int executeUpdate(String sql, Object[] obj) {
        int row = 0;
        conn = getConnection();
        try {
            //第三步：获取执行对象    第四步：获取结果（受影响的行数）
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                pstmt.setObject(i + 1, obj[i]);
            }
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return row;
    }

    /**
     * 执行查操作
     *
     * @param sql sql语句
     * @param obj 输入数组
     * @return 结果集
     */
    public ResultSet executeQuery(String sql, Object[] obj) {
        conn = getConnection();
        try {
            //第三步：获取执行对象    第四步：获取结果（结果集）
            pstmt = conn.prepareStatement(sql);
            if (obj != null)
                for (int i = 0; i < obj.length; i++) {
                    pstmt.setObject(i + 1, obj[i]);
                }
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}

