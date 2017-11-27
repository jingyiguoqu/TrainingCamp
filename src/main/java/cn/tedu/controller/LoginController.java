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
	 * �����¼����
	 */
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request,HttpSession session){
		//��ȡ�û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		//��¼��֤
		User user = service.checkLogin(username, password);
		//��session��֤
		session.setAttribute("user", user);
		//�ض�����ҳ
		return "redirect:toIndex.do";
	}
	//�˳���¼
	@RequestMapping("/signout.do")
	public String signOut(HttpSession session){
		session.removeAttribute("user");
		System.out.println("�˳���¼");
		return "redirect:toLogin.do";
		
	}
	//ҳ��������Ƿ��¼
	@RequestMapping("/checksign.do")
	@ResponseBody
	public JsonResult load(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		boolean i;
		if(obj==null){
			System.out.println("û�е�¼,�ض��򵽵�¼ҳ��");
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
	//�޸�����
		@RequestMapping("/update_pwd.do")
		public String update_pwd(HttpSession session,HttpServletRequest request){
			User user = (User)session.getAttribute("user");
			System.out.println(user);
			String newPwd = request.getParameter("new_pwd");
			System.out.println(newPwd);
			user.setPassword(newPwd);
			service.checkUpdate_pwd(user);
			System.out.println("�޸�����ɹ�");
			return "redirect:toIndex.do";
		}
	@ExceptionHandler
	/*
	 * �쳣����
	 */
	public String exceptionHandler(Exception e,HttpServletRequest request){
		if(e instanceof AppException ){
			if("�û���������!".equals(e.getMessage())){
				request.setAttribute("login_failed", e.getMessage());
			}else{
				request.setAttribute("pwd_failed", e.getMessage());
			}
			return "login";
		}
		return "error";
	}
}
