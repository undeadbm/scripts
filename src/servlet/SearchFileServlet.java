package servlet;

import bean.Report;
import dao.ReportDAO;
import util.PropertiesUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ren on 2018/10/27.
 */
@WebServlet(name = "searchFileServlet")
public class SearchFileServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReportDAO r = new ReportDAO();
        List<Report> reports = new ArrayList<>();
        request.setCharacterEncoding("UTF-8");

        String searchText = request.getParameter("searchText").trim();
        String reportletPath = PropertiesUtil.reportletPath;

        File javaFile = new File(reportletPath);
        r.search(javaFile, searchText);
        if (searchText.trim().length() == 0) {
             reports = r.listLostReport();
        } else {
          reports = r.listReport();
        }

        request.setAttribute("reports", reports);

        request.setAttribute("searchText", searchText);
        request.getRequestDispatcher("searchFile.jsp").forward(request, response);
    }


}
