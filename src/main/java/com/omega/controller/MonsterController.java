package com.omega.controller;

import com.omega.summermvc.annotation.Controller;
import com.omega.summermvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class MonsterController
 *
 * @author KennySo
 * @date 2024/7/9
 */
@Controller
public class MonsterController {

    @RequestMapping("/monster/list")
    public String queryList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<h1>展示 monster 列表</h1>");
        return null;
    }
}
