package cn.gluttonous.hotel.servlet;

import cn.gluttonous.hotel.entity.DetailBean;
import cn.gluttonous.hotel.entity.OrderBean;
import cn.gluttonous.hotel.factory.impl.BeanFactory;
import cn.gluttonous.hotel.service.OrderServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @title: hotel
 * @ClassName OrderServlet.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/26
 * @Version: 1.0
 **/
public class OrderServlet extends BaseServlet {

    OrderServiceInterface orderService = BeanFactory.getInstance("orderService",OrderServiceInterface.class);

    public Object list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Object uri = null;

        List<OrderBean> orderList = orderService.getAll();
        request.setAttribute("orderList",orderList);

        uri = request.getRequestDispatcher("system/order/orderList.jsp");

        return uri;

    }

    public Object getDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Object uri = null;

        String id = request.getParameter("id");

        List<DetailBean> details = orderService.getAllDetail(Integer.parseInt(id));
        request.setAttribute("details",details);

        uri = request.getRequestDispatcher("system/order/orderDetail.jsp");
        return uri;
    }

    public Object pay(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Object uri = null;

        String id = request.getParameter("id");
        orderService.update(Integer.parseInt(id));

        uri = request.getRequestDispatcher("/order?method=list");

        return uri;
    }
}
