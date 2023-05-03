package com;

import com.ybj.comments.dao.CommentsDao;
import com.ybj.comments.service.CommentsService;
import com.ybj.news.dao.NewsDao;
import com.ybj.product.dao.ProductDao;

import com.ybj.news.service.NewsService;
import com.ybj.product.service.ProductService;
import com.ybj.users.dao.UsersDao;
import com.ybj.users.service.UsersService;

public class Test {


    public static void main(String[] args) {

        CommentsDao cd = new CommentsDao();
        ProductDao pd = new ProductDao();
        NewsDao nd = new NewsDao();
        UsersDao ud = new UsersDao();
        CommentsService cs = new CommentsService();
        ProductService ps = new ProductService();
        NewsService ns = new NewsService();
        UsersService us = new UsersService();
        //cd.insert();
        //cd.delete();
        //cd.selectAll();
        //cd.update();
        pd.insert();
        //pd.delete();
        //pd.selectAll();
        //pd.update();
        //nd.insert();
        //nd.delete();
        //nd.selectAll();
        //nd.update();
        //ud.insert();
        //ud.delete();
        //ud.selectAll();
        //ud.update();
        //cs.queryAll();
        //ps.queryAll();
        //ns.queryAll();
        //us.queryAll();
    }
}


/*
import com.ybj.news.dao.NewsDao;
import com.ybj.users.dao.UsersDao;

import java.util.Scanner;

public class Test {

    static Scanner sc = new Scanner(System.in);
    static UsersDao ud = new UsersDao();
    static NewsDao nd = new NewsDao();

    public static void main(String[] args) {

        int choseOfTable = 0;//表选择变量：1-users表，2-news表，3-product表，4-comments表，5-退出系统
        int choseOfWork = 0;//操作选择变量：1-增，2-删，3-查，4-改, 5-返回上级目录, 6-退出系统
        while (choseOfTable != 5) {
            menuOfTable();   //择表菜单
            while (choseOfTable < 1 || choseOfTable > 5)//保证输入合法
                choseOfTable = sc.nextInt();
            if (choseOfTable == 5) {
                break;
            } else if (choseOfTable == 1) {
                //操作users表
                if (workUsers(choseOfTable, choseOfWork) == 6) {
                    choseOfTable = 5;//6-直接退出，只用重置表选择变量就好
                } else {
                    choseOfTable = 0;//5-返回上级操作，两个都要重置
                    choseOfWork = 0;
                }
            } else if (choseOfTable == 2) {
                //操作news表
                if (workNews(choseOfTable, choseOfWork) == 6) {
                    choseOfTable = 5;
                } else {
                    choseOfTable = 0;
                    choseOfWork = 0;
                }
            } else if (choseOfTable == 3) {
                //操作product表
                if (workProduct(choseOfTable, choseOfWork) == 6) {
                    choseOfTable = 5;
                } else {
                    choseOfTable = 0;
                    choseOfWork = 0;
                }
            } else if (choseOfComments == 2) {
                //操作comments表
                if (workComments(choseOfTable, choseOfWork) == 6) {
                    choseOfTable = 5;
                } else {
                    choseOfTable = 0;
                    choseOfWork = 0;
                }
            }
        }
        System.out.println("操作完毕，测试退出");
    }


    public static void menuOfTable() {
        System.out.println("\n\n--------\t\t请选择要操作的表\t\t--------");
        System.out.println("1.users表");
        System.out.println("2.news表");
        System.out.println("3.product表");
        System.out.println("4.comments表");
        System.out.println("5.退出测试");
    }

    public static void menuOfWork(int choiceOfTable) {
        if (choiceOfTable == 1) {
            System.out.println("\n--------\t\t请选择要对users表进行的操作\t\t--------");
        }else if(choiceOfTable == 2){
            System.out.println("\n--------\t\t请选择要对news表进行的操作\t\t--------");
        }
        System.out.println("1.增（插入一条记录）");
        System.out.println("2.删（删除一条记录）");
        System.out.println("3.查（查询所有记录）");
        if (choiceOfTable == 1)
            System.out.println("4.改（更改一条记录的密码）");
        else {
            System.out.println("4.改（更改一条新闻的内容）");
        }
        System.out.println("5.返回上一级菜单");
        System.out.println("6.退出测试");
    }

    public static int workUsers(int choseOfTable, int choseOfWork) {
        while (choseOfWork != 5 && choseOfWork != 6) {
            menuOfWork(choseOfTable);//users表操作菜单
            choseOfWork = sc.nextInt();
            while (choseOfWork < 1 || choseOfWork > 6)//保证输入合法
                choseOfWork = sc.nextInt();
            switch (choseOfWork) {
                case 1:
                    ud.insert();
                    break;
                case 2:
                    ud.delete();
                    break;
                case 3:
                    ud.show();
                    break;
                case 4:
                    ud.update();
                    break;
            }
        }
        return choseOfWork;//返回5或者6
    }

    public static int workNews(int choseOfTable, int choseOfWork) {
        while (choseOfWork != 5 && choseOfWork != 6) {
            menuOfWork(choseOfTable);//news表操作菜单
            choseOfWork = sc.nextInt();
            if (choseOfWork < 1 || choseOfWork > 6)//保证输入合法
                choseOfWork = sc.nextInt();
            switch (choseOfWork) {
                case 1:
                    nd.insert();
                    break;
                case 2:
                    nd.delete();
                    break;
                case 3:
                    nd.show();
                    break;
                case 4:
                    nd.update();
                    break;
            }
        }
        return choseOfWork;//返回5或者6
    }
}
*/