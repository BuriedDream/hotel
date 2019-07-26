package cn.gluttonous.hotel.service;

import cn.gluttonous.hotel.entity.DetailBean;
import cn.gluttonous.hotel.entity.OrderBean;

import java.util.List;

/**
 * @title: hotel
 * @ClassName OrderServiceInterface.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/26
 * @Version: 1.0
 **/
public interface OrderServiceInterface {

    /**
     * 修改指定订单为结账状态
     * @param id
     */
    void update(int id);

    /**
     * 得到所有订单
     * @return List<OrderBean>
     */
    List<OrderBean> getAll();

    /**
     * 查看指定订单的详情
     * @param id
     *
     * @return  List<DetailBean>
     */
    List<DetailBean> getAllDetail(int id);
}
