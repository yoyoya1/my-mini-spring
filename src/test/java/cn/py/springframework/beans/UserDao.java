package cn.py.springframework.beans;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "py");
        hashMap.put("10002", "py1");
        hashMap.put("10003", "py2");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}

