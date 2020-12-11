package com.web.haier.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理请求参数
 * @author admin
 *
 */
public class RequestParamUtil {
	/**
	 * 将请求中的参数封装成对象返回
	 * @param <T>
	 * @param cls
	 * @param request
	 * @return
	 */
	// <T> 数据类型 形参化  返回 T	注入到哪个对象类里面  数据从哪里来
	public static <T> T getParams(Class<T> cls, HttpServletRequest request) {
		T t = null;
		try {
			// 获取给定的类中的所有setter方法
			Method[] methods = cls.getMethods();
			
			// 存储所有时setter方法，以方法名为键，对应方法对象为值
			Map<String, Method> setters = new HashMap<String, Method>();
			
			String methodName = null;
			for (Method method : methods) {
				methodName = method.getName();
				if (methodName.startsWith("set")) {	// 说明当前方法是一个setter方法
					setters.put(methodName, method);
				}
			}
			
			// 获取请求中的所有的参数的参数名
			Enumeration<String> names = request.getParameterNames();
			
			// 循环所有的参数名，找到这个参数注入时对应的setter方法，将这个参数注入到对应的对象的属性中
			String name = null;
			Method method = null;
			Class<?>[] types = null;
			Class<?> type = null;
			Object obj = null;
			String objStr = null;
			t = cls.newInstance();	// 实例化这个类的一个对象
			
			while (names.hasMoreElements()) {
				name = names.nextElement();
				obj = request.getParameter(name);	// 先判断参数是否为空
				if (obj == null) {
					continue;
				}
				
				objStr = String.valueOf(obj);
				if ("".equals(objStr)) {
					continue;
				}
				
				methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
				method = setters.getOrDefault(methodName, null);
				if (method == null) {	// 说明实体类中没有这个属性的注值方法
					continue;
				}
				
				// 因为方法调用跟数据类型有关，所以我们必须先获取这个方法的参数列表
				types = method.getParameterTypes();
				if (types == null || types.length <= 0) {	// 说明这个方法不带形参，那么我们也无法注值
					continue;
				}
				
				type = types[0];	// 因为我们提供setter方法一般只有一个形参
				
				// 反向激活这个方法注值
				if (Integer.TYPE == type || Integer.class == type) {
					method.invoke(t, Integer.parseInt(objStr));
				} else if (Float.TYPE == type || Float.class == type) {
					method.invoke(t, Float.parseFloat(objStr));
				} else if (Double.TYPE == type || Double.class == type) {
					method.invoke(t, Double.parseDouble(objStr));
				} else {
					method.invoke(t, objStr);
				}
				
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return t;
	}
}
