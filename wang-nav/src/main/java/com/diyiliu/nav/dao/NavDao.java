package com.diyiliu.nav.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description: NavDao
 * Author: DIYILIU
 * Update: 2017-10-18 15:37
 */

@Component
public class NavDao {

    @Resource
    private JdbcTemplate jdbcTemplate;


}
