package LoginReg;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "CheckcodeServlet",value = "/code")
public class CheckcodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //JUI
        int width=200;
        int height=100;
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        //中间美化图片
        //获取画笔
        Graphics g = img.getGraphics();
        g.setColor(Color.PINK);
        //填充背景
        g.fillRect(0,0,width,height);
        //画边框
        g.setColor(Color.BLUE);
        //注意减一 因为边框会占1个像素
        g.drawRect(0,0,width-1,height-1);
        //写字
        String str="abcdyfghigklmnopqrstuvwsyz";
        g.setColor(Color.BLACK);

        g.setFont(new Font("宋体", Font.PLAIN, 30));
        Random random = new Random();
        String s="";
        for (int i = 1; i <= 4; i++) {
            int index = random.nextInt(str.length());
            char c = str.charAt(index);
            s+=c;
            g.drawString(c+"", width / 5 * i, height / 2);

        }
        this.getServletContext().setAttribute("key",s);
        //画干扰线
        g.setColor(Color.green);

        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(width);
            int x2= random.nextInt(width);

            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }




        //把这张画的图片响应给浏览器
        ImageIO.write(img,"png",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
