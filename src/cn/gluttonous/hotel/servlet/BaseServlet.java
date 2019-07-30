package cn.gluttonous.hotel.servlet;

import cn.gluttonous.hotel.factory.impl.BeanFactory;
import cn.gluttonous.hotel.service.FoodTypeServiceInterface;
import cn.gluttonous.hotel.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @title: hotel
 * @ClassName BaseServlet.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/24
 * @Version: 1.0
 **/
public abstract class BaseServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        //方法返回值
        Object uri = null;

        //获取方法名
        String methodName = request.getParameter("method");
        if(methodName == null){
            methodName = "listNoBook";
        }

        //当前执行类的Class文件
        Class clazz = this.getClass();

        try {
            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
            uri = method.invoke(this,request,response);
        }  catch (NoSuchMethodException e) {
            uri = "/error/error.jsp";
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
        }catch (Exception e){
            e.printStackTrace();
            uri = "/error/error.jsp";
        }
        //跳转
        WebUtils.goTo(request,response,uri);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request,response);
    }
}
