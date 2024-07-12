package com.omega.summermvc.handler;

import com.omega.summermvc.annotation.RequestParam;

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
    public Object handle(HttpServletRequest request, HttpServletResponse response, SummerHandler summerHandler) {
        try {
            Method method = summerHandler.getMethod();
            // 处理中文乱码问题
            request.setCharacterEncoding("utf-8");
            // 封装实参数组
            Parameter[] parameters = method.getParameters();
            Object[] params = new Object[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                // 1.根据 数据类型 判断是否是 HttpServletRequest 和 HttpServletResponse
                if (HttpServletRequest.class.isAssignableFrom(parameters[i].getType())) {
                    params[i] = request;
                    continue;
                } else if (HttpServletResponse.class.isAssignableFrom(parameters[i].getType())) {
                    params[i] = response;
                    continue;
                }

                String paramName;
                if (parameters[i].isAnnotationPresent(RequestParam.class)) {
                    // 2.根据 @RequestParam 注解进行请求参数映射
                    paramName = parameters[i].getAnnotation(RequestParam.class).value();
                } else {
                    // 3.按 默认机制 进行请求参数映射
                    // 在默认情况下，返回的并不是 request, response, name 而是 arg0, arg1, arg2,
                    // 这里需要使用到 jdk8 的新特性, 直呼要在 pom.xml 中配置 maven 编译插件就能得到参数名
                    paramName = parameters[i].getName();
                }
                params[i] = request.getParameter(paramName);
            }
            // Tips: 如果有些参数没传, 那么值为 null, 反射调用时会报异常, 这是正常的, springMVC 也这样
            return method.invoke(summerHandler.getController(), params);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
