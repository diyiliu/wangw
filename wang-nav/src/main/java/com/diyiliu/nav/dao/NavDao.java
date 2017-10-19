package com.diyiliu.nav.dao;

import com.diyiliu.nav.model.SiteType;
import com.diyiliu.nav.model.Website;
import com.diyiliu.support.cache.ICache;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.util.List;

/**
 * Description: NavDao
 * Author: DIYILIU
 * Update: 2017-10-18 15:37
 */

@Component
public class NavDao {

    @Resource
    private ICache siteTypeCacheProvider;

    @Resource
    private JdbcTemplate jdbcTemplate;

    public boolean insertWebSite(Website website, SiteType siteType) {
       String sql = "INSERT INTO nav_site(`NAME`,URL,TYPE) VALUES ('" +
                website.getName() + "', '" +
                website.getUrl() + "', " +
                siteType.getId() + ")";

        jdbcTemplate.execute(sql);

        /*String sql = "INSERT INTO nav_site(type)VALUES (?)";
        Object params = new Object[]{siteType.getId()};
        if (jdbcTemplate.update(sql, params) > 0){
            return true;
        }*/

        return false;
    }

    public List<SiteType> querySiteTypeList() {

        String sql = "SELECT t.id, t.name, t.top FROM nav_type t order by t.top";

        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            SiteType type = new SiteType();
            type.setId(rs.getInt("id"));
            type.setName(rs.getString("name"));

            siteTypeCacheProvider.put(type.getName(), type);

            return type;
        });
    }
}
