package com.omega.summermvc.servlet;

import com.omega.summermvc.annotation.ResponseBody;
import com.omega.summermvc.context.SummerWebApplicationContext;
import com.omega.summermvc.handler.SummerHandler;
import com.omega.summermvc.handler.SummerHandlerAdapter;
import com.omega.summermvc.handler.SummerHandlerMapping;
import com.omega.summermvc.util.JsonUtils;
import com.omega.summermvc.view.SummerViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class SummerDispatchServlet
 *
 * @author KennySo
 * @date 2024/7/9
 */
public class SummerDispatchServlet extends HttpServlet {

    private SummerWebApplicationContext summerApplicationContext;
    private SummerHandlerMapping summerHandlerMapping;
    private SummerHandlerAdapter summerHandlerAdapter;
    private SummerViewResolver summerViewResolver;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // 初始化容器
        summerApplicationContext = new SummerWebApplicationContext(servletConfig.getInitParameter("contextConfigLocation").split(":")[1]);
        summerApplicationContext.init();

        // 初始化处理器映射器
        summerHandlerMapping = new SummerHandlerMapping();
        summerHandlerMapping.init(summerApplicationContext, servletConfig.getServletContext().getContextPath());

        // 初始化处理器适配器
        summerHandlerAdapter = new SummerHandlerAdapter();

        // 初始化视图解析器
        summerViewResolver = new SummerViewResolver();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doDispatch(request, response);
    }

    public void doDispatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1.调用 HandlerMapping
        SummerHandler handler = summerHandlerMapping.getHandler(request.getRequestURI());
        if (handler == null) {
            response.getWriter().write("<h1>404 NOT FOUND</h1>");
            return;
        }
        // 2.调用 HandlerAdapter
        Object result = summerHandlerAdapter.handle(request, response, handler);

        // 判断是否有 @responseBody 注解
        if (handler.getMethod().isAnnotationPresent(ResponseBody.class)) {
            String resultJson = JsonUtils.toJsonString(result);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(resultJson);
            return;
        }

        // 3.调用 ViewResolver (兼有 View 功能)
        if (result instanceof String) {
            summerViewResolver.resolveViewName((String) result, request, response);
        }
    }
}
