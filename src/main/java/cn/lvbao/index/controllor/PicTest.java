package cn.lvbao.index.controllor;

import cn.lvbao.util.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanyuan
 * #create 2019-10-11-12:50
 */
@WebServlet("/picTest/*")
public class PicTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> map = new HashMap<>();
        map.put("123","123.png");
        //1、获取资源索引
        String resourse = req.getPathInfo().substring(1);
        //2、读入图片
        InputStream inputStream = new FileInputStream(new File("d://"+map.get(resourse)));
        //3、将图片输出到前端
        try {
            int len=0;
            byte[] bytes=new byte[1024*1024];
            ServletOutputStream outputStream = resp.getOutputStream();
            while((len=inputStream.read(bytes))!=0){
                outputStream.write(bytes,0,len);
            }
            resp.setContentType("image/png");
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
