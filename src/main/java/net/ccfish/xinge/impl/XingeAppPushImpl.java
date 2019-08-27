package net.ccfish.xinge.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import com.tencent.xinge.api.RESTAPIV3;
import com.tencent.xinge.bean.AudienceType;
import com.tencent.xinge.bean.ClickAction;
import com.tencent.xinge.bean.Message;
import com.tencent.xinge.bean.MessageAndroid;
import com.tencent.xinge.bean.MessageType;
import com.tencent.xinge.bean.OpType;
import com.tencent.xinge.bean.Platform;
import com.tencent.xinge.bean.TagListObject;
import com.tencent.xinge.push.app.PushAppRequest;
import com.tencent.xinge.push.app.PushAppResponse;

import net.ccfish.xinge.XingeAppPush;
import net.ccfish.xinge.config.XingePushConfig;
import net.ccfish.xinge.utils.XingeAuthEncodeUtils;

/**
 * 处理推送
 * 
 * @author yuan'g
 * @version 1.0
 * @since 2019年8月27日
 */
@Component
public class XingeAppPushImpl implements XingeAppPush {

    @Autowired
    private XingePushConfig xingePushConfig;
    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(XingeAppPushImpl.class);
    
    private RESTAPIV3 restapiV3 =  new RESTAPIV3();
    
    @Override
    public PushAppResponse pushTokenAndroid(String title, String content, String token) {
        PushAppRequest pushAppRequest = new PushAppRequest();
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);

        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);
        pushAppRequest.setMessage(message);
        ClickAction action = new ClickAction();

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        message.setAndroid(messageAndroid);

        ArrayList<String> tokenList = new ArrayList<String>();
        tokenList.add(token);
        pushAppRequest.setToken_list(tokenList);
        
        return post(restapiV3.getRestApiPushUrl(), pushAppRequest, PushAppResponse.class);
    }

    @Override
    public PushAppResponse pushTokenListAndroid(String title, String content, ArrayList<String> tokens) {

        PushAppRequest pushAppRequest = new PushAppRequest();
        pushAppRequest.setAudience_type(AudienceType.token_list);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);

        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);
        ClickAction action = new ClickAction();

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        message.setAndroid(messageAndroid);

        pushAppRequest.setMessage(message);
        pushAppRequest.setToken_list(tokens);
        return post(restapiV3.getRestApiPushUrl(), pushAppRequest, PushAppResponse.class);
    }

    @Override
    public PushAppResponse pushAccountAndroid(String title, String content, String account) {
        PushAppRequest pushAppRequest = new PushAppRequest();
        pushAppRequest.setAudience_type(AudienceType.account);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setAccount_push_type(1);
        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);
        ClickAction action = new ClickAction();

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        message.setAndroid(messageAndroid);

        pushAppRequest.setMessage(message);

        ArrayList<String> accountList = new ArrayList<String>();
        accountList.add(account);
        pushAppRequest.setAccount_list(accountList);
        return post(restapiV3.getRestApiPushUrl(), pushAppRequest, PushAppResponse.class);
    }

    @Override
    public PushAppResponse pushAccountListAndroid(String title, String content, ArrayList<String> accounts) {
        PushAppRequest pushAppRequest = new PushAppRequest();
        pushAppRequest.setAudience_type(AudienceType.account_list);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);

        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);
        ClickAction action = new ClickAction();

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        message.setAndroid(messageAndroid);

        pushAppRequest.setMessage(message);
        pushAppRequest.setAccount_list(accounts);
        return post(restapiV3.getRestApiPushUrl(), pushAppRequest, PushAppResponse.class);
    }

    @Override
    public PushAppResponse pushAllAndroid(String title, String content) {
        PushAppRequest pushAppRequest = new PushAppRequest();
        pushAppRequest.setAudience_type(AudienceType.all);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);

        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);
        ClickAction action = new ClickAction();


        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        message.setAndroid(messageAndroid);
        pushAppRequest.setMessage(message);
        return post(restapiV3.getRestApiPushUrl(), pushAppRequest, PushAppResponse.class);
    }

    @Override
    public PushAppResponse pushTagAndroid(String title, String content, String tag) {
        PushAppRequest pushAppRequest = new PushAppRequest();
        pushAppRequest.setAudience_type(AudienceType.tag);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);

        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);
        ClickAction action = new ClickAction();

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        message.setAndroid(messageAndroid);
        pushAppRequest.setMessage(message);

        ArrayList<String> tagList = new ArrayList<String>();
        tagList.add(tag);
        TagListObject tagListObject = new TagListObject();
        tagListObject.setTags(tagList);
        tagListObject.setOp(OpType.OR);

        pushAppRequest.setTag_list(tagListObject);
        return post(restapiV3.getRestApiPushUrl(), pushAppRequest, PushAppResponse.class);
    }

    /**
     * 发起POST请求
     * @param url URL
     * @param request 请求内容
     * @param responseType 结果类型
     * @param uriVariables URL参数
     * @return 
     */
    private <T> T post(String url, @Nullable PushAppRequest request,
            Class<T> responseType, Object... uriVariables) {
        String authStringEnc = XingeAuthEncodeUtils.getAuthString(xingePushConfig.getAndroid().getAppid(),
                xingePushConfig.getAndroid().getSecretkey());
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json");
        header.add("Authorization", "Basic " + authStringEnc);
        
        HttpEntity<PushAppRequest> httpEntity = new HttpEntity<>(request, header);
        ResponseEntity<T> response = restTemplate.postForEntity(url, httpEntity, responseType, uriVariables);

        logger.debug(" {}", response.getBody());
        return response.getBody();
    }

}
