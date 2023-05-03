package com.ybj.comments.service;

import com.ybj.comments.dao.CommentsDao;
import com.ybj.entity.Comments;

import java.util.List;

/**
 * 商品评论业务层
 */
public class CommentsService {

    //调用数据层
    CommentsDao nd = new CommentsDao();

    /**
     * 查询comments表的所有记录
     *
     * @return 记录列表
     */
    public List<Comments> queryAll() {
        List list = nd.selectAll();
        System.out.println(list.size());
        return list;
    }

}
