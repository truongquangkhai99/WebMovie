package com.asm.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageInfo {

	public static Map<PageType, PageInfo> pageRoute = new HashMap();
	
	static {
		pageRoute.put(PageType.USER_MANAGEMENT_PAGE, new PageInfo("User management", "/admin/user.jsp", null));
		pageRoute.put(PageType.SITE_HOME_PAGE, new PageInfo("Home", "item.jsp", null));
		pageRoute.put(PageType.DETAIL_VIDEO_PAGE, new PageInfo("Video detail", "detail-video.jsp", null));
		pageRoute.put(PageType.SITE_LOGIN_PAGE, new PageInfo("Login", "login.jsp", null));
		pageRoute.put(PageType.SITE_REGISTER_PAGE, new PageInfo("Register", "register.jsp", null));
		pageRoute.put(PageType.SITE_FORGOT_PASSWORD_PAGE, new PageInfo("Forget Password", "forgetPassword.jsp", null));
		pageRoute.put(PageType.Video_MANAGEMENT_PAGE, new PageInfo("Video management", "/admin/video.jsp", null));
		pageRoute.put(PageType.SITE_FAVORITE_PAGE, new PageInfo("Favorites Videos", "favoritesvideos.jsp", null));
		pageRoute.put(PageType.REPORT_MANAGEMENT_PAGE, new PageInfo("Report", "/admin/report.jsp", null));
	}
	
	public static void prepareAndForward(HttpServletRequest res, HttpServletResponse resp, PageType pageType) throws ServletException, IOException {
		PageInfo page = pageRoute.get(pageType);
		res.setAttribute("page", page);
		res.getRequestDispatcher("/views/layout.jsp").forward(res, resp);
	}
	
	private String title;
	private String contentUrl;
	private String scriptUrl;
	
	public PageInfo(String title, String contentUrl, String scriptUrl) {
		super();
		this.title = title;
		this.contentUrl = contentUrl;
		this.scriptUrl = scriptUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public String getScriptUrl() {
		return scriptUrl;
	}
	public void setScriptUrl(String scriptUrl) {
		this.scriptUrl = scriptUrl;
	}
	
	

}
