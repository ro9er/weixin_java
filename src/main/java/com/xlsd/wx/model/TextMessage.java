package com.xlsd.wx.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xlsd.wx.annotation.XStreamCDATA;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/16 0016.
 */
@XStreamAlias("xml")
public class TextMessage implements Serializable{

    @XStreamAlias("ToUserName")
    @XStreamCDATA
    private String toUserName;

    @XStreamAlias("FromUserName")
    @XStreamCDATA
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private Long createTime;

    @XStreamAlias("MsgType")
    @XStreamCDATA
    private String msgType;

    @XStreamAlias("Content")
    @XStreamCDATA
    private String content;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
