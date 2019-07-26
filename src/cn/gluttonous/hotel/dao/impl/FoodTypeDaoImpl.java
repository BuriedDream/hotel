package cn.gluttonous.hotel.dao.impl;

import cn.gluttonous.hotel.dao.FoodTypeDaoInterface;
import cn.gluttonous.hotel.entity.FoodType;
import cn.gluttonous.hotel.utils.JdbcUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @title: hotel
 * @ClassName FoodTypeDaoImpl.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/23
 * @Version: 1.0
 **/
public class FoodTypeDaoImpl implements FoodTypeDaoInterface {
    /**
     * 添加菜系
     *
     * @param foodType
     */
    @Override
    public void save(FoodType foodType) {
        String sql_save = "INSERT INTO foodType(typeName) VALUES(?)";
        try {
            JdbcUtils.getQueryRunner().update(sql_save,foodType.getTypeName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除${id}菜系
     *
     * @param id
     */
    @Override
    public void delete(int id) {

        String sql_delete = "DELETE FROM foodType WHERE id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql_delete,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 更新菜系
     *
     * @param foodType
     */
    @Override
    public void update(FoodType foodType) {

        String sql_update = "UPDATE foodType SET typeName=? WHERE id=?";

        try {
            JdbcUtils.getQueryRunner().update(sql_update,foodType.getTypeName(),foodType.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过${id}查询菜系
     *
     * @param id
     * @return FoodType
     */
    @Override
    public FoodType findById(int id) {
        String sql_findById = "SELECT * FROM foodType WHERE id=?";
        try {
            return JdbcUtils.getQueryRunner().query(sql_findById,new BeanHandler<FoodType>(FoodType.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有菜系
     *
     * @return
     */
    @Override
    public List<FoodType> getAll() {
        String sql_findById = "SELECT * FROM foodType";
        try {
            return JdbcUtils.getQueryRunner().query(sql_findById,new BeanListHandler<FoodType>(FoodType.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据输入查询菜系
     *
     * @param typeName
     * @return
     */
    @Override
    public List<FoodType> getAll(String typeName) {
        String sql_findById = "SELECT * FROM foodType WHERE typeName LIKE ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql_findById,new BeanListHandler<FoodType>(FoodType.class),"%"+typeName+"%");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
