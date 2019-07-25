package cn.gluttonous.hotel.dao;

import cn.gluttonous.hotel.entity.Food;
import cn.gluttonous.hotel.entity.FoodListEntity;
import cn.gluttonous.hotel.utils.PageBean;

import java.util.List;

/**
 * @title: hotel
 * @ClassName FoodDaoInterface.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/24
 * @Version: 1.0
 **/
public interface FoodDaoInterface {

    /**
     * 添加菜品
     * @param food
     */
    void add(Food food);

    /**
     * 删除菜品
     * @param id
     */
    void delete(int id);

    /**
     * 更新菜品
     * @param food
     */
    void update(Food food);

    /**
     * 所有菜品
     * @return
     */
    List<FoodListEntity> query();

    /**
     * 查找指定菜品
     * @param id
     * @return
     */
    Food findById(int id);

    /**
     * 模糊查询指定菜品
     * @param keyword
     * @return
     */
    List<Food> query(String keyword);

    /**
     * 查询指定菜系的所有菜品
     * @param type
     * @return
     */
    List<Food> findByType(int type);

    /**
     * 分页查询数据
     * @param foodPageBean
     */
    void getAll(PageBean<Food> foodPageBean);


    /**
     * 查询总记录数
     * @param foodPageBean
     * @return
     */
    int getTotalCount(PageBean<Food> foodPageBean);
}
