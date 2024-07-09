package com.omega.summermvc.servlet;

import com.omega.summermvc.context.SummerWebApplicationContext;

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

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        SummerWebApplicationContext summerApplicationContext = new SummerWebApplicationContext();
        summerApplicationContext.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进来了...");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
