package com.diyiliu.nav.dao;

import com.diyiliu.nav.model.WebType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Description: NavDao
 * Author: DIYILIU
 * Update: 2017-10-18 15:37
 */

@Component
public class NavDao {

    @Resource
    private JdbcTemplate jdbcTemplate;


    public List<WebType> queryWebTypeList(){

        String sql = "SELECT t.id, t.name, t.top FROM nav_type t order by t.top";

        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) ->{
            WebType type = new WebType();
            type.setId(rs.getInt("id"));
            type.setName(rs.getString("name"));

            return type;
        });
    }
}
