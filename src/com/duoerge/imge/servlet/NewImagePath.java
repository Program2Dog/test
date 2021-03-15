package com.duoerge.imge.servlet;

import com.duoerge.imge.util.ImageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "NewImagePath",urlPatterns = "/newImagePath")
public class NewImagePath extends HttpServlet {
//    初始化属性
//    List<String> imgUrls = ImageUtil.InputFile();
//    int page = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = "0";
        id = request.getParameter("id");
        HttpSession session = request.getSession();
//        如果没有初始化过就执行初始化
        if (session.getAttribute("imgUrls") == null){
            System.out.println("-----初始化了属性!-----");
            session.setAttribute("imgUrls",ImageUtil.InputFile());
            session.setAttribute("page",0);
        }
        if (id != null && Integer.valueOf(id) == 1){
            response.sendRedirect("http://file.u-flie.duoerge.cn/images/1000plus/0adfbe3100a96355ef8d3d616574bd49.png");
        }else if(id == null){
//            真随机
//            int a = 0;
//            a = (int) (Math.random()*imgUrls.size());
//            System.out.println("随机图片的序号是:"+a);
//            response.sendRedirect(imgUrls.get(a));
//            伪随机
            System.out.println("-----设置了属性!-----");
            List<String> imgUrls = (List<String>) session.getAttribute("imgUrls");
            int page = (int) session.getAttribute("page");
//            重新获取一遍图片链接集合并且重置图片序号
            if (page == imgUrls.size()){
//                imgUrls = ImageUtil.InputFile();
//                page = 0;
                System.out.println("-----重置了属性!-----");
                session.setAttribute("imgUrls",ImageUtil.InputFile());
                session.setAttribute("page",0);
                imgUrls = (List<String>) session.getAttribute("imgUrls");
                page = (int) session.getAttribute("page");
            }
//            重新排序一下图片链接集合
            if (page == 0){
                System.out.println("-----设置了图片地址集合的乱序!-----");
                Collections.shuffle(imgUrls);
                session.setAttribute("imgUrls",imgUrls);
            }
            System.out.println("-----执行了重定向!-----");
            response.sendRedirect(imgUrls.get(page));
            System.out.println("-----输出了信息!-----");
            System.out.println("SessionID是:"+session.getId());
            System.out.println("图片序号是:"+page);
            System.out.println("图片的链接是:");
            System.out.println(imgUrls.get(page));
            ImageUtil.getFileInfo(imgUrls.get(page));
//            page++;
            System.out.println("-----图片序号增加了!-----");
            page++;
            session.setAttribute("page",page);
        }else {
            response.getWriter().write("false");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
