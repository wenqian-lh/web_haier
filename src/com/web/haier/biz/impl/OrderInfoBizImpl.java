/**
 * 
 */
package com.web.haier.biz.impl;

import java.util.List;

import com.web.haier.bean.OrderInfo;
import com.web.haier.biz.IOrderInfoBiz;
import com.web.haier.dao.IOrderInfoDao;
import com.web.haier.dao.impl.OrderInfoDaoImpl;

/**
 * @author lh
 * @data 2020年12月13日
 * Email 2944862497@qq.com
 */
public class OrderInfoBizImpl implements IOrderInfoBiz{

	@Override
	public int add(OrderInfo orderInfo) {
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();
		return orderInfoDao.add(orderInfo);
	}

	@Override
	public int addOrderList(String orderlist, String oid) {
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();
		return orderInfoDao.addOrderList(orderlist, oid);
	}

	@Override
	public int updateOrderStatus(String oid, String payDate) {
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();
		return orderInfoDao.updateOrderStatus(oid, payDate);
	}
	
	@Override
	public List<OrderInfo> finds() {
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();
		return orderInfoDao.finds();
	}

}
