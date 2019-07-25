package cn.gluttonous.hotel.service.impl;

import cn.gluttonous.hotel.dao.FoodDaoInterface;
import cn.gluttonous.hotel.entity.Food;
import cn.gluttonous.hotel.entity.FoodListEntity;
import cn.gluttonous.hotel.factory.impl.BeanFactory;
import cn.gluttonous.hotel.service.FoodServiceInterface;
import cn.gluttonous.hotel.utils.PageBean;

import java.util.List;

/**
 * @title: hotel
 * @ClassName FoodServiceImpl.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/25
 * @Version: 1.0
 **/
public class FoodServiceImpl implements FoodServiceInterface {

    private FoodDaoInterface foodDao = BeanFactory.getInstance("foodDao",FoodDaoInterface.class);
    /**
     * 添加菜品
     *
     * @param food
     */
    @Override
    public void add(Food food) {

        foodDao.add(food);
    }

    /**
     * 删除指定菜品
     *
     * @param id
     */
    @Override
    public void delete(int id) {

        foodDao.delete(id);
    }

    /**
     * 更新指定菜品
     *
     * @param food
     */
    @Override
    public void update(Food food) {

        foodDao.update(food);
    }

    /**
     * 得到分页
     *
     * @param pageBean
     */
    @Override
    public void getAll(PageBean<Food> pageBean) {

        foodDao.getAll(pageBean);
    }

    /**
     * 后台的到分页
     *
     * @param pageBean
     */
    @Override
    public void getListAll(PageBean<FoodListEntity> pageBean) {
        foodDao.getListAll(pageBean);
    }

    /**
     * 获取指定菜品
     *
     * @param id
     * @return
     */
    @Override
    public Food getById(int id) {

        return foodDao.findById(id);
    }

    /**
     * 得到指定菜系的菜品
     *
     * @param type
     * @return
     */
    @Override
    public List<Food> getByType(int type) {
        return foodDao.findByType(type);
    }

    /**
     * 根据名字进行模糊查询
     *
     * @param keyName
     * @return
     */
    @Override
    public List<FoodListEntity> query(String keyName) {
        return foodDao.query(keyName);
    }

    /**
     * 查找所有菜品
     *
     * @return
     */
    @Override
    public List<FoodListEntity> query() {
        return foodDao.query();
    }
}
