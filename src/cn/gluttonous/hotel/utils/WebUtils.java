package cn.gluttonous.hotel.utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title: hotel
 * @ClassName WebUtils.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/24
 * @Version: 1.0
 **/
public class WebUtils {

    /**
     * 根据 uri 进行跳转
     *          如果 uri 是 RequestDispatcher，进行转发
     *          如果 uri 是 String 进行重定向
     * @param request
     * @param response
     * @param uri
     */
    public static void goTo(HttpServletRequest request, HttpServletResponse response,Object uri) {

        try{
            if(uri instanceof RequestDispatcher){
                ((RequestDispatcher) uri).forward(request, response);
            }
            else if(uri instanceof String){
                response.sendRedirect(request.getContextPath()+uri);
            }
        }
        catch (ServletException e){
            throw new RuntimeException(e);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
