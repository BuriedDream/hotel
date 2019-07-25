package cn.gluttonous.hotel.servlet;

import cn.gluttonous.hotel.entity.Food;
import cn.gluttonous.hotel.entity.FoodType;
import cn.gluttonous.hotel.factory.impl.BeanFactory;
import cn.gluttonous.hotel.service.FoodServiceInterface;
import cn.gluttonous.hotel.service.FoodTypeServiceInterface;
import cn.gluttonous.hotel.utils.PageBean;
import org.omg.CORBA.OBJ_ADAPTER;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @title: hotel
 * @ClassName FoodServlet.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/25
 * @Version: 1.0
 **/
public class FoodServlet extends BaseServlet {

    FoodServiceInterface foodService = BeanFactory.getInstance("foodService",FoodServiceInterface.class);
    FoodTypeServiceInterface foodTypeService = BeanFactory.getInstance("foodTypeService",FoodTypeServiceInterface.class);


    /**
     * 分页显示菜单
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    private Object list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object uri = null;

        //得到当前页
        String currPage = request.getParameter("currentPage");
        if(currPage==null || "".equals(currPage.trim())){
            currPage = "1";
        }
        int currentPage = Integer.parseInt(currPage);
        //创建PageBean,并初始化
        PageBean<Food> pageBean = new PageBean<Food>();
        pageBean.setCurrentPage(currentPage);
        foodService.getAll(pageBean);

        List<Food> foods = pageBean.getPageData();
        List<FoodType> foodTypes = new ArrayList<>();
        if(foods!=null) {
            for (Food food : foods) {
                foodTypes.add(foodTypeService.findById(food.getFoodTypeId()));
            }
        }

        request.setAttribute("foods",foods);
        request.setAttribute("foodTypes",foodTypes);
        request.setAttribute("pageBean",pageBean);

        uri = request.getRequestDispatcher("/system/food/foodList.jsp");
        return uri;
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


    }
    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    private void show(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    private void findById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<FoodType> types = foodTypeService.getAll();
        request.setAttribute("types", types);
    }

    /**
     * 后台得到所有菜品
     * @param request
     * @param response
     * @return FoodList
     * @throws ServletException
     * @throws IOException
     */
    private Object query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Object uri = null;

        //得到请求的菜品
        List<Food> foods = foodService.query();
        request.setAttribute("foods",foods);

        //得到菜品对应的菜系名
        List<FoodType> foodTypes = new ArrayList<>();
        for (Food food : foods) {
            foodTypes.add(foodTypeService.findById(food.getFoodTypeId()));
        }
        request.setAttribute("foodTypes",foodTypes);

        uri = request.getRequestDispatcher("/system/food/foodList.jsp");
        return uri;
    }
    private void getMenu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}



