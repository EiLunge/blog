/**
 * All rights Reserved, Designed By www.sunxung.com
 * 
 * @Title:TestController.java
 * @Package com.sx.blog.admin.controller
 * @Description:TODO用一句话描述该文件做什么
 * @Author:qing
 * @Date:2019年12月28日 上午10:15:19
 * @version V1.0
 * @Copyright: 2019 www.sunxung.com Inc. All rights reserved.
 */
package com.sx.blog.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName:TestController
 * @Description:TODO这里用一句话描述这个类的作用
 * @Author:王庆之
 * @E-mail:122058102@qq.com
 * @Date:2019年12月28日 上午10:15:19
 */
@RequestMapping("test")
@Controller
public class TestController {

    @RequestMapping("viewA")
    public String testView(Model model) {
        Map<String, String> user = new HashMap<>();
        user.put("name", "张三");
        model.addAllAttributes(user);
        return "test/viewA";
    }

    @RequestMapping("viewB")
    public ModelAndView testViewAA() {
        ModelAndView modeView = new ModelAndView();
        modeView.setViewName("test/viewB");
        Map<String, String> user = new HashMap<>();
        user.put("name", "张三");
        modeView.addObject("user", user);
        return modeView;
    }
}
