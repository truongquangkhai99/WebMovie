package com.asm.site.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asm.common.PageInfo;
import com.asm.common.PageType;
import com.asm.dao.UserDAO;
import com.asm.model.Users;
@WebServlet("/forgetPassword")
public class ForgetPasswordServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PageInfo.prepareAndForward(req, resp, PageType.SITE_FORGOT_PASSWORD_PAGE);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = req.getParameter("email");
		UserDAO ud = new UserDAO();
		Users user = ud.getPassword(email);
		String to = user.getEmail();
		String subject = "forget password";
		String body = "this is password: " + user.getPassword();
		//1. khai báo thông số smtp sever
		Properties props = new Properties();
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		//2. cổng ra vào dữ liệu
		props.put("mail.smtp.port", 587);
		// kết nối smtp sever
		//get Session
		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				//3.chỉ ra mail của bạn và mật khẩu
		return new PasswordAuthentication("tainnps19064@fpt.edu.vn", "nhutai1302");
			}
		});	
		MimeMessage message= new MimeMessage(session);
		
		//gửi thư đi
		try {
			message.setRecipients(Message.RecipientType.TO, to);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//tiêu đề
		try {
			message.setSubject(subject);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//nội dung
		try {
			message.setText(body);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//gửi message
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("error", "your password is send");
		PageInfo.prepareAndForward(req, resp, PageType.SITE_FORGOT_PASSWORD_PAGE);
	}
}
