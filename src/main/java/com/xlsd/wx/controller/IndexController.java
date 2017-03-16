package com.xlsd.wx.controller;

import com.xlsd.wx.model.TextMessage;
import com.xlsd.wx.service.TextService;
import com.xlsd.wx.util.CheckUtil;
import com.xlsd.wx.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TextService textService;

    @RequestMapping(value = "wx", method = RequestMethod.GET)
    public void doGet(HttpServletResponse response,@RequestParam("signature")String signature,@RequestParam("timestamp")String timestamp,
    @RequestParam("nonce")String nonce, @RequestParam("echostr")String echostr) throws IOException {
        logger.info("signature:" + signature + " timestamp:" + timestamp + " nonce:"+ nonce + " ehcostr:" + echostr);
        if(CheckUtil.checkSignature(signature, timestamp, nonce)){
            response.getWriter().println(echostr);
            logger.info("check true");
        }
    }

    @RequestMapping(value = "wx", method = RequestMethod.POST)
    public void doPost(HttpServletResponse response, @RequestBody byte[] body) throws IOException {
        String xmlStr = new String(body);
        logger.info("recerive xml:" + xmlStr);
        Map<String, String> msgMap = MessageUtil.xmlToMap(xmlStr);
        TextMessage message = textService.doProcess(msgMap);
        String responseStr = MessageUtil.textMessageToXML(message);
        logger.info("response:" + responseStr);
        response.getWriter().println(responseStr);

    }
}
