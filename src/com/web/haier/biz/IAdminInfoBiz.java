/**
 * 
 */
package com.web.haier.biz;

import com.web.haier.bean.AdminInfo;
import com.web.haier.dto.JsonObject;

/**
 * 管理员业务层接口
 * @author lh
 * @data 2020年11月21日
 * Email 2944862497@qq.com
 */
public interface IAdminInfoBiz {

	/**
	 * 管理员登录
	 * @param adminId  账号
	 * @param pwd  密码
	 * @return
	 */
	public AdminInfo login(String adminId, String pwd);
	
	/**
	 * 分页查询管理员信息
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject findByPage(int page, int rows);
	
	/**
	 * 添加管理员
	 * @param adminInfo
	 * @return
	 */
	public int add(AdminInfo adminInfo);
	
	/**
	 * 修改管理员
	 * @param adminInfo
	 * @return
	 */
	public int update(AdminInfo adminInfo);
	
	/**
	 * 删除管理员
	 * @param mid
	 * @return
	 */
	public int delete(Integer adminId);
	
	/**
	 * 指定字段模糊查询管理员信息
	 * @param val
	 * @param text
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject findByCondition(String status, String val, String text, int page, int rows);
}
