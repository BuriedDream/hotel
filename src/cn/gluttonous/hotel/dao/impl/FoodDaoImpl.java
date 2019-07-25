package cn.gluttonous.hotel.dao.impl;

import cn.gluttonous.hotel.dao.FoodDaoInterface;
import cn.gluttonous.hotel.entity.Food;
import cn.gluttonous.hotel.entity.FoodListEntity;
import cn.gluttonous.hotel.utils.Condition;
import cn.gluttonous.hotel.utils.JdbcUtils;
import cn.gluttonous.hotel.utils.PageBean;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @title: hotel
 * @ClassName FoodDaoImpl.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/24
 * @Version: 1.0
 **/
public class FoodDaoImpl implements FoodDaoInterface {
    /**
     * 添加菜品
     *
     * @param food
     */
    @Override
    public void add(Food food) {
        String sql_add = "INSERT INTO food(foodName,foodTypeId,price,memberPrice,remark,image) VALUES(?,?,?,?,?,?)";

        try {
            JdbcUtils.getQueryRunner().update(sql_add, food.getFoodName(),food.getFoodTypeId(),
                    food.getPrice(),food.getMemberPrice(),food.getRemark(),food.getImage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除菜品
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        String sql_delete = "DELETE FROM food WHERE id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql_delete,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 更新菜品
     *
     * @param food
     */
    @Override
    public void update(Food food) {

        String sql_update = "UPDATE food SET foodName=?,foodTypeId=?,price=?,memberPrice=?,remark=?,image=? WHERE id=?";

        try {
            JdbcUtils.getQueryRunner().update(sql_update,food.getFoodName(),food.getFoodTypeId(),
                    food.getPrice(),food.getMemberPrice(),food.getRemark(),food.getImage(),food.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 所有菜品
     *
     * @return
     */
    @Override
    public List<FoodListEntity> query() {
        String sel_select = "SELECT id, foodName, typeName, price, memberPrice FROM foodList";
        try {
            return JdbcUtils.getQueryRunner().query(sel_select,new BeanListHandler<FoodListEntity>(FoodListEntity.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查找指定菜品
     *
     * @param id
     * @return
     */
    @Override
    public Food findById(int id) {
        String sel_select = "SELECT * FROM food WHERE id=?";
        try {
            return JdbcUtils.getQueryRunner().query(sel_select,new BeanHandler<Food>(Food.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 模糊查询指定菜品
     *
     * @param keyword
     * @return
     */
    @Override
    public List<FoodListEntity> query(String keyword) {
        String sel_select = "SELECT * FROM food WHERE foodName=?";
        try {
            return JdbcUtils.getQueryRunner().query(sel_select,new BeanListHandler<FoodListEntity>(FoodListEntity.class),"%"+keyword+"%");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    public List<Food> query(String keyword) {
//        String sel_select = "SELECT * FROM food WHERE foodName=?";
//        try {
//            return JdbcUtils.getQueryRunner().query(sel_select,new BeanListHandler<Food>(Food.class),"%"+keyword+"%");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * 查询指定菜系的所有菜品
     *
     * @param type
     * @return
     */
    @Override
    public List<Food> findByType(int type) {
        String sel_select = "SELECT * FROM food WHERE foodType=?";
        try {
            return JdbcUtils.getQueryRunner().query(sel_select,new BeanListHandler<Food>(Food.class),type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 分页查询数据
     *
     * @param foodPageBean
     */
    @Override
    public void getAll(PageBean<Food> foodPageBean) {

        /**
         * 总页数
         */
        int totalCount = this.getTotalCount(foodPageBean);
        foodPageBean.setTotalCount(totalCount);

        /**
         * 设置PageBean基础数据
         *      如果当前页 大于 1  则当前页为 1 <br/>
         *      如果当前页大于最大页数  则当前页为 最大页数
         *
         */
        if(foodPageBean.getCurrentPage()<1){
            foodPageBean.setCurrentPage(1);
        }
        else if(foodPageBean.getCurrentPage() > foodPageBean.getTotalPage()){
            foodPageBean.setCurrentPage(foodPageBean.getTotalPage());
        }

        /**
         * 得到分页的页数条件
         * @ index 起始页
         * @ count 每页记录数
         */
        int index = (foodPageBean.getCurrentPage() - 1) * foodPageBean.getPageCount();
        int count = foodPageBean.getPageCount();

        /**
         * sql 语句，为了线程安全使用StringBuffer
         */
        StringBuffer sql_count = new StringBuffer();
        /**
         * 参数 因为参数个数不确定所以使用 集合
         */
        List<Object> params = new ArrayList<>();

        sql_count.append("SELECT");
        sql_count.append("     f.foodName,");
        sql_count.append("     f.foodTypeId,");
        sql_count.append("     f.Price,");
        sql_count.append("     f.memberPrice,");
        sql_count.append("     f.remark,");
        sql_count.append("     f.image,");
        sql_count.append("     ft.TypeName,");
        sql_count.append("FROM ");
        sql_count.append("     food f,");
        sql_count.append("     foodType ft ");
        sql_count.append("WHERE 1=1");
        sql_count.append("      AND f.foodTypeId=ft.id ");

        /**
         * 封装了分页查询的条件
         */
        Condition condition = foodPageBean.getCondition();
        if(condition !=null){
            String foodName = condition.getFoodName();
            if(foodName!=null && !foodName.isEmpty()){
                sql_count.append(" AND f.foodName Like ?");
                params.add("%"+foodName+"%");
            }
            int typeId = condition.getFoodTypeId();
            if(typeId>0){
                sql_count.append(" AND f.foodTypeId=? ");
                params.add(typeId);
            }
        }
        /**
         * 分页
         */
        sql_count.append(" LIMIT ?,?");
        params.add(index);
        params.add(count);

        try {
            List<Food> pageDate = JdbcUtils.getQueryRunner().query(sql_count.toString(),
                    new BeanListHandler<Food>(Food.class),params.toArray());
            foodPageBean.setPageData(pageDate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 后台的到分页
     * @param pageBean
     */
    @Override
    public void getListAll(PageBean<FoodListEntity> pageBean){
        /**
         * 总页数
         */
        int totalCount = this.getListTotalCount(pageBean);
        pageBean.setTotalCount(totalCount);
        pageBean.setPageCount(15);


        /**
         * 设置PageBean基础数据
         *      如果当前页 大于 1  则当前页为 1 <br/>
         *      如果当前页大于最大页数  则当前页为 最大页数
         *
         */
        if(pageBean.getCurrentPage()<1){
            pageBean.setCurrentPage(1);
        }
        else if(pageBean.getCurrentPage() > pageBean.getTotalPage()){
            pageBean.setCurrentPage(pageBean.getTotalPage());
        }

        /**
         * 得到分页的页数条件
         * @ index 起始页
         * @ count 每页记录数
         */
        int index = (pageBean.getCurrentPage() - 1) * pageBean.getPageCount();
        int count = pageBean.getPageCount();

        String sql_list = "SELECT id, foodName, typeName, price, memberPrice FROM foodList LIMIT ?,?";
        try {
            List<FoodListEntity> foodListEntities = JdbcUtils.getQueryRunner().query(sql_list,new BeanListHandler<FoodListEntity>(FoodListEntity.class),index,count);
            pageBean.setPageData(foodListEntities);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 查询总记录数
     *
     * @param foodPageBean
     * @return
     */
    @Override
    public int getTotalCount(PageBean<Food> foodPageBean) {

        /**
         * sql 语句，为了线程安全使用StringBuffer
         */
        StringBuffer sql_count = new StringBuffer();
        /**
         * 参数 因为参数个数不确定所以使用 集合
         */
        List<Object> params = new ArrayList<>();

        sql_count.append("SELECT");
        sql_count.append("    count(*) ");
        sql_count.append("FROM ");
        sql_count.append("     food f,");
        sql_count.append("     foodType ft ");
        sql_count.append("WHERE 1=1");
        sql_count.append("      AND f.foodTypeId=ft.id ");

        /**
         * 封装了分页查询的条件
         */
        Condition condition = foodPageBean.getCondition();
        if(condition !=null){
            String foodName = condition.getFoodName();
            if(foodName!=null && !foodName.isEmpty()){
                sql_count.append(" AND f.foodName Like ? ");
                params.add("%"+foodName+"%");
            }
            int typeId = condition.getFoodTypeId();
            if(typeId>0){
                sql_count.append(" AND f.foodTypeId=? ");
                params.add(typeId);
            }
        }

        try {
            Long count = JdbcUtils.getQueryRunner().query(sql_count.toString(),
                    new ScalarHandler<Long>(),params.toArray());

            return count.intValue();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询总记录数
     *
     * @param foodPageBean
     * @return
     */
    public int getListTotalCount(PageBean<FoodListEntity> foodPageBean) {

        /**
         * sql 语句，为了线程安全使用StringBuffer
         */
        StringBuffer sql_count = new StringBuffer();

        sql_count.append("SELECT");
        sql_count.append("    count(*) ");
        sql_count.append("FROM ");
        sql_count.append("     foodList");

        /**
         * 封装了分页查询的条件
         */


        try {
            Long count = JdbcUtils.getQueryRunner().query(sql_count.toString(),
                    new ScalarHandler<Long>());

            return count.intValue();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
