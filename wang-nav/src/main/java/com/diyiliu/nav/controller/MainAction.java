package com.diyiliu.nav.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description: MainAction
 * Author: DIYILIU
 * Update: 2017-10-18 14:18
 */

@Controller
@RequestMapping(produces = "text/html;charset=UTF-8")
public class MainAction {

    @RequestMapping(value = "/display", method = RequestMethod.POST)
    public void display() {

    }
}
