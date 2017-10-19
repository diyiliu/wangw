package com.diyiliu.nav.controller;

import com.diyiliu.nav.dao.NavDao;
import com.diyiliu.nav.model.Website;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: MainAction
 * Author: DIYILIU
 * Update: 2017-10-18 14:18
 */

@Controller
@RequestMapping(produces = "text/html;charset=UTF-8")
public class MainAction {

    @Resource
    private NavDao navDao;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody String add(Website site){
        System.out.println(site);

        return "success";
    }

    @RequestMapping("/")
    public String index(Model model) {
        List list = navDao.queryWebTypeList();
        model.addAttribute("typeList", list);

        return "index";
    }
}
