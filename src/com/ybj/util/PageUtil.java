package com.ybj.util;

import java.util.List;

/**
 * 分页工具类
 */
public class PageUtil {
    //首页
    private int first = 1;
    //上一页
    private int back;
    //下一页
    private int next;
    //尾页
    private int last;
    //当前页
    private int now;
    //总页数
    private int total;
    //每页显示的行数
    private int pageSize = 8;
    //每页显示的数据
    private List list;

    /**
     * 无参显式构造方法
     */
    public PageUtil() {

    }
    /**
     * 构造方法：声明对象时，初始化
     *
     * @param count 对象总行数
     * @param index 当前页数
     * @param size  每页显示的对象行数
     */
    public PageUtil(int count, int index, int size) {
        //如果传入的参数不为零，改变每页显示的行数
        if (size != 0) {
            this.pageSize = size;
        }
        //总页数
        if (count % pageSize == 0) {
            total = count / pageSize;
        } else {
            total = count / pageSize + 1;
        }
        //当前页
        if (index < 1 || index > total) {//非法页数
            this.now = 1;
        } else {
            this.now = index;
        }
        //上一页
        if (now > 1) {
            back = now - 1;
        } else {
            back = 1;
        }
        //尾页
        last = total;
        //下一页
        if (now < last) {
            next = now + 1;
        } else {
            next = last;
        }

    }

    public int getFirst() {
        return first;
    }

    public int getBack() {
        return back;
    }

    public int getNext() {
        return next;
    }

    public int getLast() {
        return last;
    }

    public int getNow() {
        return now;
    }

    public int getTotal() {
        return total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List getList() {
        return list;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public void setBack(int back) {
        this.back = back;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setList(List list) {
        this.list = list;
    }
}
