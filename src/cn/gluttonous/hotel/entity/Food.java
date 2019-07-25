package cn.gluttonous.hotel.entity;

import java.util.Objects;

/**
 * @title: hotel
 * @ClassName Food.java
 * @Description: 菜品实体类
 * @Author: liam
 * @Date: 2019/7/24
 * @Version: 1.0
 **/
public class Food {

    /**
     * 菜品主键
     */
    private int id;
    /**
     * 菜品名
     */
    private String foodName;
    /**
     * 菜系主键
     */
    private int foodTypeId;
    /**
     * 菜品普通价格
     */
    private double price;
    /**
     * 菜品会员价格
     */
    private double memberPrice;
    /**
     * 菜品评价
     */
    private String remark;
    /**
     * 菜品图片路径
     */
    private String image;

    public Food() {
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

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Food food = (Food) o;
        return id == food.id &&
                foodTypeId == food.foodTypeId &&
                Double.compare(food.price, price) == 0 &&
                Double.compare(food.memberPrice, memberPrice) == 0 &&
                Objects.equals(foodName, food.foodName) &&
                Objects.equals(remark, food.remark) &&
                Objects.equals(image, food.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, foodName, foodTypeId, price, memberPrice, remark, image);
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", foodTypeId=" + foodTypeId +
                ", price=" + price +
                ", memberPrice=" + memberPrice +
                ", remark='" + remark + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
