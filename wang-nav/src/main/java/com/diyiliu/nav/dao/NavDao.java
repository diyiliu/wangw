package com.diyiliu.nav.dao;

import com.diyiliu.nav.model.SiteType;
import com.diyiliu.nav.model.Website;
import com.diyiliu.support.cache.ICache;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private QueryRunner queryRunner;

    public void insertWebSite(Website website, SiteType siteType) throws SQLException {
        String sql = "INSERT INTO nav_site(name,url,type)VALUES (?,?,?)";
        Object[] params = new Object[]{website.getName(), website.getUrl(), siteType.getId()};

        queryRunner.execute(sql, params);
    }

    public List<SiteType> querySiteTypeList() throws SQLException {

        String sql = "SELECT t.id, t.name, t.top FROM nav_type t order by t.top";

        List<SiteType> list = new ArrayList();
        queryRunner.query(sql, (ResultSet rs) -> {
            while (rs.next()) {
                SiteType type = new SiteType();
                type.setId(rs.getInt("id"));
                type.setName(rs.getString("name"));
                siteTypeCacheProvider.put(type.getName(), type);

                list.add(type);
            }
            return null;
        });

        return list;
    }
}
