package com.example.wbdemo.event;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zhoujunyu on 2019/5/28.
 */
public class EventManager {


    private EventManager() {
        super();
    }

    public static EventManager getInstance(){
        return new EventManager();
    }

    public void register(Object o){
        EventBus.getDefault().register(o);
    }

    public void unregister(Object o){
        EventBus.getDefault().unregister(o);
    }

    public void postEvent(Object o){
        EventBus.getDefault().post(o);
    }
}
