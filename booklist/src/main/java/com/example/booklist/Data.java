package com.example.booklist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyuqiao on 2019/3/6.
 */
public class Data {

    private static List<Book> dataList;

    public static List<Book> makeFakeData() {

        if (dataList == null) {
            dataList = new ArrayList<>();
        }

        if (dataList.size() <= 0) {

            Book first = new Book(R.mipmap.ic_launcher,"MySQL是怎样运行的：从根儿上理解MYSQL",
                    "小孩子4919","25小节","1257人购买","¥29.9");
            Book second = new Book(R.mipmap.ic_launcher,"基于Three JS框架的魔方微信小游戏实践",
                    "NewbieYoung","10小节","467人购买","¥9.9");
            Book third = new Book(R.mipmap.ic_launcher,"前端面试之道",
                    "yck","36小节","6245人购买","¥49.9");
            Book forth = new Book(R.mipmap.ic_launcher,"Kubernetes从上手到实践",
                    "TaoBeier","24小节","1073人购买","¥19.9");
            Book fifth = new Book(R.mipmap.ic_launcher,"Vue.js组件精讲",
                    "Aresn","20小节","2871人购买","¥29.9");
            Book sixth = new Book(R.mipmap.ic_launcher,"React实战：设计模式和最佳实践",
                    "程墨","21小节","1523人购买","¥29.9");
            Book seventh = new Book(R.mipmap.ic_launcher,"Vue项目构建与开发入门",
                    "劳卜","17小节","2953人购买","¥9.9");
            Book eighth = new Book(R.mipmap.ic_launcher,"前端性能优化原理与实践",
                    "修言","15小节","5228人购买","¥19.9");
            Book ninth = new Book(R.mipmap.ic_launcher,"Redis深度历险：核心原理与应用实践",
                    "老钱","45小节","18429人购买","¥19.9");
            Book tenth = new Book(R.mipmap.ic_launcher,"程序员小白书——如何规划和经营你的职业",
                    "Easy","14小节","1554人购买","¥29.9");

            dataList.add(first);
            dataList.add(second);
            dataList.add(third);
            dataList.add(forth);
            dataList.add(fifth);
            dataList.add(sixth);
            dataList.add(seventh);
            dataList.add(eighth);
            dataList.add(ninth);
            dataList.add(tenth);
        }
        return dataList;

    }

}
