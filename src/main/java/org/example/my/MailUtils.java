package org.example.my;

/**
 * @Author: Derek
 * @DateTime: 2020/9/7 19:50
 * @Description: TODO
 */
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {
    private static final String USER = "yzh1999@foxmail.com";
    private static final String PASSWORD = "xgxasuvnytenbdgh";

    /**
     *邮件发送
     * @param to        收件人邮箱
     * @param text      邮件正文
     * @param title     标题
     * @return
     */
    public static boolean sendMail(String to, String text, String title){
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.host","smtp.qq.com");

            //发件人的账号
            props.put("mail.user",USER);
            //发件人的密码
            props.put("mail.password",PASSWORD);
            Authenticator authenticator = new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName,password);
                }
            };
            //使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props,authenticator);
            //创建邮件消息
            MimeMessage message = new MimeMessage(mailSession) ;
            //设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);
            //设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message. setRecipient(Message.RecipientType.TO,toAddress);
            //设置邮件标题
            message.setSubject(title);
            //设置邮件的内容体
            message.setContent(text,"text/html;charset=UTF-8");
            //发送邮件
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            MailUtils.sendMail("729035617@qq.com","你好鸡鸡！","阿鸡牛逼");
            System.out.println("发送成功");
        }
    }
}


