package cn.gluttonous.hotel.servlet;

import cn.gluttonous.hotel.entity.FoodType;
import cn.gluttonous.hotel.factory.impl.BeanFactory;
import cn.gluttonous.hotel.service.FoodTypeServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @title: hotel
 * @ClassName FoodTypeServlet.java
 * @Description: 完成菜系管理的servlet
 *                  1、添加菜系
 *                  2、删除菜系
 *                  3、更新菜系
 *                  4、展示菜系
 *                  5.跳转到更新菜系页面
 *                  6.跳转到添加菜系页面
 *
 * @Author: liam
 * @Date: 2019/7/23
 * @Version: 1.0
 **/
public class FoodTypeServlet extends BaseServlet {

    FoodTypeServiceInterface foodTypeService = BeanFactory.getInstance("foodTypeService",FoodTypeServiceInterface.class);

    /**
     * 添加菜系
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public Object addFoodType(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //返回跳转
        Object uri = null;

        //获取请求数据并封装
        String typeName = request.getParameter("typeName");
        FoodType foodType = new FoodType();
        foodType.setTypeName(typeName);
        foodTypeService.save(foodType);

        uri = request.getRequestDispatcher("/foodType?method=list");
        return uri;
    }

    /**
     * 显示所有菜系
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //返回跳转
        Object uri = null;

        //获取请求数据并封装
        List<FoodType> foodTypes = foodTypeService.getAll();
        request.setAttribute("foodTypeList",foodTypes);

        uri = request.getRequestDispatcher("/system/foodType/cuisineList.jsp");
        return uri;
    }

    /**
     * 跳转到更新页面
     * @param request 得到请求对象
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object updateView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //返回跳转
        Object uri = null;

        //获取请求数据并封装
        String id = request.getParameter("id");
        FoodType foodType = foodTypeService.findById(Integer.parseInt(id));

        //保存数据
        request.setAttribute("foodType",foodType);

        uri = request.getRequestDispatcher("/system/foodType/updateCuisine.jsp");
        return uri;
    }

    public Object delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //返回跳转
        Object uri = null;

        //获取请求数据并封装
        String id = request.getParameter("id");

        //删除
        foodTypeService.delete(Integer.parseInt(id));

        uri = request.getRequestDispatcher("/foodType?method=list");
        return uri;
    }

    public Object update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //返回跳转
        Object uri = null;

        //获取请求数据并封装
        String id = request.getParameter("id");
        String typeName = request.getParameter("typeName");
        FoodType foodType = new FoodType();
        foodType.setTypeName(typeName);
        foodType.setId(Integer.parseInt(id));

        //更新
        foodTypeService.update(foodType);

        uri = request.getRequestDispatcher("/foodType?method=list");
        return uri;
    }

    public Object search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //返回跳转
        Object uri = null;

        //获取请求数据并封装
        String keyword = request.getParameter("keyword");
        List<FoodType> foodTypes = foodTypeService.getAll(keyword);
        request.setAttribute("foodTypeList",foodTypes);

        uri = request.getRequestDispatcher("/system/foodType/cuisineList.jsp");
        return uri;
    }

}
