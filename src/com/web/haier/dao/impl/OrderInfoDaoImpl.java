/**
 * 
 */
package com.web.haier.dao.impl;

import java.util.List;

import com.web.haier.bean.OrderInfo;
import com.web.haier.dao.DBHelper;
import com.web.haier.dao.IOrderInfoDao;

/**
 * @author lh
 * @data 2020年12月13日
 * Email 2944862497@qq.com
 */
public class OrderInfoDaoImpl implements IOrderInfoDao{

	@Override
	public int add(OrderInfo orderInfo) {
		DBHelper db = new DBHelper();
		String sql = "insert into orderinfo values(?, ?, ?, ?, ?, now(), now())";
		return db.update(sql, orderInfo.getOid(), orderInfo.getMid(), orderInfo.getTotal(), orderInfo.getAddr(), orderInfo.getStatus());
	}

	@Override
	public int addOrderList(String orderlist, String oid) {
		DBHelper db = new DBHelper();
		String sid;
		int price;
		int num;
		int cost;
		int sum = 0;
		String sql = "insert into orderlist values(0, ?, ?, ?, ?, ?, ?, '', 0)";
		String[] list = orderlist.split("-");
		for(int i = 0, len = list.length; i < len; i ++) {
			String[] detail = list[i].split("_");
			sid = detail[0];
			num = Integer.parseInt(detail[1]);
			price = Integer.parseInt(detail[2]);
			cost = Integer.parseInt(detail[3]);
			int result = db.update(sql, oid, sid, num, price, num * price, num * (price - cost));
			if (result < 1) { // 如果有一条添加失败，则返回添加失败
				return result;
			}
			sum += result; // 累加添加成功的记录数
		}
		return sum;
	}

	@Override
	public int updateOrderStatus(String oid, String payDate) {
		DBHelper db = new DBHelper();
		String sql = "update orderinfo set status = 1, payDate=?  where oid=? and 1=1";
		return db.update(sql, payDate, oid);
	}
	
	@Override
	public List<OrderInfo> finds() {
		DBHelper db = new DBHelper();
		String sql = "select oid, mid, total, addr, status, orderDate, payDate from orderinfo";
		return db.finds(OrderInfo.class, sql);
	}

}
