package cn.gluttonous.hotel.dao.impl;

import cn.gluttonous.hotel.dao.OrderDaoInterface;
import cn.gluttonous.hotel.entity.DetailBean;
import cn.gluttonous.hotel.entity.OrderBean;
import cn.gluttonous.hotel.utils.JdbcUtils;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @title: hotel
 * @ClassName OrderDao.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/26
 * @Version: 1.0
 **/
public class OrderDao implements OrderDaoInterface {
    /**
     * 修改指定订单的状态
     *          0  未支付
     *          1   已支付
     * @param id
     */
    @Override
    public void update(int state, int id) {
        String sql_update = "UPDATE orders SET orderStatus=? WHERE id=?";

        try {
            JdbcUtils.getQueryRunner().update(sql_update,state,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 得到所有订单
     *
     * @return List<OrderBean>
     */
    @Override
    public List<OrderBean> getAll() {
        String sql_select = "SELECT id,tableName, orderDate, totalPrice, orderStatus FROM orderList";

        try {
            return JdbcUtils.getQueryRunner().query(sql_select,new BeanListHandler<OrderBean>(OrderBean.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查看指定订单的详情
     *
     * @param id
     * @return List<DetailBean>
     */
    @Override
    public List<DetailBean> getAllDetail(int id) {

        String sql_detail = "SELECT foodName, price, memberPrice, foodCount FROM detail WHERE orderId=?";

        try {
            return JdbcUtils.getQueryRunner().query(sql_detail,new BeanListHandler<DetailBean>(DetailBean.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
