package cn.tedu.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * ����session��֤������
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
		System.out.println("��ʼsession��֤");
		//��ȡsession
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if(obj==null){
			System.out.println("û�е�¼,�ض��򵽵�¼ҳ��");
			//û�е�¼
			response.sendRedirect("toLogin.do");
			return false;
		}
		//��¼�����������
		System.out.println("��¼�����������");
		return true;
	}
	
}
