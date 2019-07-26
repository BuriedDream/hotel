package cn.gluttonous.hotel.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @title: hotel
 * @ClassName Order.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/26
 * @Version: 1.0
 **/
public class Order {

    private int id;
    private int tableId;
    private Date orderDate;
    private double totalPrice;
    private int orderStatus;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return id == order.id &&
                tableId == order.tableId &&
                Double.compare(order.totalPrice, totalPrice) == 0 &&
                orderStatus == order.orderStatus &&
                Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tableId, orderDate, totalPrice, orderStatus);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", tableId=" + tableId +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
