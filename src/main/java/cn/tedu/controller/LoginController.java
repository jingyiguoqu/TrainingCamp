package cn.tedu.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.entity.TrainingCamp;
import cn.tedu.entity.User;
import cn.tedu.interceptors.LoginInterceptor;
import cn.tedu.service.AppException;
import cn.tedu.service.LoginService;
import cn.tedu.util.JsonResult;

@Controller
public class LoginController {
	@Resource(name="loginService")
	private LoginService service;
	
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		return "login";
	}
	/*
	 * 处理登录请求
	 */
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request,HttpSession session){
		//读取用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		//登录验证
		User user = service.checkLogin(username, password);
		//绑定session验证
		session.setAttribute("user", user);
		//重定向到主页
		return "redirect:toIndex.do";
	}
	//退出登录
	@RequestMapping("/signout.do")
	public String signOut(HttpSession session){
		session.removeAttribute("user");
		System.out.println("退出登录");
		return "redirect:toLogin.do";
		
	}
	//页面点击检查是否登录
	@RequestMapping("/checksign.do")
	@ResponseBody
	public JsonResult load(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		boolean i;
		if(obj==null){
			System.out.println("没有登录,重定向到登录页面");
			i=false;
		}else{
			i=true;
		}
		return new JsonResult(i);
	}
	@RequestMapping("/toIndex.do")
	public String toIndex(HttpServletRequest request){
		List<TrainingCamp> Tclist=service.Tclist();
		request.setAttribute("Tclist", Tclist);
		return "index_admin";
	}
	//修改密码
		@RequestMapping("/update_pwd.do")
		public String update_pwd(HttpSession session,HttpServletRequest request){
			User user = (User)session.getAttribute("user");
			System.out.println(user);
			String newPwd = request.getParameter("new_pwd");
			System.out.println(newPwd);
			user.setPassword(newPwd);
			service.checkUpdate_pwd(user);
			System.out.println("修改密码成功");
			return "redirect:toIndex.do";
		}
	@ExceptionHandler
	/*
	 * 异常处理
	 */
	public String exceptionHandler(Exception e,HttpServletRequest request){
		if(e instanceof AppException ){
			if("用户名不存在!".equals(e.getMessage())){
				request.setAttribute("login_failed", e.getMessage());
			}else{
				request.setAttribute("pwd_failed", e.getMessage());
			}
			return "login";
		}
		return "error";
	}
}
