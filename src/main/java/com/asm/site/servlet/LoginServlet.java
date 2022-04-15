package com.asm.site.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.asm.common.PageInfo;
import com.asm.common.PageType;
import com.asm.dao.UserDAO;
import com.asm.model.Users;
@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PageInfo.prepareAndForward(req, resp, PageType.SITE_LOGIN_PAGE);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Users user = new Users();
		try {
			BeanUtils.populate(user, req.getParameterMap());
			user = new UserDAO().checkAccount(user);
			if(user != null) {
				req.getSession().setAttribute("user", user);
				System.out.println("Email: " + ((Users)req.getSession().getAttribute("user")).getEmail());
				if(user.getAdmin()) {
					resp.sendRedirect("/asm/admin/user");
				} else {
					resp.sendRedirect("/asm/home");
				}
				
			} else {
				req.setAttribute("error", "Account isn't exist");
				PageInfo.prepareAndForward(req, resp, PageType.SITE_LOGIN_PAGE);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
			PageInfo.prepareAndForward(req, resp, PageType.SITE_LOGIN_PAGE);
		}
		
	}

}
