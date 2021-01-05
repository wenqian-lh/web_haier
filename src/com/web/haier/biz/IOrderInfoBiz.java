/**
 * 
 */
package com.web.haier.biz;

import java.util.List;

import com.web.haier.bean.OrderInfo;

/**
 * @author lh
 * @data 2020年12月13日
 * Email 2944862497@qq.com
 */
public interface IOrderInfoBiz {
	
	/**
	 * 添加一条订单信息
	 * @param orderInfo
	 * @return
	 */
	public int add(OrderInfo orderInfo);
	
	/**
	 * 添加订单详细信息表
	 * @param orderlist
	 * @param oid
	 * @return
	 */
	public int addOrderList(String orderlist, String oid);
	
	/**
	 * 修改订单支付状态
	 * @param oid
	 * @param payDate
	 * @return
	 */
	public int updateOrderStatus(String oid, String payDate);
	
	/**
	 * 分页查询所有
	 * @return
	 */
	public List<OrderInfo> finds();

}
