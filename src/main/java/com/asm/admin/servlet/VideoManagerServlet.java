package com.asm.admin.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.asm.common.PageInfo;
import com.asm.common.PageType;
import com.asm.dao.VideoDAO;
import com.asm.model.Videos;
@MultipartConfig
@WebServlet({"/admin/video", "/admin/video/create", "/admin/video/update", "/admin/video/delete", "/admin/video/edit"})
public class VideoManagerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		VideoDAO videoDAO = new VideoDAO();
		String uri = req.getRequestURI().toString();
		if (uri.contains("create")) {
			Videos video = new Videos();
			try {
				BeanUtils.populate(video, req.getParameterMap());
				Part photo = req.getPart("poster");
				System.out.println(photo.getSubmittedFileName());
//				videoDAO.insert(video);
//				req.setAttribute("error", "Insert successfull");
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				req.setAttribute("error", e.getMessage());
			}
			
		} else if (uri.contains("update")) {
			Videos video = new Videos();
			try {
				BeanUtils.populate(video, req.getParameterMap());
				videoDAO.update(video);
				req.setAttribute("error", "Update successfull");
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				req.setAttribute("error", e.getMessage());
			}
		} else if (uri.contains("delete")) {
			videoDAO.delete(req.getParameter("id"));
			req.setAttribute("error", "Delete successfull");
		} else if (uri.contains("edit")) {
			String id = req.getParameter("id");
			Videos video = videoDAO.findById(id);
			req.setAttribute("video", video);
		}
		List<Videos> videos = videoDAO.findAll();
		req.setAttribute("videos", videos);
		PageInfo.prepareAndForward(req, resp, PageType.Video_MANAGEMENT_PAGE);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		VideoDAO videoDAO = new VideoDAO();
		String uri = req.getRequestURI().toString();
		if (uri.contains("create")) {
			Videos video = new Videos();
			try {
				BeanUtils.populate(video, req.getParameterMap());
				Part photo = req.getPart("poster");
				video.setPoster(photo.getSubmittedFileName());
				File file = new File("D:\\hoctap\\java\\java4\\baitap\\asm\\src\\main\\webapp\\img\\"+photo.getSubmittedFileName());
				file.createNewFile();
				photo.write(file.getAbsolutePath());
				videoDAO.insert(video);
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
			Videos video = new Videos();
			try {
				BeanUtils.populate(video, req.getParameterMap());
				Part photo = req.getPart("poster");
				File file = new File("D:\\hoctap\\java\\java4\\baitap\\asm\\src\\main\\webapp\\img\\"+photo.getSubmittedFileName());
				video.setPoster(photo.getSubmittedFileName());
				file.createNewFile();
				photo.write(file.getAbsolutePath());
				videoDAO.update(video);
				req.setAttribute("error", "Update successfull");
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				req.setAttribute("error", e.getMessage());
			}
		} else if (uri.contains("delete")) {
			videoDAO.delete(req.getParameter("id"));
			req.setAttribute("error", "Delete successfull");
		} else if (uri.contains("edit")) {
			String id = req.getParameter("id");
			Videos video = videoDAO.findById(id);
			req.setAttribute("video", video);
		}
		List<Videos> videos = videoDAO.findAll();
		req.setAttribute("videos", videos);
		PageInfo.prepareAndForward(req, resp, PageType.Video_MANAGEMENT_PAGE);
	}

}
