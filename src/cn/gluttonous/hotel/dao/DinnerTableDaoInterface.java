package cn.gluttonous.hotel.dao;

import cn.gluttonous.hotel.entity.DinnerTable;

import java.util.List;

/**
 * @title: hotel
 * @ClassName DinnerTableDaoInterface.java
 * @Description: 对餐桌的操作Dao
 * @Author: liam
 * @Date: 2019/7/24
 * @Version: 1.0
 **/
public interface DinnerTableDaoInterface {

    /**
     * 添加餐桌
     * @param dinnerTable
     */
    public void add(DinnerTable dinnerTable);

    /**
     * 删除指定餐桌
     * @param id 指定餐桌
     */
    public void delete(int id);

    /**
     * 更新餐桌
     * @param dinnerTable
     */
    public void update(DinnerTable dinnerTable);

    /**
     * 查找所有餐桌
     * @return
     */
    public List<DinnerTable> query();

    /**
     * 获取指定餐桌
     * @param id 指定餐桌
     * @return 得到指定餐桌
     */
    public DinnerTable findById(int id);

    /**
     * 根据keyWord进行模糊查询
     * @param keyWord
     * @return
     */
    public List<DinnerTable> query(String keyWord);
}
