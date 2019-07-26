package cn.gluttonous.hotel.dao;

import cn.gluttonous.hotel.entity.DetailBean;
import cn.gluttonous.hotel.entity.OrderBean;

import java.util.List;

/**
 * @title: hotel
 * @ClassName OrderDaoInterface.java
 * @Description: 后台订单dao
 * @Author: liam
 * @Date: 2019/7/26
 * @Version: 1.0
 **/
public interface OrderDaoInterface {

    /**
     * 修改指定订单的状态
     * @param id
     */
    void update(int state, int id);

    /**
     * 得到所有订单
     * @return List<OrderBean>
     */
    List<OrderBean> getAll();

    /**
     * 查看指定订单的详情
     * @return  List<DetailBean>
     */
    List<DetailBean> getAllDetail(int id);
}
