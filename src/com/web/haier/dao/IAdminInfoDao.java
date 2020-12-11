/**
 * 
 */
package com.web.haier.dao;

import java.util.List;

import com.web.haier.bean.AdminInfo;

/**
 * 管理员数据层接口
 * @author lh
 * @data 2020年11月21日
 * Email 2944862497@qq.com
 */
public interface IAdminInfoDao {
	
	/**
	 * 管理员登录
	 * @param adminId  账号
	 * @param pwd  密码
	 * @return
	 */
	public AdminInfo login(String adminId, String pwd);
	
	/**
	 * 查询管理员总数
	 * @return
	 */
	public int total();
	
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<AdminInfo> findByPage(int page, int rows);
	
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
	 * 按账号状态查询账号总数
	 * @param flag
	 * @return
	 */
	public int totalByStatus(boolean flag);
	
	/**
	 * 指定字段模糊查询管理员信息
	 * @param val
	 * @param text
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<AdminInfo> findByCondition(String val, String text, int page, int rows);
}
