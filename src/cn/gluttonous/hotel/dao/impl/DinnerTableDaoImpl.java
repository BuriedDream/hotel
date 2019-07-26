package cn.gluttonous.hotel.dao.impl;

import cn.gluttonous.hotel.dao.DinnerTableDaoInterface;
import cn.gluttonous.hotel.entity.DinnerTable;
import cn.gluttonous.hotel.utils.JdbcUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @title: hotel
 * @ClassName DinnerTableDaoImpl.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/24
 * @Version: 1.0
 **/
public class DinnerTableDaoImpl implements DinnerTableDaoInterface {
    /**
     * 添加餐桌
     *
     * @param dinnerTable
     */
    @Override
    public void add(DinnerTable dinnerTable) {
        String sql_insert = "INSERT INTO dinnerTable(tableName) VALUES(?)";

        try {
            JdbcUtils.getQueryRunner().update(sql_insert,dinnerTable.getTableName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除指定餐桌
     *
     * @param id 指定餐桌
     */
    @Override
    public void delete(int id) {

        String sql_delete = "DELETE FROM dinnerTable WHERE id=?";

        try {
            JdbcUtils.getQueryRunner().update(sql_delete,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 更新餐桌
     *
     * @param dinnerTable
     */
    @Override
    public void update(DinnerTable dinnerTable) {

        String sql_update = "UPDATE dinnerTable SET tableName=?,tableStatus=?,orderDate=? WHERE id=?";

        try {
            JdbcUtils.getQueryRunner().update(sql_update,
                    dinnerTable.getTableName(),
                    dinnerTable.getTableStatus(),
                    dinnerTable.getOrderDate(),
                    dinnerTable.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查找所有餐桌
     *
     * @return
     */
    @Override
    public List<DinnerTable> query() {

        String sql_query = "SELECT * FROM dinnerTable";
        try {
            return JdbcUtils.getQueryRunner().query(sql_query,new BeanListHandler<DinnerTable>(DinnerTable.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取指定餐桌
     *
     * @param id 指定餐桌
     * @return 得到指定餐桌
     */
    @Override
    public DinnerTable findById(int id) {

        String sql_findById = "SELECT * FROM dinnerTable WHERE id=?";

        try {
            return JdbcUtils.getQueryRunner().query(sql_findById,new BeanHandler<DinnerTable>(DinnerTable.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据keyWord进行模糊查询
     *
     * @param keyWord
     * @return
     */
    @Override
    public List<DinnerTable> query(String keyWord) {
        String sql_query = "SELECT * FROM dinnerTable WHERE tableName LIKE ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql_query,new BeanListHandler<DinnerTable>(DinnerTable.class),"%"+keyWord+"%");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
