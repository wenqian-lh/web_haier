/**
 * 
 */
package com.web.haier.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.web.haier.bean.AdminInfo;
import com.web.haier.dao.DBHelper;
import com.web.haier.dao.IAdminInfoDao;

/**
 * @author lh
 * @data 2020年11月21日
 * Email 2944862497@qq.com
 */
public class AdminInfoDaoImpl implements IAdminInfoDao{

	@Override
	public AdminInfo login(String adminId, String pwd) {
		DBHelper db = new DBHelper();
		String sql = "select adminId, aname, tel, photo, status from admininfo where adminId=? and pwd=md5(?) and 1=1";
		return db.find(AdminInfo.class, sql, adminId, pwd);
	}

	@Override
	public int total() {
		DBHelper db = new DBHelper();
		String sql = "select count(adminId) from admininfo where status!=1";
		return db.total(sql);
	}

	@Override
	public List<AdminInfo> findByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select adminId, aname, tel, pwd, status from admininfo where status!=1 order by adminId desc limit ?, ? ";
		return db.finds(AdminInfo.class, sql, (page - 1) * rows, rows);
	}

	@Override
	public int add(AdminInfo adminInfo) {
		DBHelper db = new DBHelper();
		String sql = "insert into admininfo values(0, ?, ?, ?, null, ?)";
		return db.update(sql, adminInfo.getAname(), adminInfo.getPwd(), adminInfo.getTel(), adminInfo.getStatus());
	}

	@Override
	public int update(AdminInfo adminInfo) {
		DBHelper db = new DBHelper();
		String sql = "update admininfo set aname=?, pwd=?, tel=?, status=? where adminId=?";
		return db.update(sql, adminInfo.getAname(), adminInfo.getPwd(), adminInfo.getTel(), adminInfo.getStatus(), adminInfo.getAdminId());
	}

	@Override
	public int delete(Integer adminId) {
		DBHelper db = new DBHelper();
		String sql = "delete from admininfo where adminId=?";
		return db.update(sql, adminId);
	}

	@Override
	public int totalByStatus(boolean flag) {
		DBHelper db = new DBHelper();
		String sql = "select count(adminId) from adminInfo where status=";
		if(flag) {
			sql += "0";
		} else {
			sql += "2";
		}
		return db.total(sql);
	}

	@Override
	public List<AdminInfo> findByCondition(String val, String text, int page, int rows) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select adminId, aname, tel, pwd, status from admininfo where 1=1";
		/*
		 * if(status == 0) { sql += " and status=?"; params.add(status); } else
		 * if(status == 2) { sql += " and status=?"; params.add(status); } else { sql +=
		 * " and status!=1"; }
		 */
		
		if("0".equals(val)) {
			sql += " and aname like concat('%', ?, '%')";
			params.add(text);
		} else {
			sql += " and tel like concat('%', ?, '%')";
			params.add(text);
		}
		sql += "order by adminId desc limit ?, ?";		
		params.add((page - 1) * rows);
		params.add(rows);
		return db.finds(AdminInfo.class, sql, params);
	}

}
