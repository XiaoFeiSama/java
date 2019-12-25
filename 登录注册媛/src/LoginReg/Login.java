package LoginReg;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "Login",value = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //定义标签
        boolean flag = true;
        //解决中文乱码
        response.setContentType("text/html;charset=utf-8");
        //从页面上获取账户密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //获取记住密码信息
        String rember = request.getParameter("rember");
        //从数据库中获取对应信息储存到一个集合中
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
        List<User> users = null;
        try {
            users = qr.query("select * from users", new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //循环比较用户信息
        for (User user : users) {
            if (username.equals(user.getUser()) && password.equals(user.getPassword())) {
                if (rember != null) {
                    Cookie uCookie = new Cookie("username", user.getUser());
                    Cookie pCookie = new Cookie("password", user.getPassword());
                    //设置cookie的存活时间
                    uCookie.setMaxAge(60 * 60 * 24 * Integer.parseInt(rember));
                    pCookie.setMaxAge(60 * 60 * 24 * Integer.parseInt(rember));
                    response.addCookie(uCookie);
                    response.addCookie(pCookie);
                }
                    flag = false;
                    response.getWriter().write("ok");
                    //跳转至商品页面
                    request.getRequestDispatcher("/WEB-INF/MySpace.jsp").forward(request, response);
                    break;
                }
            }
            if (flag) {
                //登录失败并重载页面
                response.getWriter().write("<script type=\"text/javascript\"> alert(\"登录失败\");</script>");
                response.getWriter().write("<script type=\"text/javascript\">window.location.replace(\"/index.jsp\")</script>");
            }

        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
