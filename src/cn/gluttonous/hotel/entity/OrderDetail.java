package cn.gluttonous.hotel.entity;

import java.util.Objects;

/**
 * @title: hotel
 * @ClassName OrderDetail.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/26
 * @Version: 1.0
 **/
public class OrderDetail {

    private int id;
    private int orderId;
    private int foodId;
    private int foodCount;

    public OrderDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        OrderDetail that = (OrderDetail) o;
        return id == that.id &&
                orderId == that.orderId &&
                foodId == that.foodId &&
                foodCount == that.foodCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, foodId, foodCount);
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", foodId=" + foodId +
                ", foodCount=" + foodCount +
                '}';
    }
}
