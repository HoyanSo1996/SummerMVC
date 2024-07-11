package com.omega.summermvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
            method.invoke(summerHandler.getController(), request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
