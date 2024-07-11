package com.omega.summermvc.servlet;

import com.omega.summermvc.context.SummerWebApplicationContext;
import com.omega.summermvc.handler.SummerHandler;
import com.omega.summermvc.handler.SummerHandlerAdapter;
import com.omega.summermvc.handler.SummerHandlerMapping;

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

    private final SummerWebApplicationContext summerApplicationContext = new SummerWebApplicationContext();
    private final SummerHandlerMapping summerHandlerMapping = new SummerHandlerMapping();
    private final SummerHandlerAdapter summerHandlerAdapter = new SummerHandlerAdapter();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        summerApplicationContext.init();
        summerHandlerMapping.init(summerApplicationContext, servletConfig.getServletContext().getContextPath());

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
        SummerHandler handler = summerHandlerMapping.getHandler(request.getRequestURI());
        if (handler == null) {
            response.getWriter().write("<h1>404 NOT FOUND</h1>");
        }
        summerHandlerAdapter.handle(request, response, handler);
    }
}
