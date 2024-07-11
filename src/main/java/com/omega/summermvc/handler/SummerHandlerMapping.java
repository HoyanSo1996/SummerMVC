package com.omega.summermvc.handler;

import com.omega.summermvc.annotation.Controller;
import com.omega.summermvc.annotation.RequestMapping;
import com.omega.summermvc.context.SummerWebApplicationContext;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class SummerHandlerMapping
 * 处理器映射器
 *
 * @author KennySo
 * @date 2024/7/11
 */
public class SummerHandlerMapping {

    private final ConcurrentHashMap<String, SummerHandler> handlerMap = new ConcurrentHashMap<>();


    /**
     * 初始化
     * @param context summer 容器
     * @param contextPath 项目路径
     */
    public void init(SummerWebApplicationContext context, String contextPath) {
        ConcurrentHashMap<String, Object> singletonObjects = context.getSingletonObjects();
        if (singletonObjects.isEmpty()) {
            return;
        }
        // 封装 url 和 controller 方法的映射关系
        for (Map.Entry<String, Object> singletonObjectEntry : singletonObjects.entrySet()) {
            Object bean = singletonObjectEntry.getValue();
            Class<?> beanClazz = bean.getClass();
            if (beanClazz.isAnnotationPresent(Controller.class)) {
                String url1 = "";
                if (beanClazz.isAnnotationPresent(RequestMapping.class)) {
                    url1 = beanClazz.getAnnotation(RequestMapping.class).value();
                }
                Method[] declaredMethods = beanClazz.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    if (declaredMethod.isAnnotationPresent(RequestMapping.class)) {
                        String url2 = declaredMethod.getAnnotation(RequestMapping.class).value();
                        SummerHandler summerHandler = new SummerHandler(bean, declaredMethod);
                        handlerMap.put(contextPath + url1 + url2, summerHandler);
                    }
                }
            }
        }
    }

    public SummerHandler getHandler(String url) {
        return handlerMap.get(url);
    }
}
