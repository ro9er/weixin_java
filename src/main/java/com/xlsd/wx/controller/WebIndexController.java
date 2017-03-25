package com.xlsd.wx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
@Controller
@RequestMapping("wx_web")
public class WebIndexController {

    @RequestMapping("index")
    public String index(){
        return "product-list";
    }

    @RequestMapping("item/{itemName}")
    public String product(@PathVariable("itemName")String itemName){
        return "product-item-" + itemName;
    }
}
