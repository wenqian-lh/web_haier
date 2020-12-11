package com.web.haier.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.web.haier.bean.MemberInfo;
import com.web.haier.dao.DBHelper;
import com.web.haier.dao.IMemberInfoDao;

public class MemberInfoDaoImpl implements IMemberInfoDao {
	
	@Override
	public int total() {
		DBHelper db = new DBHelper();
		String sql = "select count(mid) from memberinfo";
		return db.total(sql);
	}
	
	@Override
	public int add(MemberInfo mf) {
		DBHelper db = new DBHelper();
		String sql = "insert into memberinfo values(0, ?, ?, ?, ?, md5(?), ?, ?, ?)";
		return db.update(sql, mf.getNickName(), mf.getRealName(), mf.getTel(), mf.getEmail(),
				mf.getPwd(), mf.getPhoto(), mf.getRegDate(), mf.getStatus());
	}

	@Override
	public int update(MemberInfo mf) {
		DBHelper db = new DBHelper();
		String sql = "update memberinfo set nickName=?, realName=?, tel=?, email=?, status=? where mid=?";
		return db.update(sql, mf.getNickName(), mf.getRealName(), mf.getTel(), mf.getEmail(),mf.getStatus(),mf.getMid());
	}
	
	@Override
	public int delete(MemberInfo mf) {
		DBHelper db = new DBHelper();
		String sql = "delete from memberinfo where mid=?";
		return db.update(sql, mf.getMid());
	}
	
	@Override
	public List<MemberInfo> findAll() {
		DBHelper db = new DBHelper();
		String sql = "select mid, photo, nickName, realName, tel, email, status from memberinfo order by mid";
		return db.finds(MemberInfo.class, sql);
	}
	
	@Override
	public List<MemberInfo> findByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select mid, photo, nickName, realName, tel, email, status from memberinfo order by mid limit ?, ?";
		return db.finds(MemberInfo.class, sql, (page - 1) * rows, rows);
	}

	@Override
	public List<MemberInfo> findByCondition(String way, String zway, int page, int rows) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select mid, photo, nickName, realName, tel, email, status from memberinfo where 1=1";
		switch (way) {
		case "0": sql += " and mid=?"; params.add(zway); break;
		case "1": sql += " and nickName like concat('%', ?, '%')"; params.add(zway); break;
		case "2": sql += " and realName like concat('%', ?, '%')"; params.add(zway); break;
		case "3": sql += " and tel=?"; params.add(zway); break;
		case "4": sql += " and email=?"; params.add(zway); break;
		case "5": sql += " and status=?"; params.add(zway); break;
		default:  break;
		}
		sql += " order by mid limit ?, ?";
		params.add((page - 1) * rows);
		params.add(rows);
		return db.finds(MemberInfo.class, sql, params);
	}

	@Override
	public int total(String way, String zway) {
		DBHelper db = new DBHelper();
		String sql = "select count(mid) from memberinfo where 1=1";
		List<Object> params = new ArrayList<Object>();
		switch (way) {
		case "0": sql += " and mid=?"; params.add(zway); break;
		case "1": sql += " and nickName like concat('%', ?, '%')"; params.add(zway); break;
		case "2": sql += " and realName like concat('%', ?, '%')"; params.add(zway); break;
		case "3": sql += " and tel=?"; params.add(zway); break;
		case "4": sql += " and email=?"; params.add(zway); break;
		case "5": sql += " and status=?"; params.add(zway); break;
		default:  break;
		}
		return db.total(sql, params);
	}

	@Override
	public MemberInfo findByMid(String mid) {
		DBHelper db = new DBHelper();
		String sql = "select mid, photo, nickName, realName, tel, email, status from memberinfo where mid=?";
		return db.find(MemberInfo.class, sql, mid);
	}

	@Override
	public int reg(MemberInfo mf) {
		DBHelper db = new DBHelper();
		String sql = "insert into memberinfo values(0, ?, ?, ?, ?, md5(?), null, now(), 0)";
		return db.update(sql, mf.getNickName(), mf.getRealName(), mf.getTel(), mf.getEmail(), mf.getPwd());
	}

	@Override
	public MemberInfo login(String email, String pwd) {
		DBHelper db = new DBHelper();
		String sql = "select mid, nickName, realName, tel, email, photo, regDate from memberinfo where status=0 and pwd=md5(?)"
				+ " and email=?";
		return db.find(MemberInfo.class, sql, pwd, email);
	}

	@Override
	public int save(MemberInfo mf) {
		DBHelper db = new DBHelper();
		String sql = "update memberinfo set nickName=?, realName=?, tel=?, email=?, photo=?, regDate=? where mid=?";
		return db.update(sql, mf.getNickName(), mf.getRealName(), mf.getTel(), mf.getEmail(),mf.getPhoto(),mf.getRegDate(),mf.getMid());
	}

	@Override
	public MemberInfo getPhoto(MemberInfo mf) {
		DBHelper db = new DBHelper();
		String sql = "select photo from memberinfo where mid=?";
		return db.find(MemberInfo.class, sql, mf.getMid());
	}
}
