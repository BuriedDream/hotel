package cn.gluttonous.hotel.Test;

import cn.gluttonous.hotel.dao.impl.FoodDaoImpl;
import cn.gluttonous.hotel.entity.Food;
import cn.gluttonous.hotel.servlet.BaseServlet;
import cn.gluttonous.hotel.utils.JdbcUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Date;

/**
 * @title: hotel
 * @ClassName Test.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/24
 * @Version: 1.0
 **/
public class Test extends BaseServlet implements Cloneable{

    @org.junit.Test
    public void test(HttpServletRequest request, HttpServletResponse response) {
//        FoodDaoImpl dao = new FoodDaoImpl();
//        for (int i = 0; i < 20; i++) {
//            Food food = new Food();
//            food.setFoodName(i+"ha");
//            food.setFoodTypeId(7);
//            food.setPrice(i);
//            food.setMemberPrice(i);
//            food.setRemark("i");
//            food.setImage("img");
//            dao.add(food);
//        }

        String path = getServletContext().getContextPath()+"/image";
        System.out.println(getServletContext().getContextPath()+"/image");
    }
}