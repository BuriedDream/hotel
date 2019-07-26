package cn.gluttonous.hotel.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @title: hotel
 * @ClassName OrderBean.java
 * @Description: 订单页面Bean 对应视图 OrderList
 * @Author: liam
 * @Date: 2019/7/26
 * @Version: 1.0
 **/
public class OrderBean {

    private int id;
    private String tableName;
    private Date orderDate;
    private double totalPrice;
    private int orderStatus;

    public OrderBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderBean orderBean = (OrderBean) o;
        return id == orderBean.id &&
                Double.compare(orderBean.totalPrice, totalPrice) == 0 &&
                orderStatus == orderBean.orderStatus &&
                Objects.equals(tableName, orderBean.tableName) &&
                Objects.equals(orderDate, orderBean.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tableName, orderDate, totalPrice, orderStatus);
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "id=" + id +
                ", tableName='" + tableName + '\'' +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
