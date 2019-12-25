package LoginReg;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

@WebServlet(name = "RegServlet", value = "/register")
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决中文乱码
        response.setContentType("text/html;charset=utf-8");
        //先验证验证码
        String checkCode = request.getParameter("checkCode");
        String value = (String) this.getServletContext().getAttribute("key");
        if (checkCode.equals(value)) {
            //获取账号密码
            response.getWriter().write("ok");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            ServletContext servletContext = this.getServletContext();
            String realPath = servletContext.getRealPath("/");
            Properties properties = new Properties();
            properties.load(new FileInputStream(realPath + "/WEB-INF/druid.properties"));
            DataSource ds = null;
            try {
                ds = DruidDataSourceFactory.createDataSource(properties);
            } catch (Exception e) {
                e.printStackTrace();
            }
            QueryRunner qr = new QueryRunner(ds);
            int i = 0;
            try {
                i = qr.update("insert into users values(?,?,?)", Types.NULL, username, password);
                if (i > 0) {
                    response.getWriter().write("<script type=\"text/javascript\"> alert(\"添加成功\");</script>");
                    response.getWriter().write("<script type=\"text/javascript\">window.location.replace(\"/index.jsp\")</script>");
                }
            } catch (SQLException e) {
                response.getWriter().write("<script type=\"text/javascript\"> alert(\"添加失败\");</script>");
                response.getWriter().write("<script type=\"text/javascript\">window.location.replace(\"/Register.jsp\")</script>");
            }


        } else {
            response.getWriter().write("<script type=\"text/javascript\"> alert(\"验证码错误\");</script>");
            response.getWriter().write("<script type=\"text/javascript\">window.location.replace(\"/Register.jsp\")</script>");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        this.doPost(request, response);
    }

}

