package com.dongpu.model;

/**
 *
 * @author wy105
 */
public class User {

    private String userId; //   -- 用户ID
    private String userPwd; //  -- 用户密码(MD5 加密 密码是 admin)
    private String userName; // -- 用户姓名

    public User() {
    }

    
    public User(String userId, String userPwd, String userName) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
