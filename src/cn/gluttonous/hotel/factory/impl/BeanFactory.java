package cn.gluttonous.hotel.factory.impl;

import cn.gluttonous.hotel.factory.BeanFactoryInterface;

import java.util.ResourceBundle;

/**
 * @title: hotel
 * @ClassName BeanFactory.java
 * @Description:
 * @Author: liam
 * @Date: 2019/7/23
 * @Version: 1.0
 **/
public class BeanFactory implements BeanFactoryInterface {

    private static ResourceBundle resourceBundle;
    static {
        resourceBundle = ResourceBundle.getBundle("instance");
    }

    public static <T> T getInstance(String key, Class<T> clazz){

        String className = resourceBundle.getString(key);
        try {

            return (T) Class.forName(className).newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
