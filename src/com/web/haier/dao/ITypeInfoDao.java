package com.web.haier.dao;

import java.util.List;

import com.web.haier.bean.TypeInfo;

/**
 * 父分类 数据模型层 接口
 * @author admin
 *
 */
public interface ITypeInfoDao {
	/**
	 * 添加 父分类信息
	 * @param tf
	 * @return
	 */
	public int add(TypeInfo tf);
	
	/**
	 * 修改 父分类信息
	 * @param tf
	 * @return
	 */
	public int update(TypeInfo tf);
	
	/**
	 * 删除 父分类信息
	 * @param tf
	 * @return
	 */
	public int delete(TypeInfo tf);
	
	/**
	 * 查询所有 父分类信息
	 * @return
	 */
	public List<TypeInfo> findAll();
	
	/**
	 * 获取 总记录数
	 * @return
	 */
	public int total();
	
	/**
	 * 分页查询
	 * @param page 查询 第几页
	 * @param rows 每页显示 多少行
	 * @return
	 */
	public List<TypeInfo> findByPage(int page, int rows);
}
