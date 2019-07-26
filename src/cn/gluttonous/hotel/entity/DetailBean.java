package cn.gluttonous.hotel.entity;

import java.util.Objects;

/**
 * @title: hotel
 * @ClassName DetailBean.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/26
 * @Version: 1.0
 **/
public class DetailBean {

    private String foodName;
    private double price;
    private double memberPrice;
    private int foodCount;

    public DetailBean() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(double memberPrice) {
        this.memberPrice = memberPrice;
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
        DetailBean that = (DetailBean) o;
        return Double.compare(that.price, price) == 0 &&
                Double.compare(that.memberPrice, memberPrice) == 0 &&
                foodCount == that.foodCount &&
                Objects.equals(foodName, that.foodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodName, price, memberPrice, foodCount);
    }

    @Override
    public String toString() {
        return "DetailBean{" +
                "foodName='" + foodName + '\'' +
                ", price=" + price +
                ", memberPrice=" + memberPrice +
                ", foodCount=" + foodCount +
                '}';
    }
}
