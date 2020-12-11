package com.web.haier.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


//import com.lh.ui.Index;

public class MyUtil {
	
	public String myEmail = "lh15873424347@163.com";
	public String myHost = "smtp.163.com";
	public String myEmailPwssword = "YAXRVNWCUVZIBNPD";

	//发送邮件准备工作：邮箱，SMTP服务，授权码
	public String senEmail(String email){
		//随机获取六位的验证码
		Random r = new Random();
		String code = "";
		for(int i = 0; i < 6; i++){
			code += r.nextInt(10);
		}
		
		//开始发邮件
		Properties p = new Properties();
		p.setProperty("mail.transport.protocol" , "smtp"); //使用smtp协议发送邮件
		p.setProperty("mail.smtp.host", myHost); //主机地址
		p.setProperty("mail.smtp.auth", "true");//授权通过
		Session session = Session.getInstance(p);
		session.setDebug(true);
		
		//创建邮件
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom( new InternetAddress(myEmail , "一花一世界" , "utf-8")); //设置发件人
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email , "用户" , "utf-8"));//设置收件人
			message.setSubject(" 鸡犬不相闻  " , "utf-8"); //邮件主题
			message.setContent(" 打死不相往来 " + code , "text/html;charset=utf-8");//内容
			message.setSentDate(new Date());
			message.saveChanges(); //保存好
			//开始发送
			Transport transport = session.getTransport();
			transport.connect(myEmail , myEmailPwssword);
			transport.sendMessage(message , message.getAllRecipients());
			transport.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}	
		
		return code;	
	}
}