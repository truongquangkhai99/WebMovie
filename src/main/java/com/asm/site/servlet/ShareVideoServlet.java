package com.asm.site.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asm.dao.VideoDAO;
import com.asm.model.Share;
import com.asm.model.Users;
import com.asm.model.Videos;
import com.asm.common.PageInfo;
import com.asm.common.PageType;
import com.asm.dao.ShareDAO;
@WebServlet("/share")
public class ShareVideoServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		Users user = (Users) req.getSession().getAttribute("user");
		if(user != null){
			String id = req.getParameter("id");
			String email = req.getParameter("email");
			Videos video = new VideoDAO().findById(id);
			Date date = new Date();
			Share s = new Share(user, video, email, date);
			ShareDAO sd = new ShareDAO();
			try {
				sd.insert(s);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String to = s.getEmail();
			String subject = user.getFullname() + " send video to you!";
			String body = "this id link of that video " +video.getDescription();
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
			try {		
			MimeMessage message= new MimeMessage(session);
			
			//gửi thư đi
			message.setRecipients(Message.RecipientType.TO, to);
			//tiêu đề
			message.setSubject(subject);
			//nội dung
			message.setText(body);

			//gửi message
			Transport.send(message);
			req.setAttribute("message", "your mail sent... message sent successfully");
			String title = req.getParameter("title") == null ? "%%" : req.getParameter("title");
			String page = req.getParameter("page") == null ? "1" : req.getParameter("page");
			VideoDAO vd = new VideoDAO();
			try {
				List<Videos> videos = vd.findAll(title, 12 * (Integer.valueOf(page)) - 11,
						12 * (Integer.valueOf(page)) - 11 + 11);
				req.setAttribute("videos", videos);
				req.setAttribute("size", Math.ceil(vd.findByTitle(title).size() / 12.0));
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("error", e.getMessage());
			}
			PageInfo.prepareAndForward(req, resp, PageType.SITE_HOME_PAGE);
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} else if (user == null) {
			resp.sendRedirect("/asm/login");
		}
		
		
	}

}
