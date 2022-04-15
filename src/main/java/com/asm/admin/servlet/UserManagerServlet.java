package com.asm.admin.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import com.asm.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.asm.common.PageInfo;
import com.asm.common.PageType;
import com.asm.dao.UserDAO;
@WebServlet({"/admin/user", "/admin/user/update", "/admin/user/delete", "/admin/user/create", "/admin/user/edit"})
public class UserManagerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDAO userDAO = new UserDAO();
		String uri = req.getRequestURI().toString();
		if (uri.contains("create")) {
			Users user = new Users();
			try {
				BeanUtils.populate(user, req.getParameterMap());
				userDAO.insert(user);
				req.setAttribute("error", "Insert successfull");
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				req.setAttribute("error", e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				req.setAttribute("error", e.getMessage());
			}
			
		} else if (uri.contains("update")) {
			Users user = new Users();
			try {
				BeanUtils.populate(user, req.getParameterMap());
				userDAO.update(user);
				req.setAttribute("error", "Update successfull");
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				req.setAttribute("error", e.getMessage());
			}
		} else if (uri.contains("delete")) {
			userDAO.delete(req.getParameter("id"));
			req.setAttribute("error", "Delete successfull");
		} else if (uri.contains("edit")) {
			String id = req.getParameter("id");
			Users user = userDAO.findById(id);
			req.setAttribute("user", user);
		}
		List<Users> users = userDAO.findAll();
		req.setAttribute("users", users);
		PageInfo.prepareAndForward(req, resp, PageType.USER_MANAGEMENT_PAGE);
	}

}
