package com.omega.summermvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Class SummerHandlerAdapter
 * 处理器适配器
 *
 * @author KennySo
 * @date 2024/7/11
 */
public class SummerHandlerAdapter {

    /**
     * 执行处理器
     * @param request request
     * @param response response
     * @param summerHandler handler
     */
    public void handle(HttpServletRequest request, HttpServletResponse response, SummerHandler summerHandler) {
        try {
            Method method = summerHandler.getMethod();
            // 封装实参数组
            Parameter[] parameters = method.getParameters();
            Object[] params = new Object[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                // 根据 数据类型 判断是否是 HttpServletRequest 和 HttpServletResponse
                if (HttpServletRequest.class.isAssignableFrom(parameters[i].getType())) {
                    params[i] = request;
                } else if (HttpServletResponse.class.isAssignableFrom(parameters[i].getType())) {
                    params[i] = response;
                }
            }
            method.invoke(summerHandler.getController(), params);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
