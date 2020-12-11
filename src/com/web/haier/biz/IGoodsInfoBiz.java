/**
 * 
 */
package com.web.haier.biz;

import java.util.List;

import com.web.haier.bean.GoodsInfo;
import com.web.haier.bean.GoodsNameAndGid;
import com.web.haier.dto.JsonObject;

/**
 * @author lh
 * @data 2020年11月27日
 * Email 2944862497@qq.com
 */
public interface IGoodsInfoBiz {
	
	/**
	 * 分页查询商品信息
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject findByPage(int page, int rows);
	
	/**
	 * 分页查询可用商品信息-针对前台
	 * @return
	 */
	public List<GoodsInfo> finds(int page, int rows);
	
	/**
	 * 查询商品总数
	 * @flag flag为true时查询所有商品总数  为false时查询在架商品总数
	 * @return
	 */
	public int total();
	
	/**
	 * 查询符合条件的商品总数
	 * @param gid
	 * @param gname
	 * @param status
	 * @return
	 */
	public int total(String tid, String pid, String sname, String status);
	
	/**
	 * 添加商品信息
	 * @param goodsInfo
	 * @return
	 */
	public int add(GoodsInfo goodsInfo);
	
	/**
	 * 修改商品信息
	 * @param goodsInfo
	 * @return
	 */
	public int update(GoodsInfo goodsInfo);
	
	/**
	 * 按商品编号查询商品信息
	 * @param gid
	 * @return
	 */
	public GoodsInfo findByGid(String gid);	
	
	/**
	 * 按条件分页组合查询
	 * @param gid
	 * @param gname
	 * @param status
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject findByCondition(String tid, String pid, String sname, String status, int page, int rows);
	
	/**
	 * 按条件获取商品编号和商品名称
	 * @param tid
	 * @param pid
	 * @param sname
	 * @param status
	 * @return
	 */
	public List<GoodsNameAndGid> getGoodsName(String tid, String pid, String status);
	
	/**
	 * 按商品名称分页查询商品信息
	 * @param name
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<GoodsInfo> findByName(String name, int page, int rows);
	
	/**
	 * 查询符合条件的商品总数
	 * @param tid
	 * @param pid
	 * @param price
	 * @return
	 */
	public int total(String tid, String pid, String price);
	
	/**
	 * 按条件分页组合查询
	 * @param tid
	 * @param pid
	 * @param price
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<GoodsInfo> findByConditioners(String tid, String pid, String price, String desc, int page, int rows);
}
