package com.asm.site.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asm.common.PageInfo;
import com.asm.common.PageType;
import com.asm.dao.VideoDAO;
import com.asm.model.Videos;

@WebServlet("/detailVideo")
public class DetailVideoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String videoId = req.getParameter("videoId");
		if (videoId != null) {
			try {
				VideoDAO ud = new VideoDAO();
				List<Videos> videos = ud.query(
						"select o from Videos o where o.title in (select t.title from Videos t where t.id like :n)",
						videoId);
				Videos video = ud.findById(videoId);
				video.setViews(video.getViews() + 1);
				ud.update(video);
				req.setAttribute("videos", videos);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("error", e.getMessage());
			}
			PageInfo.prepareAndForward(req, resp, PageType.DETAIL_VIDEO_PAGE);
		}

	}

}
