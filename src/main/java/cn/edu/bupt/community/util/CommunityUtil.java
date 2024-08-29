package cn.edu.bupt.community.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class CommunityUtil {

    // 生成随机字符串
    public static String generateUUID() {
        return java.util.UUID.randomUUID().toString().replaceAll("-", "");
    }

    // MD5加密
    // 加密是唯一的
    // 需要一个随机字符串
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    public static String getJSONString(int code, String msg, Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        if (map != null) {
            for (String key : map.keySet()) {
                jsonObject.put(key, map.get(key));
            }
        }
        return jsonObject.toJSONString();
    }

    public static String getJSONString(int code, String msg) {
        return getJSONString(code, msg, null);
    }

    public static String getJSONString(int code) {
        return getJSONString(code, null, null);
    }

    // test
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "ZhangSan");
        map.put("age", "25");
        System.out.println(getJSONString(0, "ok", map));
    }
}
