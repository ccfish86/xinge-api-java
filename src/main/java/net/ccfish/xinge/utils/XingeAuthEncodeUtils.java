package net.ccfish.xinge.utils;

import org.apache.commons.codec.binary.Base64;

public class XingeAuthEncodeUtils {

    /**
     * HTTP Header Authorization 的值：Basic base64_auth_string<br>
     * base64_auth_string 生成规则是：base64(appId:secretKey)<br>
     * 留意 appId 与 secretKey 中间使用 ":" 冒号隔开<br>
     *
     * @param appId     appId
     * @param secretKey secretKey
     */
    public static String getAuthString(String appId, String secretKey) {
        String authString = appId + ":" + secretKey;
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        return new String(authEncBytes);
    }
}
