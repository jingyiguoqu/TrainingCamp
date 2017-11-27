package cn.tedu.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.entity.SeatInfo;
import cn.tedu.entity.TrainingCamp;
import cn.tedu.service.LoginService;
import cn.tedu.util.JsonResult;

@Controller
public class ClassController {
	@Resource(name="loginService")
	private LoginService service;
		//读取tcid返回教室参数
		@RequestMapping("/loadtcseat.do")
		@ResponseBody
		public JsonResult load(HttpServletRequest request ,HttpServletResponse response) throws IOException{
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			int tcId = Integer.parseInt(request.getParameter("tcId"));
			TrainingCamp tc=service.loadTc(tcId);
			return new JsonResult(tc);
		}
		//读取tcid返回学生列表
		@RequestMapping("/seatlist.do")
		@ResponseBody
		public JsonResult seatlist(HttpServletRequest request ,HttpServletResponse response) throws IOException{
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			int tcId = Integer.parseInt(request.getParameter("tcId"));
			List<SeatInfo> seatlist=service.seatlist(tcId);
			return new JsonResult(seatlist);
		}
}
