package cn.gluttonous.hotel.service;

import cn.gluttonous.hotel.entity.DinnerTable;

import java.util.List;

/**
 * @title: hotel
 * @ClassName DinnerTableServiceInterface.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/24
 * @Version: 1.0
 **/
public interface DinnerTableServiceInterface {

    /**
     * 添加餐桌
     * @param dinnerTable
     */
    void add(DinnerTable dinnerTable);

    /**
     * 删除餐桌
     * @param id
     */
    void delete(int id);

    /**
     * 查询所以餐桌
     * @return
     */
    List<DinnerTable> query();

    /**
     * 查找指定餐桌
     * @param id
     * @return
     */
    DinnerTable findById(int id);

    /**
     * 模糊查找餐桌
     * @param keyword
     * @return
     */
    List<DinnerTable> query(String keyword);

    /**
     * 改变餐桌状态
     * @param id
     * @return
     */
    DinnerTable changeState(int id);

    /**
     * 退订
     * @param id
     */
    void quitTable(int id);

    /**
     * 预定餐桌
     * @param id
     */
    void book(int id);
}
