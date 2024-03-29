package cn.gluttonous.hotel.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @title: hotel
 * @ClassName EncodingFilter.java
 * @Description: 编码拦截，完成编码处理
 *          * 出现GET中文乱码，是因为在request.getParameter方法内部没有进行提交方式判断并处理。
 *          * String name = request.getParameter("userName");
 *          *
 *          * 解决：对指定接口的某一个方法进行功能扩展，可以使用代理!
 *          *      对request对象(目标对象)，创建代理对象！
 * @Author: liam
 * @Date: 2019/7/23
 * @Version: 1.0
 **/
public class EncodingFilter implements Filter {

    /**
     * 过滤器业务处理方法：处理的公用的业务逻辑操作
      */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        // 转型
        final HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // 一、处理公用业务

        // POST提交有效
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpServletRequest proxy =  (HttpServletRequest) Proxy.newProxyInstance(
                // 指定当前使用的类加载器
                request.getClass().getClassLoader(),
                // 对目标对象实现的接口类型
                new Class[]{HttpServletRequest.class},
                // 事件处理器
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        // 定义方法返回值
                        Object returnValue = null;
                        // 获取方法名
                        String methodName = method.getName();
                        // 判断：对getParameter方法进行GET提交中文处理
                        if ("getParameter".equals(methodName)) {
                                // 调用目标对象的方法
                            // 获取请求数据值【 <input type="text" name="userName">】
                            String value = request.getParameter(args[0].toString());

                                // 直接调用目标对象的方法
                            // 获取提交方式
                            String methodSubmit = request.getMethod();

                            // 判断如果是GET提交，需要对数据进行处理  (POST提交已经处理过了)
                            if ("GET".equals(methodSubmit)) {
                                if (value != null && !"".equals(value.trim())){
                                    // 处理GET中文
                                    value = new String(value.getBytes("ISO8859-1"),"UTF-8");
                                }
                            }
                            return value;
                        }
                        else {
                            // 执行request对象的其他方法
                            returnValue = method.invoke(request, args);
                        }

                        return returnValue;
                    }
                });

        // 二、放行 (执行下一个过滤器或者servlet)
        // 传入代理对象
        chain.doFilter(proxy, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}

