package cn.gluttonous.hotel.service.impl;

import cn.gluttonous.hotel.dao.OrderDaoInterface;
import cn.gluttonous.hotel.entity.DetailBean;
import cn.gluttonous.hotel.entity.OrderBean;
import cn.gluttonous.hotel.factory.impl.BeanFactory;
import cn.gluttonous.hotel.service.OrderServiceInterface;
import cn.gluttonous.hotel.servlet.BaseServlet;

import java.util.List;

/**
 * @title: hotel
 * @ClassName OrderServiceImpl.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/26
 * @Version: 1.0
 **/
public class OrderServiceImpl implements OrderServiceInterface {

    OrderDaoInterface orderDao = BeanFactory.getInstance("orderDao",OrderDaoInterface.class);

    /**
     * 修改指定订单为结账状态
     *
     * @param id
     */
    @Override
    public void update(int id) {
        orderDao.update(1,id);
    }

    /**
     * 得到所有订单
     *
     * @return List<OrderBean>
     */
    @Override
    public List<OrderBean> getAll() {
        return orderDao.getAll();
    }

    /**
     * 查看指定订单的详情
     *
     * @param id
     * @return List<DetailBean>
     */
    @Override
    public List<DetailBean> getAllDetail(int id) {
        return orderDao.getAllDetail(id);
    }
}
