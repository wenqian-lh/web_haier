package com.web.haier.biz;

import java.util.List;

import com.web.haier.bean.MemberInfo;
import com.web.haier.dto.JsonObject;

public interface IMemberInfoBiz {
	public MemberInfo findByMid(String mid);
	
	/**
	 * 注册 会员
	 * @param mf
	 * @return
	 */
	public int add(MemberInfo mf);
	
	/**
	 * 修改 用户信息
	 * @param mf
	 * @return
	 */
	public int update(MemberInfo mf);
	
	/**
	 * 删除 会员
	 * @param mf
	 * @return
	 */
	public int delete(MemberInfo mf);
	
	/**
	 * 查询所有 会员信息
	 * @return
	 */
	public List<MemberInfo> findAll();
	
	/**
	 * 分页查询
	 * @param page 查询 第几页
	 * @param rows 每页显示 多少行
	 * @return
	 */
	public JsonObject findByPage(int page, int rows);
	
	/**
	 * 条件查询
	 * @param way
	 * @param zway
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject findByCondition(String way, String zway, int page, int rows);
	
	/**
	 * 注册
	 * @param mf
	 * @return
	 */
	public int reg(MemberInfo mf);
	
	/**
	 * 登录
	 * @param email
	 * @param pwd
	 * @return
	 */
	public MemberInfo login(String email, String pwd);

	public int save(MemberInfo mf);
	
	public MemberInfo getPhoto(MemberInfo mf);
}
