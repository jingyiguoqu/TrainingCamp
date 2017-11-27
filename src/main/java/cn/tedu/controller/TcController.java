package cn.tedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tedu.dao.UserDAO;
import cn.tedu.entity.TrainingCamp;
import cn.tedu.service.AppException;
import cn.tedu.service.LoginService;
import cn.tedu.util.JsonResult;

@Controller
public class TcController {
	@Resource(name="loginService")
	private LoginService service;
	

	//添加训练营
		@RequestMapping("/addTc.do")
		public String addTc(HttpServletRequest request){
			TrainingCamp tc=new TrainingCamp();
			tc.setClassName(request.getParameter("className"));
			tc.setType(request.getParameter("type"));
			tc.setUsername(request.getParameter("username"));
			tc.setSeatType(request.getParameter("seatType"));
			service.addTc(tc);
			return "redirect:toIndex.do";
		}
		//删除训练营
		@RequestMapping("/delete.do")
		public String delete(HttpServletRequest request){
			int tcId = Integer.parseInt(request.getParameter("tcId"));
			service.checkDelete(tcId);
			return "redirect:toIndex.do";
		}
		//读取tcid返回修改参数
		@RequestMapping("/loadtc.do")
		@ResponseBody
		public JsonResult load(HttpServletRequest request ,HttpServletResponse response) throws IOException{
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			int tcId = Integer.parseInt(request.getParameter("tcId"));
			TrainingCamp tc=service.loadTc(tcId);
			/*PrintWriter out = 
					response.getWriter();
			ObjectMapper om=new ObjectMapper();
			String jsonStr=om.writeValueAsString(tc);
			out.println(jsonStr);*/
			return new JsonResult(tc);
		}
		@RequestMapping("/update.do")
		public String update(HttpServletRequest request,TrainingCamp tc){
//			System.out.println("读取参数值");
//			int tcId = Integer.parseInt(request.getParameter("tcId"));
//			System.out.println(tcId);
//			String className = request.getParameter("className");
//			System.out.println(className);
//			String username = request.getParameter("username");
//			System.out.println(username);
//			String type = request.getParameter("type");
//			System.out.println(type);
//			String seatType = request.getParameter("seatType");
//			System.out.println(seatType);
			
			
			System.out.println(tc);
//			TrainingCamp tc = new TrainingCamp();
//			tc.setClassName(className);
//			System.out.println(tc);
//			tc.setSeatType(seatType);
//			tc.setType(type);
//			tc.setTcId(tcId);
//			tc.setUsername(username);
//			System.out.println(tc);
			service.update(tc);
			return "redirect:toIndex.do";
		}
		//历史训练营
		@RequestMapping("/histclist.do")
		@ResponseBody
		public JsonResult histclist(){
			
			return new JsonResult(service.hisTclist());
		}
		
		
		
		@ExceptionHandler
		
		public String exceptionHandler(Exception e,HttpServletRequest request){
			if(e instanceof AppException ){
				request.setAttribute("del_failed", e.getMessage());
				
				return "redirect:toIndex.do";
			}
			return "error";
		}
}
