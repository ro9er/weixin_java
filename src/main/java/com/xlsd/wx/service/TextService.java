package com.xlsd.wx.service;

import com.xlsd.wx.model.TextMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

@Service
public class TextService {

    public TextMessage doProcess(Map<String, String> msgMap){
        TextMessage message = new TextMessage();
        message.setContent(msgMap.get("Content"));
        message.setFromUserName(msgMap.get("ToUserName"));
        message.setMsgType("text");
        message.setToUserName(msgMap.get("FromUserName"));
        message.setCreateTime(System.currentTimeMillis());
        return message;
    }
}
