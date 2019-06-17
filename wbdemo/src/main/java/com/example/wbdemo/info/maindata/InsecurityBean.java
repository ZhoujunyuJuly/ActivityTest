package com.example.wbdemo.info.maindata;

import java.io.Serializable;

/**
 * Created by zhoujunyu on 2019/5/23.
 */
public class InsecurityBean implements Serializable {
    /**
     * sexual_content : false
     */

    private boolean sexual_content;

    public boolean isSexual_content() {
        return sexual_content;
    }

    public void setSexual_content(boolean sexual_content) {
        this.sexual_content = sexual_content;
    }
}
