package com.web.haier.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.web.haier.util.FileUploadUtil;
import com.web.haier.util.StringUtil;

/**
 * 程序一启动,我们就监听创建文件上传的目录
 * @author admin
 *
 */
@WebListener
public class CreateUploadPathListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String path = sce.getServletContext().getInitParameter("uploadPath");
		if (StringUtil.checkNull(path)) {
			path = "images_haier";
		}
		
		String basePath = sce.getServletContext().getRealPath("/");	// 获取 Tomcat在服务器中的绝对路径
			// 重启服务器可见
		path = "../" + path;			// D:\study\Tomcat\apache-tomcat-9.0.34\webapps\wowo\
			//	../wowo_pics
		File f1 = new File(basePath, path);
		
		if (!f1.exists()) {
			f1.mkdirs();
		}
		
		FileUploadUtil.uploadPath = path;
				//	../wowo_pics
	}
}
