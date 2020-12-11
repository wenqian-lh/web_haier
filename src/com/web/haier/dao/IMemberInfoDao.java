package com.web.haier.dao;

import java.util.List;

import com.web.haier.bean.MemberInfo;

/**
 * 会员信息 数据模型层 接口
 * @author admin
 *
 */
public interface IMemberInfoDao {
	public int total();
	public MemberInfo findByMid(String mid);
	/**
	 * 注册 会员
	 * @param mf
	 * @return
	 */
	public int add(MemberInfo mf);
	
	/**
	 * 删除 会员
	 * @param mf
	 * @return
	 */
	public int delete(MemberInfo mf);
	
	/**
	 * 修改 用户信息
	 * @param mf
	 * @return
	 */
	public int update(MemberInfo mf);
	
	/**
	 * 查询所有 会员信息
	 * @return
	 */
	public List<MemberInfo> findAll();
	
	/**
	 * 获取 总记录数
	 * @return
	 */
	public int total(String way, String zway);
	
	/**
	 * 分页查询
	 * @param page 查询 第几页
	 * @param rows 每页显示 多少行
	 * @return
	 */
	public List<MemberInfo> findByPage(int page, int rows);
	
	/**
	 * 条件查询
	 * @param way
	 * @param zway
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<MemberInfo> findByCondition(String way, String zway, int page, int rows);
	
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
	
	/**
	 * 修改 个人信息
	 * @param mf
	 * @return
	 */
	public int save(MemberInfo mf);
	
	/**
	 * 得到指定用户的头像
	 * @param photo
	 * @return
	 */
	public MemberInfo getPhoto(MemberInfo mf);
}
