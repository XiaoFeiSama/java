package LoginReg;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
import java.util.List;
import java.util.Properties;

@WebServlet(name = "CommServlet",value = "/comm")
public class CommServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Comm.jsp").forward(request, response);
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
        List<Commodity> comm = null;
        try {
            comm = qr.query("SELECT * FROM shangpi ORDER BY sno", new BeanListHandler<Commodity>(Commodity.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().write("asdfas");
        for (Commodity commodity : comm) {

            response.getWriter().write(commodity.getSno() + ".    " + commodity.getName() + "    " + commodity.getPrice());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
