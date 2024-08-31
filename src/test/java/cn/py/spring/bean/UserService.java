package cn.py.spring.bean;

public class UserService {
    private String userName;
    private String userInfo;

    public UserService(String userName, String userInfo) {
        this.userName = userName;
        this.userInfo = userInfo;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserInfo() {
        return this.userInfo;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
