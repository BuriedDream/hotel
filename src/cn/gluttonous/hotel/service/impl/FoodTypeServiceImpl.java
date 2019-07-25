package cn.gluttonous.hotel.service.impl;

import cn.gluttonous.hotel.dao.FoodTypeDaoInterface;
import cn.gluttonous.hotel.entity.FoodType;
import cn.gluttonous.hotel.factory.impl.BeanFactory;
import cn.gluttonous.hotel.service.FoodTypeServiceInterface;

import java.util.List;

/**
 * @title: hotel
 * @ClassName FoodTypeServiceImpl.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/23
 * @Version: 1.0
 **/
public class FoodTypeServiceImpl implements FoodTypeServiceInterface {

    private FoodTypeDaoInterface foodTypeDao = BeanFactory.getInstance("foodTypeDao",FoodTypeDaoInterface.class);

    /**
     * 添加菜系
     *
     * @param foodType
     */
    @Override
    public void save(FoodType foodType) {

        foodTypeDao.save(foodType);
    }

    /**
     * 更新菜系名
     *
     * @param foodType
     */
    @Override
    public void update(FoodType foodType) {

        foodTypeDao.update(foodType);
    }

    /**
     * 删除菜系
     *
     * @param id
     */
    @Override
    public void delete(int id) {

        foodTypeDao.delete(id);
    }

    /**
     * 根据ID查询菜系
     *
     * @param id
     * @return
     */
    @Override
    public FoodType findById(int id) {
        return foodTypeDao.findById(id);
    }

    /**
     * 查找所有菜系名
     *
     * @return
     */
    @Override
    public List<FoodType> getAll() {
        return foodTypeDao.getAll();
    }

    /**
     * 根据菜系名称查询
     *
     * @param typeName
     * @return List
     */
    @Override
    public List<FoodType> getAll(String typeName) {
        return foodTypeDao.getAll(typeName);
    }
}
