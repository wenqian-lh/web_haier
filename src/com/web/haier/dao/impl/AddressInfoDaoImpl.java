/**
 * 
 */
package com.web.haier.dao.impl;

import java.util.List;

import com.web.haier.bean.AddressInfo;
import com.web.haier.dao.DBHelper;
import com.web.haier.dao.IAddressInfoDao;

/**
 * 收货地址管理数据层实现
 * @author lh
 * @data 2020年12月6日
 * Email 2944862497@qq.com
 */
public class AddressInfoDaoImpl implements IAddressInfoDao {

	@Override
	public int add(AddressInfo addressInfo) {
		DBHelper db = new DBHelper();
		String sql = "insert into addressinfo values(0, ?, ?, ?, ?, ?, ?, ?, 0)";
		return db.update(sql, addressInfo.getMid(), addressInfo.getProvince(), addressInfo.getCity(), addressInfo.getArea(),
				addressInfo.getAddr(), addressInfo.getTel(), addressInfo.getName());
	}

	@Override
	public List<AddressInfo> findAll(String mid) {
		DBHelper db = new DBHelper();
		String sql = "select addrId, province, city, area, addr, tel, name, status from addressinfo where mid=?";
		return db.finds(AddressInfo.class, sql, mid);
	}

	@Override
	public int update(AddressInfo addressInfo) {
		DBHelper db = new DBHelper();
		String sql = "update addressinfo set province=?, city=?, area=?, addr=?, tel=?,  name=? where addrId=?";
		return db.update(sql, addressInfo.getProvince(), addressInfo.getCity(), addressInfo.getArea(),
				addressInfo.getAddr(), addressInfo.getTel(), addressInfo.getName(), addressInfo.getAddrId());
	}

	@Override
	public int delete(String addrId) {
		DBHelper db = new DBHelper();
		String sql = "delete from addressinfo where addrId=? and 1=1";
		return db.update(sql, addrId);
	}

	@Override
	public int setDefault(String onaddrId, String addrId) {
		DBHelper db = new DBHelper();
		if (onaddrId == "" || onaddrId == null) {
			String sql = "update addressinfo set status=1 where addrId=?";
			return db.update(sql, addrId);
		}
		String sql2 = "update addressinfo set status=0 where addrId=?";
		int a = db.update(sql2, onaddrId);
		if (a > 0) {
			String sql = "update addressinfo set status=1 where addrId=?";
			return db.update(sql, addrId);
		}
		return 0;
	}
}
