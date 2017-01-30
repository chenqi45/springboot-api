package com.crell.common.model;

import com.crell.core.persistence.DataEntity;

import java.util.Date;

/**
 * Created by crell on 2016/10/9.
 */
public class User extends DataEntity<User> {
    private String userName;
    private String email;
    private Date lastLoginDate;
    private String ip;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
