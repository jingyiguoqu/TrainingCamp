package cn.tedu.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.entity.StudentInfo;
import cn.tedu.entity.TrainingCamp;
import cn.tedu.service.LoginService;
import cn.tedu.util.JsonResult;
@Controller
public class StuController {
	@Resource(name="loginService")
	private LoginService service;
	
	//ï¿½ï¿½È¡studentIdï¿½ï¿½ï¿½ï¿½Ñ§ï¿½ï¿½ï¿½ï¿½Ï¢
	@RequestMapping("/seatstu.do")
	@ResponseBody
	public JsonResult load(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		return new JsonResult(service.seatstulist(studentId));
	}
	
	@RequestMapping("/removestu.do")
	@ResponseBody
	public boolean removestu(int tcId,int studentId){
		service.removestu(tcId, studentId);
		return true;
	}
	
	@RequestMapping("/unseatstu.do")
	@ResponseBody
	public List<StudentInfo> nseatstu(){
		return service.unseatstu();
	}
	@RequestMapping("/bookingstu.do")
	@ResponseBody
	public boolean bookingstu(int tcId,int studentId,int serialNum){
		service.bookingstu(tcId, studentId, serialNum);
		return true;
	}
	@RequestMapping("/findStuList.do")
	@ResponseBody
	public JsonResult findStuList(String name,Integer pageCurrent){
		Map<String,Object> map = service.findPageStudents(name, pageCurrent);
		System.out.println(map);
		return new JsonResult(map);
	}
	@RequestMapping("/insertStu.do")
	@ResponseBody
	public JsonResult insertStu(HttpServletRequest request){
		boolean basic = false;
		String name = request.getParameter("name");
		String major = request.getParameter("major");
		String learnPro = request.getParameter("learnPro");
		String degree = request.getParameter("degree");
		String type = request.getParameter("type");
		
		String username = request.getParameter("username");
		int userId =service.findByUsername(username).getUserId();
		System.out.println(userId);
		String flag= request.getParameter("basic");
		if("ÊÇ".equals(flag)){
			basic=true;
		}
		if("·ñ".equals(flag)){
			basic=false;
		}
		//int studyTime = Integer.parseInt(request.getParameter("studyTime"));
		String preSituation = request.getParameter("preSituation");
		String source = request.getParameter("source");
		StudentInfo stu = new StudentInfo(userId, name, type, source, degree, major, basic, preSituation, learnPro);
		System.out.println(stu);
		service.insertStu(stu);
		return new JsonResult();
	}
	@RequestMapping("/studentupdate.do")
	@ResponseBody
	public JsonResult updateStu(HttpServletRequest request){
		boolean basic = false;
		String name = request.getParameter("name");
		String major = request.getParameter("major");
		String learnPro = request.getParameter("learnPro");
		String degree = request.getParameter("degree");
		String type = request.getParameter("type");
		String username=request.getParameter("username");
		int userId = service.findByUsername(username).getUserId();
		System.out.println(userId);
		String flag= request.getParameter("basic");
		if("ÊÇ".equals(flag)){
			basic=true;
		}
		if("·ñ".equals(flag)){
			basic=false;
		}
		
		//int studyTime = Integer.parseInt(request.getParameter("studyTime"));
		String preSituation = request.getParameter("preSituation");
		String source = request.getParameter("source");
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		StudentInfo stu= new StudentInfo();
		stu.setUserId(userId);
		stu.setName(name);
		stu.setMajor(major);
		stu.setLearnPro(learnPro);
		stu.setDegree(degree);
		stu.setType(type);
		stu.setBasic(basic);
		stu.setPreSituation(preSituation);
		stu.setSource(source);
		stu.setStudentId(studentId);
		
		System.out.println(stu);
		service.updateStu(stu);
		return new JsonResult();
		
	}
	@RequestMapping("/studelete.do")
	@ResponseBody
	public JsonResult delStu(int studentId){
		service.delStu(studentId);
		return new JsonResult();
	}
	@RequestMapping("/listUserByJobId.do")
	@ResponseBody
	public JsonResult listUserByJobId(){
		return new JsonResult(service.findUserByJobId());
	}
}
