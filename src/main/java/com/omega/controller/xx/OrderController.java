package com.omega.controller.xx;

import com.omega.summermvc.annotation.Controller;
import com.omega.summermvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class OrderController
 *
 * @author KennySo
 * @date 2024/7/11
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/list")
    public String queryList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<h1>展示 order 列表</h1>");
        return null;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<h1>添加 order 成功</h1>");
        return null;
    }
}
