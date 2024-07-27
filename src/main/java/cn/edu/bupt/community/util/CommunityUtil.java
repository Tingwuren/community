package cn.edu.bupt.community.util;

import org.springframework.util.DigestUtils;
import org.apache.commons.lang3.StringUtils;

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
}
