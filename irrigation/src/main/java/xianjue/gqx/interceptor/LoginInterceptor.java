package xianjue.gqx.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{
	private final String LOGIN_URL = "/greenhouse/login/loginPage";
	private String[] accessURI = {"/login/loginPage"};
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		HttpSession session = request.getSession(true);  
        // 从session 里面获取用户名的信息  
		Object obj = session.getAttribute("admin");  
        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆  
		String uri = request.getRequestURI();
        if (obj == null && !isAccessURI(uri)) {  
        	response.sendRedirect(LOGIN_URL);
        	return false;
        }  
        return true; 
	}

	private boolean isAccessURI(String uri){
		for(String s:accessURI){
			if(uri.contains(s)){
				return true;
			}
		}
		return false;
	}
}
