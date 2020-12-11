package com.web.haier.biz;

import java.util.List;

import com.web.haier.bean.PTypeInfo;
import com.web.haier.dto.JsonObject;

/**
 * 
 * @author admin
 *
 */
public interface IPTypeInfoBiz {
	/**
	 * 添加 子分类信息
	 * @param pt
	 * @return
	 */
	public int add(PTypeInfo pt);
	
	/**
	 * 修改 子分类信息
	 * @param pt
	 * @return
	 */
	public int update(PTypeInfo pt);
	
	/**
	 * 查询所有 子分类信息
	 * @return
	 */
	public List<PTypeInfo> findAll();
	
	/**
	 * 分页查询
	 * @param page 查询 第几页
	 * @param rows 每页显示 多少行
	 * @return
	 */
	public JsonObject findByPage(int page, int rows);
	
	/**
	 * 条件查询
	 * @param tid
	 * @param pname
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject findCondition(String tid, String pname, int page, int rows);

	public int delete(PTypeInfo pt);
	
	/**
	 * 按子类型编号查询类型信息
	 * @param pid
	 * @return
	 */
	public PTypeInfo findByPid(String pid);
}
