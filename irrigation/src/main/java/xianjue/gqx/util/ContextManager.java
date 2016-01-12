package xianjue.gqx.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ContextManager {
	
	private static WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
    private static ServletContext servletContext = webApplicationContext.getServletContext();
	private static ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	public static WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}
	public static void setWebApplicationContext(WebApplicationContext webApplicationContext) {
		ContextManager.webApplicationContext = webApplicationContext;
	}
	public static ServletContext getServletContext() {
		return servletContext;
	}
	public static void setServletContext(ServletContext servletContext) {
		ContextManager.servletContext = servletContext;
	}
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	public static void setApplicationContext(ApplicationContext applicationContext) {
		ContextManager.applicationContext = applicationContext;
	}

	
}
