/**
 * 
 */
package com.web.haier.biz;

import java.util.List;

import com.web.haier.bean.SkuInfo;
import com.web.haier.dto.JsonObject;

/**
 * @author lh
 * @data 2020年11月29日
 * Email 2944862497@qq.com
 */
public interface ISkuInfoBiz {
	
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject findByPage(int page, int rows);
	
	/**
	 * 添加商品
	 * @param skuInfo
	 * @return
	 */
	public int add(SkuInfo skuInfo);
	
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
	 * 按条件查询商品信息
	 * @param tid
	 * @param pid
	 * @param sname
	 * @param status
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject findCondition(String tid, String pid, String sname, String status, int page, int rows);
	
	/**
	 * 根据商品编号获取商品所有属性
	 * @param gid
	 * @return
	 */
	public List<SkuInfo> getAttributes(String gid);

}
