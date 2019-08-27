package net.ccfish.xinge.config;

/**
 * 腾讯信鸽的配置
 * 
 * @author yuan'gui
 * @version 1.0
 * @since 2019年8月27日
 */
public interface XingePushConfig {

    default XingeAndroid getAndroid() {
        return new XingeAndroid() {
            @Override
            public String getAppid() {
                return "03eed9f248f94";
            }
            @Override
            public String getSecretkey() {
                return "55bbe5adf3f029b654c6f76d55908ca6";
            }
        };
    }

    default String getUrl() {
        return "https://openapi.xg.qq.com/";
    }
}
