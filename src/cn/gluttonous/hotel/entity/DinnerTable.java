package cn.gluttonous.hotel.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @title: hotel
 * @ClassName DinnerTable.java
 * @Description:
 *              id INT ,  -- 餐桌主键
 *              tableName VARCHAR(20),	       -- 餐桌名
 *              tableStatus INT DEFAULT 0,     -- 餐桌状态：0，空闲； 1，预定
 *              orderDate DATETIME
 * @Author: liam
 * @Date: 2019/7/24
 * @Version: 1.0
 **/
public class DinnerTable {
    /**
     * @IDLE_STATUS 空闲状态
     */
    public final int IDLE_STATUS = 0;
    /**
     * @BOOK_STATUS 预定状态
     */
    public final int BOOK_STATUS = 1;


    private int id;
    private String tableName;
    private int tableStatus;
    private Date orderDate;

    public DinnerTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(int tableStatus) {
        this.tableStatus = tableStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        DinnerTable that = (DinnerTable) o;
        return IDLE_STATUS == that.IDLE_STATUS &&
                BOOK_STATUS == that.BOOK_STATUS &&
                id == that.id &&
                tableStatus == that.tableStatus &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(orderDate, that.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IDLE_STATUS, BOOK_STATUS, id, tableName, tableStatus, orderDate);
    }

    @Override
    public String toString() {
        return "DinnerTable{" +
                "IDLE_STATUS=" + IDLE_STATUS +
                ", BOOK_STATUS=" + BOOK_STATUS +
                ", id=" + id +
                ", tableName='" + tableName + '\'' +
                ", tableStatus=" + tableStatus +
                ", orderDate=" + orderDate +
                '}';
    }
}
