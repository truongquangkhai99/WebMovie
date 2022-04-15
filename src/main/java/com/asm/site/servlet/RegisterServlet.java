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
@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PageInfo.prepareAndForward(req, resp, PageType.SITE_REGISTER_PAGE);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Users user = new Users();
		String confirm = req.getParameter("password2");
		try {
			BeanUtils.populate(user, req.getParameterMap());
			if(user.getPassword().equals(confirm)) {
				UserDAO ud = new UserDAO();
				ud.insert(user);
				req.setAttribute("error", "Registrator successfull");
			} else {
				req.setAttribute("error", "Password's not match");
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForward(req, resp, PageType.SITE_REGISTER_PAGE);
	}

}
