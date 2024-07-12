package com.omega.controller;

import com.omega.entity.Monster;
import com.omega.service.MonsterService;
import com.omega.summermvc.annotation.Autowired;
import com.omega.summermvc.annotation.Controller;
import com.omega.summermvc.annotation.RequestMapping;
import com.omega.summermvc.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Class MonsterController
 *
 * @author KennySo
 * @date 2024/7/9
 */
@Controller
public class MonsterController {

    @Autowired
    private MonsterService monsterService;

    @RequestMapping("/monster/list")
    public String queryList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Monster> monsterList = monsterService.queryList();

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        StringBuilder content = new StringBuilder("<h1>展示 monster 列表</h1>");
        content.append("<table border='1px' width='500px' style='border-collapse:collapse'>");
        for (Monster monster : monsterList) {
            content
                    .append("<tr>")
                    .append("<td>").append(monster.getId()).append("</td>")
                    .append("<td>").append(monster.getName()).append("</td>")
                    .append("<td>").append(monster.getSkill()).append("</td>")
                    .append("<td>").append(monster.getAge()).append("</td>")
                    .append("</tr>");
        }
        content.append("</table>");
        writer.write(String.valueOf(content));
        return null;
    }


    @RequestMapping("/monster/query")
    public String queryListByName(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestParam("name") String monsterName) throws IOException {
        System.out.println("=== monsterName = " + monsterName + " ===");
        List<Monster> monsterList = monsterService.queryListByName(monsterName);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        StringBuilder content = new StringBuilder("<h1>展示 monster 列表</h1>");
        content.append("<table border='1px' width='500px' style='border-collapse:collapse'>");
        for (Monster monster : monsterList) {
            content
                    .append("<tr>")
                    .append("<td>").append(monster.getId()).append("</td>")
                    .append("<td>").append(monster.getName()).append("</td>")
                    .append("<td>").append(monster.getSkill()).append("</td>")
                    .append("<td>").append(monster.getAge()).append("</td>")
                    .append("</tr>");
        }
        content.append("</table>");
        writer.write(String.valueOf(content));
        return null;
    }


    @RequestMapping("/monster/query2")
    public String queryListByIdAndName(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestParam("name") String monsterName,
                                  String id) throws IOException {
        System.out.println("=== monsterName = " + monsterName + " ===");
        List<Monster> monsterList = monsterService.queryListByIdAndName(Integer.valueOf(id), monsterName);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        StringBuilder content = new StringBuilder("<h1>展示 monster 列表</h1>");
        content.append("<table border='1px' width='500px' style='border-collapse:collapse'>");
        for (Monster monster : monsterList) {
            content
                    .append("<tr>")
                    .append("<td>").append(monster.getId()).append("</td>")
                    .append("<td>").append(monster.getName()).append("</td>")
                    .append("<td>").append(monster.getSkill()).append("</td>")
                    .append("<td>").append(monster.getAge()).append("</td>")
                    .append("</tr>");
        }
        content.append("</table>");
        writer.write(String.valueOf(content));
        return null;
    }


    @RequestMapping("/monster/login")
    public String login(HttpServletRequest request, HttpServletResponse response,
                        String name) {
        System.out.println("=== " + name + " ===");
        if (name.equals("白骨精")) {
            request.setAttribute("name", name);
            // return "login_success";
            return "forward:/login_success.jsp";
        } else {
            // 重定向需要使用 session
            request.getSession().setAttribute("name", name);
            return "redirect:/login_fail.jsp";
        }
    }
}
