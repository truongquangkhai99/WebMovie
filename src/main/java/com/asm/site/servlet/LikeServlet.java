package com.asm.site.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asm.dao.FavoritesDAO;
import com.asm.dao.VideoDAO;
import com.asm.model.Favorites;
import com.asm.model.Users;
import com.asm.model.Videos;
@WebServlet({"/like","/like/add", "/like/remove"})
public class LikeServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Users user = (Users) req.getSession().getAttribute("user");
		String uri = req.getRequestURI();
		String url = req.getParameter("url");
		if(user == null ) {
			resp.sendRedirect("/asm/login");
		} else if(user != null) {
			if(uri.contains("add")) {
				String id = req.getParameter("id");
				Videos video = new VideoDAO().findById(id);
				Date date = new Date();
				Favorites favorite = new Favorites(user, video, date);
				FavoritesDAO fd = new FavoritesDAO();
				try {
					fd.insert(favorite);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(url.contains("hobbies")) {
					resp.sendRedirect("/asm/hobbies");
				} else if (url.contains("detail")) {
					resp.sendRedirect("/asm/"+url);
				} else {
					resp.sendRedirect("/asm/home");
				}
				
			}
			else if ( uri.contains("remove")) {
				String id = req.getParameter("id");
				Videos video = new VideoDAO().findById(id);
				FavoritesDAO fd = new FavoritesDAO();
				int favoriteId = fd.findByUserAndVideo(user.getId(), video.getId());
				fd.delete(favoriteId);
				if(url.contains("hobbies")) {
					resp.sendRedirect("/asm/hobbies");
				} else if (url.contains("detail")) {
					resp.sendRedirect("/asm/"+url);
				} else {
					resp.sendRedirect("/asm/home");
				}
			}
			
		}
	}
}
