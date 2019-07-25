package cn.gluttonous.hotel.entity;

import java.util.Objects;

/**
 * @title: hotel
 * @ClassName FoodListEntity.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/25
 * @Version: 1.0
 **/
public class FoodListEntity {
    /**
     * 菜品主键
     */
    private int id;
    /**
     * 菜品名
     */
    private String foodName;
    /**
     * 所属菜系名
     */
    private String typeName;
    /**
     * 菜品普通价格
     */
    private double price;
    /**
     * 菜品会员价格
     */
    private double memberPrice;

    public FoodListEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        FoodListEntity that = (FoodListEntity) o;
        return id == that.id &&
                typeName == that.typeName &&
                Double.compare(that.price, price) == 0 &&
                Double.compare(that.memberPrice, memberPrice) == 0 &&
                Objects.equals(foodName, that.foodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, foodName, typeName, price, memberPrice);
    }

    @Override
    public String toString() {
        return "FoodListEntity{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", foodTypeName=" + typeName +
                ", price=" + price +
                ", memberPrice=" + memberPrice +
                '}';
    }
}
