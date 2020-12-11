package com.web.haier.biz;

import java.util.List;

import com.web.haier.bean.TypeInfo;
import com.web.haier.dto.JsonObject;

/**
 * 
 * @author admin
 *
 */
public interface ITypeInfoBiz {
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
	 * @param mf
	 * @return
	 */
	public int delete(TypeInfo tf);
	
	/**
	 * 查询所有 父分类信息
	 * @return
	 */
	public List<TypeInfo> findAll();
	
	/**
	 * 分页查询
	 * @param page 查询 第几页
	 * @param rows 每页显示 多少行
	 * @return
	 */
	public JsonObject findByPage(int page, int rows);
}
