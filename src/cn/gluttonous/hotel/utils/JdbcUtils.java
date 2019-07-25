package cn.gluttonous.hotel.utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

/**
 * @title: hotel
 * @ClassName JdbcUtils.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/23
 * @Version: 1.0
 **/
public class JdbcUtils {
    private static DataSource dataSource;
    static {
        dataSource = new ComboPooledDataSource();
    }

    public static synchronized DataSource getDataSource(){
        return dataSource;
    }

    public static synchronized QueryRunner getQueryRunner(){
        return new QueryRunner(dataSource);
    }
}
