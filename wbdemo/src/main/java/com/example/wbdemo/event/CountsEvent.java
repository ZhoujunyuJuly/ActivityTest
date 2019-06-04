package com.example.wbdemo.event;

import java.util.List;

/**
 * Created by zhoujunyu on 2019/6/4.
 */
public class CountsEvent {

    private static List<Integer> counts;

    public static CountsEvent getInstance(List<Integer> count) {
        counts = count;
        return new CountsEvent();
    }

    public List<Integer> getCounts() {
        return counts;
    }

    public void setCounts(List<Integer> counts) {
        this.counts = counts;
    }
}
