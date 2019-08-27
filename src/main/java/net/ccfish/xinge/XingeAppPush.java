package net.ccfish.xinge;

import java.util.ArrayList;

import com.tencent.xinge.push.app.PushAppResponse;

/**
 * 信鸽推送
 * 
 * @author yuan'gui
 * @version 1.0
 * @since 2019年8月27日
 */
public interface XingeAppPush {
    /**
     * android Token 单推
     * @param title
     * @param content
     * @param token
     * @return
     */
    PushAppResponse pushTokenAndroid(String title, String content, String token);

    /**
     * android 设备列表推送
     * @param title
     * @param content
     * @param tokens
     * @return
     */
    PushAppResponse pushTokenListAndroid(String title, String content, ArrayList<String> tokens);
    
    /**
     * android 账号单推
     * @param title
     * @param content
     * @param account
     * @return
     */
    PushAppResponse pushAccountAndroid(String title, String content, String account);
    
    /**
     * android  账号列表推送
     * @param title
     * @param content
     * @param accounts
     * @return
     */
    PushAppResponse pushAccountListAndroid(String title, String content, ArrayList<String> accounts);

    /**
     * android  全推
     * @param title
     * @param content
     * @return
     */
    PushAppResponse pushAllAndroid(String title,String content);
    
    /**
     * android  标签推送
     * @param title
     * @param content
     * @param tag
     * @return
     */
    PushAppResponse pushTagAndroid(String title,String content,String tag);

}
