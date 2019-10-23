package cn.lvbao.user.util;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Description: 邮件激活的工具类
 * @Date: 2019/10/3 22:36
 */
public class MailUtils {

    public static void sendMail(String to,String subject,String content) throws AddressException, MessagingException {
        // 1.创建连接对象，连接到邮箱服务器
        Properties props = new Properties();
        // 设置邮件服务器 这里使用 163 邮箱
        props.setProperty("mail.smtp.host", "smtp.163.com");
        // 打开认证
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("lms19990808@163.com","6695588lin");
            }
        });
        // 2.创建邮件对象
        Message message = new MimeMessage(session);
        // 2.1设置发件人
        try {
            message.setFrom(new InternetAddress("lms19990808@163.com"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 2.2设置接收人
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        // 2.3设置邮件主题，内容
        message.setSubject(subject);
        message.setContent(content, "text/html;charset=UTF-8");
        // 3.发送邮件
        Transport.send(message);
    }
}
