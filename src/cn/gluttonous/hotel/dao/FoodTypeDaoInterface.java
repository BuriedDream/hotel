package cn.gluttonous.hotel.dao;

import cn.gluttonous.hotel.entity.FoodType;

import java.util.List;

/**
 * @title: hotel
 * @Description:
 * @Author: liam
 * @Date: 2019/7/23
 * @Version: 1.0
 **/
public interface FoodTypeDaoInterface {

    /**
     * 添加菜系
     * @param foodType
     */
    public void save(FoodType foodType);

    /**
     * 删除${id}菜系
     * @param id
     */
    public void delete(int id);

    /**
     * 更新菜系
     * @param foodType
     */
    public void update(FoodType foodType);

    /**
     * 通过${id}查询菜系
     * @param id
     * @return FoodType
     */
    public FoodType findById(int id);

    /**
     * 查询所有菜系
     * @return
     */
    public List<FoodType> getAll();

    /**
     * 根据输入查询菜系
     * @param typeName
     * @return
     */
    public List<FoodType> getAll(String typeName);
}
