package luoma.test_cms.common.Util;

import org.springframework.util.DigestUtils;

public class StringUtil {
    public static String MD5(String text) {
        return DigestUtils.md5DigestAsHex(text.getBytes());
    }

    public static String getRandString() {
        String rtn = "";
        for (int i = 0; i < 10; i++) {
            rtn = MD5(rtn + Math.random());
        }
        return rtn;
    }
}
