package cn.tedu.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 用于session验证拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response, 
			Object arg2) throws Exception {
		System.out.println("开始session验证");
		//获取session
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if(obj==null){
			System.out.println("没有登录,重定向到登录页面");
			//没有登录
			response.sendRedirect("toLogin.do");
			return false;
		}
		//登录过则允许访问
		System.out.println("登录过了允许访问");
		return true;
	}
	
}
