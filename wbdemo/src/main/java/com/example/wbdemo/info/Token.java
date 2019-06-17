package com.example.wbdemo.info;

import java.io.Serializable;

/**
 * Created by zhoujunyu on 2019/5/21.
 */
public class Token implements Serializable {

    /**
     * access_token : 2.00U1XGpF0olLDg992dbd1487pHDnXE
     * remind_in : 157679999
     * expires_in : 157679999
     * uid : 5335815386
     * isRealName : true
     */

    private String access_token;
    private String remind_in;
    private int expires_in;
    private String uid;
    private String isRealName;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRemind_in() {
        return remind_in;
    }

    public void setRemind_in(String remind_in) {
        this.remind_in = remind_in;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(String isRealName) {
        this.isRealName = isRealName;
    }
}
