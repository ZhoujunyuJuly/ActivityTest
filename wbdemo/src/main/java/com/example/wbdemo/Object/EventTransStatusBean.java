package com.example.wbdemo.Object;

import com.example.wbdemo.Object.MainFgData.StatusesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoujunyu on 2019/5/24.
 */
public class EventTransStatusBean {
    private List<StatusesBean> mStatusesBean;

    public EventTransStatusBean(List<StatusesBean> mStatusesBean) {
        this.mStatusesBean = mStatusesBean;
    }

    public List<StatusesBean> getmStatusesBean() {
        return mStatusesBean;
    }

    public void setmStatusesBean(List<StatusesBean> mStatusesBean) {
        this.mStatusesBean = mStatusesBean;
    }
}
