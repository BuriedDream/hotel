package cn.gluttonous.hotel.service;

import cn.gluttonous.hotel.entity.Food;
import cn.gluttonous.hotel.entity.FoodListEntity;
import cn.gluttonous.hotel.utils.PageBean;

import java.util.List;

/**
 * @title: hotel
 * @ClassName FoodServiceInterface.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/25
 * @Version: 1.0
 **/
public interface FoodServiceInterface {

    /**
     * 添加菜品
     * @param food
     */
    void add(Food food);

    /**
     * 删除指定菜品
     * @param id
     */
    void delete(int id);

    /**
     * 更新指定菜品
     * @param food
     */
    void update(Food food);

    /**
     * 得到分页
     * @param pageBean
     */
    void getAll(PageBean<Food> pageBean);

    /**
     * 后台的到分页
     * @param pageBean
     */
    void getListAll(PageBean<FoodListEntity> pageBean);

    /**
     * 获取指定菜品
     * @param id
     * @return
     */
    Food getById(int id);

    /**
     * 得到指定菜系的菜品
     * @param type
     * @return
     */
    List<Food> getByType(int type);

    /**
     * 根据名字进行模糊查询
     * @param keyName
     * @return
     */
    List<FoodListEntity> query(String keyName);

    /**
     * 模糊查询分页
     * @param pageBean
     * @param keyword
     */
    void query(PageBean<FoodListEntity> pageBean, String keyword);
    /**
     * 查找所有菜品
     * @return
     */
    List<FoodListEntity> query();
}
