/**
 * 
 */
package com.web.haier.dao;

import java.util.List;

import com.web.haier.bean.SkuInfo;


/**
 * 库存管理数据层
 * @author lh
 * @data 2020年11月29日
 * Email 2944862497@qq.com
 */
public interface ISkuInfoDao {

	/**
	 * 添加商品
	 * @param skuInfo
	 * @return
	 */
	public int add(SkuInfo skuInfo);
	
	/**
	 * 库存信息分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<SkuInfo> findByPage(int page, int rows);
	
	/**
	 * 查询库存总数
	 * @return
	 */
	public int total();
	
	/**
	 * 按sku编号查询商品信息
	 * @param sid
	 * @return
	 */
	public SkuInfo findBySid(String sid);
	
	/**
	 * 修改商品信息
	 * @param skuInfo
	 * @return
	 */
	public int update(SkuInfo skuInfo);
	
	/**
	 * 按条件查询符合条件的记录数
	 * @param tid
	 * @param pid
	 * @param sname
	 * @param status
	 * @return
	 */
	public int total(String tid, String pid, String sname, String status);
	
	/**
	 * 按条件查询商品信息
	 * @param tid
	 * @param pid
	 * @param sname
	 * @param status
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<SkuInfo> findCondition(String tid, String pid, String sname, String status, int page, int rows);
	
	/**
	 * 根据商品编号获取商品所有属性
	 * @param gid
	 * @return
	 */
	public List<SkuInfo> getAttributes(String gid);
	
}
