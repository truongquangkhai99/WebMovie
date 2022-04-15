package com.asm.site.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asm.common.PageInfo;
import com.asm.common.PageType;
import com.asm.dao.VideoDAO;
import com.asm.model.Users;
import com.asm.model.Videos;

@WebServlet("/hobbies")
public class HobbiesServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Users user = (Users) req.getSession().getAttribute("user");
		String page = req.getParameter("page") == null ? "1" : req.getParameter("page");
		if (user == null) {
			resp.sendRedirect("/asm/login");
		} else if (user != null) {
			VideoDAO vd = new VideoDAO();
			List<Videos> videos = vd.favoriteVideo(user.getId(), 12 * (Integer.valueOf(page)) - 11,
					12 * (Integer.valueOf(page)) - 11 + 11);
			req.setAttribute("videos", videos);
			req.setAttribute("size", Math.ceil(vd.favoriteVideo(user.getId(), 0, 10000).size() / 12.0));
			PageInfo.prepareAndForward(req, resp, PageType.SITE_FAVORITE_PAGE);
		}
		
	}
}
