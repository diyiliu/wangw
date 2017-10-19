package com.diyiliu.nav.controller;

import com.diyiliu.nav.dao.NavDao;
import com.diyiliu.nav.model.SiteType;
import com.diyiliu.nav.model.Website;
import com.diyiliu.support.cache.ICache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Resource
    private ICache siteTypeCacheProvider;

    @RequestMapping(value = "/add")
    public @ResponseBody String add(Website site){
        String type = site.getType();
        if (siteTypeCacheProvider.containsKey(type)){
            SiteType siteType = (SiteType) siteTypeCacheProvider.get(type);
            navDao.insertWebSite(site, siteType);
        }

        return "success";
    }

    @RequestMapping("/")
    public String index(Model model) {
        List list = navDao.querySiteTypeList();
        model.addAttribute("typeList", list);

        return "index";
    }
}
