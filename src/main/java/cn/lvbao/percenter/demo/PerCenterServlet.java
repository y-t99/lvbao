package cn.lvbao.percenter.demo;

import cn.lvbao.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/per_centerdemo")
public class PerCenterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("percenter");


        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getPool());
        String sql="select *,unix_timestamp(skim_time) as skim_timeL from lvbao_trace where article_id='F29169D302144421860C134D4578FA90'";
        Map<String ,Object> map = template.queryForMap(sql);

        request.setAttribute("time", map.get("skim_time"));

//        String imgPath = "E:\\uploadFileTest\\h123.jpg";
//        BufferedImage image = ImageIO.read(new FileInputStream(imgPath));
//        response.setContentType("image/jpeg");
//        //2、获取验证码图片并发送
//        ImageIO.write(image, "jpg", response.getOutputStream());


//        //1、创建工厂
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        //2、用工厂创建解析器
//        ServletFileUpload fileUpload = new ServletFileUpload(factory);
//        //3、解析器解析请求
//        try{
//            List<FileItem> fileItems = fileUpload.parseRequest(request);
//            //4、获取文件表单项，写入文件
//            FileItem imgItem=fileItems.get(0);
//            String id=fileItems.get(1).getString();
//            System.out.println(id);
//            imgItem.write(new File("E:/uploadFileTest/"+id+".jpg"));
//            System.out.println("write");
//        } catch (Exception e){
//            e.printStackTrace();
//        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response );
    }
}
