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
import com.asm.model.Favorites;
import com.asm.model.Users;
import com.asm.model.Videos;

@WebServlet("/home")
public class HomePageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String title = req.getParameter("title") == null ? "%%" : req.getParameter("title");
		String page = req.getParameter("page") == null ? "1" : req.getParameter("page");
		VideoDAO vd = new VideoDAO();
		Users user = (Users) req.getSession().getAttribute("user");
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

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String title = req.getParameter("title");
		VideoDAO vd = new VideoDAO();
		List<Videos> videos = vd.query(
				"select o from Videos o where (id like :n or title like :n) and cast(o.id as int) >= 1 and cast(o.id as int) <= 10",
				title);

		req.setAttribute("videos", videos);
		req.setAttribute("size", Math.ceil(vd.findAll().size() / 10.0));
		PageInfo.prepareAndForward(req, resp, PageType.SITE_HOME_PAGE);
	}

}
