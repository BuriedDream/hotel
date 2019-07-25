package cn.gluttonous.hotel.utils;


/**
 * @title: hotel
 * @ClassName Condition.java
 * @Description: 菜品约束
 * @Author: liam
 * @Date: 2019/7/24
 * @Version: 1.0
 **/
public class Condition {

	private String foodName;
	private int foodTypeId;
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getFoodTypeId() {
		return foodTypeId;
	}
	public void setFoodTypeId(int foodType_id) {
		this.foodTypeId = foodTypeId;
	}
	@Override
	public String toString() {
		return "Condition [foodName=" + foodName + ", foodType_id="
				+ foodTypeId + "]";
	}
	
}
