package cn.tedu.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.entity.User;
import cn.tedu.service.LoginService;
import cn.tedu.util.JsonResult;

@Controller
public class UserController {
	@Resource(name="loginService")
	private LoginService service;
	
	@RequestMapping("/userlist.do")
	@ResponseBody
	public JsonResult load(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int num=Integer.parseInt(request.getParameter("num"));
		System.out.println(num);
		List<User> userlist=service.userlist(num);
		return new JsonResult(userlist);
	}
	
	@RequestMapping("/userupdate.do")
	@ResponseBody
	public JsonResult userupdate(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		User user=new User();
		String jobName=request.getParameter("jobName");
		user.setJobName(jobName);
		String username=request.getParameter("username");
		user.setUsername(username);
		user.setUserId(Integer.parseInt(request.getParameter("userId")));
		String password=request.getParameter("password");
		user.setPassword(password);
		String phone=request.getParameter("phone");
		user.setPhone(phone);
		System.out.println(user);
		service.updateuser(user);
		return new JsonResult(user);
	}
	
	@RequestMapping("/list_job.do")
	@ResponseBody
	public List<User> listJob(){
		return service.listJob();
	}
	//保存用户
	@RequestMapping("/usersave.do")
	@ResponseBody
	public void usersave(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println(1);
		User user=new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setJobName(request.getParameter("jobName"));
		user.setPhone(request.getParameter("phone"));
		service.usersave(user);
	}
	//删除用户
	@RequestMapping("/userdelete.do")
	@ResponseBody
	public void userdelete(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int userId=Integer.parseInt(request.getParameter("userId"));
		System.out.println(userId);
		service.userdelete(userId);
		
	}
	//返回总页数
	@RequestMapping("/usercount.do")
	@ResponseBody
	public int doFindObjects(){
		
		int num=service.usercount();
		return num;
		
	}
}
