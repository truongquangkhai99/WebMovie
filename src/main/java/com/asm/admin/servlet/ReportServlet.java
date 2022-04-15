package com.asm.admin.servlet;

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
@WebServlet({"/admin/report/mostLikeVideo", "/admin/report/mostShareVideo", "/admin/report/mostViewVideo"})
public class ReportServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if(uri.contains("mostLikeVideo")) {
			List<Object[]> list = new VideoDAO().mostLikeVideo();
			req.setAttribute("critial", "like");
			req.setAttribute("list", list);
			PageInfo.prepareAndForward(req, resp, PageType.REPORT_MANAGEMENT_PAGE);
		} else if (uri.contains("mostShareVideo")) {
			List<Object[]> list = new VideoDAO().mostShareVideo();
			req.setAttribute("critial", "Share");
			req.setAttribute("list", list);
			PageInfo.prepareAndForward(req, resp, PageType.REPORT_MANAGEMENT_PAGE);
		} else if (uri.contains("mostViewVideo")) {
			List<Object[]> list = new VideoDAO().mostViewVideo();
			req.setAttribute("critial", "View");
			req.setAttribute("list", list);
			PageInfo.prepareAndForward(req, resp, PageType.REPORT_MANAGEMENT_PAGE);
		}
	}

}
