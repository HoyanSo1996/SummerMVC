package com.omega.controller;

import com.omega.summermvc.annotation.Controller;
import com.omega.summermvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class MonsterController
 *
 * @author KennySo
 * @date 2024/7/9
 */
@Controller
public class MonsterController {

    @RequestMapping("/monster/list")
    public String queryList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("MonsterController.queryList() ...");
        return null;
    }
}
