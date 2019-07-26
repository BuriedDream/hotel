package cn.gluttonous.hotel.servlet;

import cn.gluttonous.hotel.entity.DinnerTable;
import cn.gluttonous.hotel.factory.impl.BeanFactory;
import cn.gluttonous.hotel.service.DinnerTableServiceInterface;
import cn.gluttonous.hotel.service.FoodTypeServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @title: hotel
 * @ClassName DinnerTableServlet.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/24
 * @Version: 1.0
 **/
public class DinnerTableServlet extends BaseServlet {

    DinnerTableServiceInterface dinnerTableService =
            BeanFactory.getInstance("dinnerTableService",DinnerTableServiceInterface.class);

    /**
     * 删除餐桌
     * @param request
     * @param response
     * @return uri 路径
     * @throws ServletException
     * @throws IOException
     */
    public Object delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        Object uri = null;

        String id = request.getParameter("id");
        dinnerTableService.delete(Integer.parseInt(id));

        uri = request.getRequestDispatcher("/dinnerTable?method=list");
        return uri;
    }

    /**
     * 预定餐桌
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object book(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object uri = null;

        String id = request.getParameter("id");
        dinnerTableService.book(Integer.parseInt(id));
        uri = request.getRequestDispatcher("/dinnerTable?method=list");
        return uri;
    }

    /**
     * 退订
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object quitTable(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object uri = null;

        String id = request.getParameter("id");
        dinnerTableService.quitTable(Integer.parseInt(id));

        uri = request.getRequestDispatcher("/dinnerTable?method=list");
        return uri;
    }

    /**
     * 查找所有餐桌
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object uri = null;

        List<DinnerTable> list = dinnerTableService.query();
        request.setAttribute("list",list);

        // 将餐桌列表存到context里传到前台显示
        request.getServletContext().setAttribute("table", list);

        uri = request.getRequestDispatcher("/system/dinnerTable/boardList.jsp");

        return uri;
    }

    /**
     * 添加餐桌
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public Object add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        Object uri;

        String tableName = request.getParameter("tableName");

        if(tableName!=null){
            DinnerTable dinnerTable = new DinnerTable();
            dinnerTable.setTableName(tableName);
            dinnerTableService.add(dinnerTable);
        }

        uri = request.getRequestDispatcher("/dinnerTable?method=list");
        return uri;
    }

    public Object search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object uri = null;

        String keyword = request.getParameter("keyword");

        List<DinnerTable> list = dinnerTableService.query(keyword);
        request.setAttribute("list",list);

        uri = request.getRequestDispatcher("/system/dinnerTable/boardList.jsp");

        return uri;
    }
}
