package com.omega.summermvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class SummerViewResolver And View
 *
 * @author KennySo
 * @date 2024/7/12
 */
public class SummerViewResolver {

    private final String prefix = "/";
    private final String suffix = ".jsp";


    /**
     * 默认视图解析机制
     * @param viewName 视图名
     * @param request request
     * @param response response
     */
    public void resolveViewName(String viewName,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            if (viewName.contains(":")) {
                String viewType = viewName.split(":")[0];
                String viewPage = viewName.split(":")[1];
                if ("forward".equalsIgnoreCase(viewType)) {
                    request.getRequestDispatcher(viewPage).forward(request, response);
                } else if ("redirect".equalsIgnoreCase(viewType)) {
                    response.sendRedirect(request.getContextPath() + viewPage);
                }
            } else {
                request.getRequestDispatcher(prefix + viewName + suffix).forward(request, response);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
