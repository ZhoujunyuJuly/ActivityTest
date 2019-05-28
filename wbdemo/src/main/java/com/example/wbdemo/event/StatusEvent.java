package com.example.wbdemo.event;

import com.example.wbdemo.info.MainFgData.StatusesBean;

import java.util.List;

/**
 * Created by zhoujunyu on 2019/5/24.
 */
public class StatusEvent {
    private List<StatusesBean> mStatusesBean;

    public static StatusEvent getInstance(List<StatusesBean> mStatusesBean){
        return new StatusEvent(mStatusesBean);
    }

    private StatusEvent(List<StatusesBean> mStatusesBean) {
        this.mStatusesBean = mStatusesBean;
    }

    public List<StatusesBean> getmStatusesBean() {
        return mStatusesBean;
    }

    public void setmStatusesBean(List<StatusesBean> mStatusesBean) {
        this.mStatusesBean = mStatusesBean;
    }
}
