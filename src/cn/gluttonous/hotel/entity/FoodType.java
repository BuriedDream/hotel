package cn.gluttonous.hotel.entity;

import java.util.Objects;

/**
 * @title: hotel
 * @ClassName FoodType.java
 * @Description: 菜系实体
 * @Author: liam
 * @Date: 2019/7/23
 * @Version: 1.0
 **/
public class FoodType {

    /**
     * 菜系编号
     */
    private int id;
    /**
     * 菜系名
     */
    private String typeName;

    public FoodType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        FoodType foodType = (FoodType) o;
        return id == foodType.id &&
                Objects.equals(typeName, foodType.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName);
    }
}
