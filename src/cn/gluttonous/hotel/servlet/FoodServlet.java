package cn.gluttonous.hotel.servlet;

import cn.gluttonous.hotel.entity.DinnerTable;
import cn.gluttonous.hotel.entity.Food;
import cn.gluttonous.hotel.entity.FoodListEntity;
import cn.gluttonous.hotel.entity.FoodType;
import cn.gluttonous.hotel.factory.impl.BeanFactory;
import cn.gluttonous.hotel.service.DinnerTableServiceInterface;
import cn.gluttonous.hotel.service.FoodServiceInterface;
import cn.gluttonous.hotel.service.FoodTypeServiceInterface;
import cn.gluttonous.hotel.utils.Condition;
import cn.gluttonous.hotel.utils.PageBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
    DinnerTableServiceInterface dinnerTableService = BeanFactory.getInstance("dinnerTableService",DinnerTableServiceInterface.class);

    /**
     * 分页显示菜单
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object uri = null;

        //得到当前页
        String currPage = request.getParameter("currentPage");
        if(currPage==null || "".equals(currPage.trim())){
            currPage = "1";
        }
        int currentPage = Integer.parseInt(currPage);
        //创建PageBean,并初始化
        PageBean<FoodListEntity> pageBean = new PageBean<FoodListEntity>();
        pageBean.setCurrentPage(currentPage);
        foodService.getListAll(pageBean);

        request.setAttribute("pageBean",pageBean);

        uri = request.getRequestDispatcher("/system/food/foodList.jsp");
        return uri;
    }


    /**
     * 添加菜品
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws FileUploadException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws Exception
     */
    public Object add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileUploadException,
            IllegalAccessException,InvocationTargetException,Exception  {

        Object uri = null;

        FileItemFactory fileItem = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fileItem);

        //单个文件大小不超过10M
        upload.setFileSizeMax(10*1024*1024);
        //总大小超过 100M
        upload.setSizeMax(100*1024*1024);
        //编码设置
        upload.setHeaderEncoding("utf-8");

        if (upload.isMultipartContent(request)) {

            Food food = new Food();
            List<FileItem> list = upload.parseRequest(request);

            for (FileItem item : list) {
                // 普通本文内容
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    // 获取值
                    String value = item.getString("utf-8");
                    BeanUtils.setProperty(food, name, value);
                } // 上传内容
                else {
                    String fieldName = item.getFieldName();
                    String path = getServletContext()
                            .getRealPath("/image");
                    File f = new File(path);
                    if (!f.exists()) {
                        f.mkdir();
                    }
                    // 全部绝对路径
                    String name = item.getName();

                    BeanUtils.setProperty(food, fieldName, name);

                    // a2. 拼接文件名
                    File file = new File(path, name);
                    // d. 上传
                    if (!file.isDirectory()) {
                        item.write(file);
                    }
                    item.delete(); // 删除组件运行时产生的临时文件
                }
            }
            foodService.add(food);
            uri = request.getRequestDispatcher("/food?method=list");
        }else {
            uri = "error/error.jsp";
        }

        return uri;
    }

    public Object update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException,Exception {

        Object uri = null;

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 单个文件大小限制
        upload.setFileSizeMax(10 * 1024 * 1024);
        // 总文件大小限制
        upload.setSizeMax(100 * 1024 * 1024);
        // 对中文文件编码处理
        upload.setHeaderEncoding("UTF-8");

        if (upload.isMultipartContent(request)) {

            Food food = new Food();
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                // 普通本文内容
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    // 获取值
                    String value = item.getString("utf-8");
                    BeanUtils.setProperty(food, name, value);
                } else {// 上传内容
                    String fieldName = item.getFieldName();
                    String path = getServletContext()
                            .getRealPath("/image");
                    File f = new File(path);
                    if (!f.exists()) {
                        f.mkdir();
                    }
                    String name = item.getName();
                    if(name!=null && !"".equals(name.trim())){
                        BeanUtils.setProperty(food, fieldName,
                                (name));

                        // a2. 拼接文件名
                        item.write(new File(path,name));
                        item.delete(); // 删除组件运行时产生的临时文件
                    }else{
                        int id = food.getId();
                        String image =foodService.getById(id).getImage();
                        BeanUtils.setProperty(food, "image",image);

                    }
                }
            }
            foodService.update(food);

        }

        uri = request.getRequestDispatcher("/food?method=list");
        return uri;

    }

    /**
     * 删除指定菜品
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public Object delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Object uri = null;

        String id = request.getParameter("id");
        foodService.delete(Integer.parseInt(id));

        uri = request.getRequestDispatcher("/food?method=list");
        return uri;
    }

    /**
     * 模糊查找
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Object uri = null;

        //得到当前页
        String currPage = request.getParameter("currentPage");
        if(currPage==null || "".equals(currPage.trim())){
            currPage = "1";
        }
        int currentPage = Integer.parseInt(currPage);
        //创建PageBean,并初始化
        PageBean<FoodListEntity> pageBean = new PageBean<FoodListEntity>();
        pageBean.setCurrentPage(currentPage);


        String keyword = request.getParameter("keyword");
        if (keyword != null) {
            foodService.query(pageBean,keyword);
        }

        request.setAttribute("pageBean",pageBean);

        uri = request.getRequestDispatcher("/system/food/foodList.jsp");
        return uri;
    }

    /**
     * 得到指定菜品进行更新
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object show(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Object uri = null;

        String id = request.getParameter("id");
        Food food = foodService.getById(Integer.parseInt(id));

        request.setAttribute("food", food);
        // 得到食物里面的食物类型ID
        int foodTypeId = food.getFoodTypeId();

        // 通过
        List<FoodType> foodTypes = foodTypeService.getAll();
        FoodType type = foodTypeService.findById(foodTypeId);

        request.setAttribute("foodTypes", foodTypes);
        request.setAttribute("type",type);

        uri = request.getRequestDispatcher("/system/food/updateFood.jsp");
        return uri;

    }

    /**
     * 得到菜系进入添加菜品页面
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object findFoodType(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Object uri = null;
        List<FoodType> foodtypes = foodTypeService.getAll();
        request.setAttribute("foodTypes", foodtypes);

        uri = request.getRequestDispatcher("/system/food/saveFood.jsp");
        return uri;
    }

    /**
     * 后台得到所有菜品
     * @param request
     * @param response
     * @return FoodList
     * @throws ServletException
     * @throws IOException
     */
    public Object query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Object uri = null;

        //得到请求的菜品
        List<FoodListEntity> foods = foodService.query();
        request.setAttribute("foods",foods);

        uri = request.getRequestDispatcher("/system/food/foodList.jsp");
        return uri;
    }

    /**
     * 1. 进入主页，显示菜品以及菜系
     */
    public Object getMenu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Object uri = null;

        HttpSession session = request.getSession();
        //1.1 获取餐桌ID,根据ID查询，再把查询到的结果保存到session （生成订单用）
        // 只需要执行一次即可: 先从session获取看有没有餐桌对象； 如果没有，就执行根据主键查询；
        // 如果sesison中已经有餐桌对象，就不执行主键查询
        Object obj = session.getAttribute("dinnerTable");
        // 判断
        if (obj == null){
            // 只在第一次执行的时候，查询餐桌对象
            String tableId = request.getParameter("tableId");
            DinnerTable dt = dinnerTableService.findById(Integer.parseInt(tableId));
            // 保存到session
            session.setAttribute("dinnerTable", dt);

        }

        //1.2 查询所有的“菜品信息”, 保存
        PageBean<Food> pb = new PageBean<Food>();
        // 分页参数： 获取当前页参数
        String curPage = request.getParameter("currentPage");
        // 判断
        if (curPage == null || "".equals(curPage.trim())) {
            // 第一次访问，设置当前页为1
            pb.setCurrentPage(1);
        } else {
            // 【设置当前页参数】
            pb.setCurrentPage(Integer.parseInt(curPage));
        }

        // 条件对象
        Condition condition = new Condition();
        // 分页参数： 菜系id
        String foodTypeId = request.getParameter("foodTypeId");
        // 如果类别为null,不作为条件，那就查询全部
        if (foodTypeId != null) {
            // 设置条件
            condition.setFoodTypeId(Integer.parseInt(foodTypeId));
        }
        // 分页参数： 菜名称
        String foodName = request.getParameter("foodName");
        // 设置菜品参数
        condition.setFoodName(foodName);

        // 【设置条件对象到pb中】
        pb.setCondition(condition);

        // ---->分页查询
        foodService.getAll(pb);
        // 保存查询后的pb对象
        request.setAttribute("pb", pb);

        //1.3 查询所有的“菜系信息”， 保存
        List<FoodType> listFoodType = foodTypeService.getAll();
        request.setAttribute("listFoodType", listFoodType);

        //1.4 跳转(转发)
        uri = request.getRequestDispatcher("/application/menu/caidan.jsp");

        return uri;
    }


}



