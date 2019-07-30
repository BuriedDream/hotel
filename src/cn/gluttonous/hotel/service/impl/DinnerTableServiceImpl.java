package cn.gluttonous.hotel.service.impl;

import cn.gluttonous.hotel.dao.DinnerTableDaoInterface;
import cn.gluttonous.hotel.entity.DinnerTable;
import cn.gluttonous.hotel.factory.impl.BeanFactory;
import cn.gluttonous.hotel.service.DinnerTableServiceInterface;

import java.util.Date;
import java.util.List;

/**
 * @title: hotel
 * @ClassName DinnerTableServiceImpl.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/24
 * @Version: 1.0
 **/
public class DinnerTableServiceImpl implements DinnerTableServiceInterface{

    private DinnerTableDaoInterface dinnerTableDao = BeanFactory.getInstance("dinnerTableDao",DinnerTableDaoInterface.class);

    /**
     * 添加餐桌
     *
     * @param dinnerTable
     */
    @Override
    public void add(DinnerTable dinnerTable) {
        dinnerTableDao.add(dinnerTable);
    }

    /**
     * 删除指定餐桌
     *
     * @param id 指定餐桌
     */
    @Override
    public void delete(int id) {

        dinnerTableDao.delete(id);
    }


    /**
     * 查找所有餐桌
     *
     * @return
     */
    @Override
    public List<DinnerTable> query() {
        return dinnerTableDao.query();
    }

    /**
     * 获取指定餐桌
     *
     * @param id 指定餐桌
     * @return 得到指定餐桌
     */
    @Override
    public DinnerTable findById(int id) {
        return dinnerTableDao.findById(id);
    }

    /**
     * 根据keyWord进行模糊查询
     *
     * @param keyWord
     * @return
     */
    @Override
    public List<DinnerTable> query(String keyWord) {
        return dinnerTableDao.query(keyWord);
    }

    /**
     * 查找指定状态的餐桌
     *
     * @param state
     * @return
     */
    @Override
    public List<DinnerTable> query(int state) {
        return dinnerTableDao.queryByStatus(state);
    }

    /**
     * 改变餐桌状态
     *
     * @param id
     * @return
     */
    @Override
    public DinnerTable changeState(int id) {

        DinnerTable dinnerTable = dinnerTableDao.findById(id);
        if(dinnerTable.getTableStatus() == 0){
            dinnerTable.setOrderDate(new Date());
            dinnerTable.setTableStatus(1);
        }
        else if(dinnerTable.getTableStatus() == 1){
            dinnerTable.setTableStatus(0);
            dinnerTable.setOrderDate(null);
        }

        return dinnerTable;
    }

    /**
     * 退订
     *
     * @param id
     */
    @Override
    public void quitTable(int id) {

        DinnerTable dinnerTable = dinnerTableDao.findById(id);
        dinnerTable.setTableStatus(0);
        dinnerTable.setOrderDate(null);
        dinnerTableDao.update(dinnerTable);
    }

    /**
     * 预定餐桌
     *
     * @param id
     */
    @Override
    public void book(int id) {

        DinnerTable dinnerTable = dinnerTableDao.findById(id);
        dinnerTable.setTableStatus(1);
        dinnerTable.setOrderDate(new Date());

        dinnerTableDao.update(dinnerTable);
    }
}
