package com.omega.summermvc.servlet;

import com.omega.summermvc.context.SummerWebApplicationContext;
import com.omega.summermvc.handler.SummerHandler;
import com.omega.summermvc.handlerMapping.SummerHandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Class SummerDispatchServlet
 *
 * @author KennySo
 * @date 2024/7/9
 */
public class SummerDispatchServlet extends HttpServlet {

    private final SummerWebApplicationContext summerApplicationContext = new SummerWebApplicationContext();
    private final SummerHandlerMapping summerHandlerMapping = new SummerHandlerMapping();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        summerApplicationContext.init();
        summerHandlerMapping.init(summerApplicationContext, servletConfig.getServletContext().getContextPath());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进来了...");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
