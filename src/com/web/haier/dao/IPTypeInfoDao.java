package com.web.haier.dao;

import java.util.List;

import com.web.haier.bean.PTypeInfo;

/**
 * 子分类 数据模型层 接口
 * @author admin
 *
 */
public interface IPTypeInfoDao {
	public int total();
	
	/**
	 * 添加 子分类信息
	 * @param tf
	 * @return
	 */
	public int add(PTypeInfo tf);
	
	/**
	 * 修改 子分类信息
	 * @param tf
	 * @return
	 */
	public int update(PTypeInfo tf);
	
	/**
	 * 查询所有 子分类信息
	 * @return
	 */
	public List<PTypeInfo> findAll();
	
	/**
	 * 获取 总记录数
	 * @return
	 */
	public int total(String tid, String pname);
	
	/**
	 * 分页查询
	 * @param page 查询 第几页
	 * @param rows 每页显示 多少行
	 * @return
	 */
	public List<PTypeInfo> findByPage(int page, int rows);
	
	/**
	 * 条件查询
	 * @param tid
	 * @param pname
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<PTypeInfo> findCondition(String tid, String pname, int page, int rows);

	public int delete(PTypeInfo pt);
	
	/**
	 * 按子类型编号查询类型信息
	 * @param pid
	 * @return
	 */
	public PTypeInfo findByPid(String pid);
}
