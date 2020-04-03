package com.example.wbdemo.event;

import com.example.wbdemo.info.maindata.StatusesBean;

import java.util.List;

/**
 * Created by zhoujunyu on 2019/5/24.
 */
public class StatusEvent {
    private List<StatusesBean> StatusesBean;


    public StatusEvent(List<StatusesBean> mStatusesBean) {
        this.StatusesBean = mStatusesBean;
    }

    public List<StatusesBean> getStatusesBean() {
        return StatusesBean;
    }

    public void setStatusesBean(List<StatusesBean> mStatusesBean) {
        this.StatusesBean = mStatusesBean;
    }
}
