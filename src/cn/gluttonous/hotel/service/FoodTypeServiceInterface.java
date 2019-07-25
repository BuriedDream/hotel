package cn.gluttonous.hotel.service;

import cn.gluttonous.hotel.entity.FoodType;

import java.util.List;

/**
 * @title: hotel
 * @ClassName FoodTypeServiceInterface.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/23
 * @Version: 1.0
 **/
public interface FoodTypeServiceInterface {

    /**
     * 添加菜系
     * @param foodType
     */
    void save(FoodType foodType);

    /**
     * 更新菜系名
     * @param foodType
     */
    void update(FoodType foodType);

    /**
     * 删除菜系
     * @param id
     */
    void delete(int id);

    /**
     * 根据ID查询菜系
     * @param id
     * @return
     */
    FoodType findById(int id);

    /**
     * 查找所有菜系名
     * @return
     */
    List<FoodType> getAll();

    /**
     * 根据菜系名称查询
     * @param typeName
     * @return List
     */
    List<FoodType> getAll(String typeName);
}
