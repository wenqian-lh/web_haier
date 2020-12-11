package com.web.haier.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.web.haier.bean.PTypeInfo;
import com.web.haier.dao.DBHelper;
import com.web.haier.dao.IPTypeInfoDao;
import com.web.haier.util.StringUtil;

/**
 * 子分类 数据模型层 实现
 * @author admin
 *
 */
public class PTypeInfoDaoImpl implements IPTypeInfoDao {

	@Override
	public int add(PTypeInfo pt) {
		DBHelper db = new DBHelper();
		String sql = "insert into ptypeinfo values(0, ?, ?)";
		return db.update(sql, pt.getPname(), pt.getTid());
	}

	@Override
	public int update(PTypeInfo pt) {
		DBHelper db = new DBHelper();
		String sql = "update ptypeinfo set pname=? where pid=?";
		return db.update(sql, pt.getPname(), pt.getPid());
	}

	@Override
	public List<PTypeInfo> findAll() {
		DBHelper db = new DBHelper();
		String sql = "select pid, pname from ptypeinfo order by pid";
		return db.finds(PTypeInfo.class, sql);
	}

	@Override
	public int total() {
		DBHelper db = new DBHelper();
		String sql = "select count(pid) from ptypeinfo";
		return db.total(sql);
	}

	@Override
	public List<PTypeInfo> findByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select tid, pid, pname from ptypeinfo order by pid limit ?, ?";	// 注意: 从哪一条记录开始查    查多少条数据
		return db.finds(PTypeInfo.class, sql, (page - 1) * rows, rows);
	}

	@Override
	public List<PTypeInfo> findCondition(String tid, String pname, int page, int rows) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select tid, pid, pname from ptypeinfo where 1=1";
		if (!StringUtil.checkNull(tid)) {
			sql += " and tid=?";
			params.add(tid);
		}
		if (!StringUtil.checkNull(pname)) {
			sql += " and pname like concat('%', ?, '%')";
			params.add(pname);
		}
		params.add((page - 1) * rows);
		params.add(rows);
		sql += " order by pid desc limit ?, ?";
		return db.finds(PTypeInfo.class, sql, params);
	}

	@Override
	public int total(String tid, String pname) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select count(pid) from ptypeinfo where 1=1";
		if (!StringUtil.checkNull(tid)) {
			sql += " and tid=?";
			params.add(tid);
		}
		if (!StringUtil.checkNull(pname)) {
			sql += " and pname like concat('%', ?, '%')";
			params.add(pname);
		}
		return db.total(sql, params);
	}

	@Override
	public int delete(PTypeInfo pt) {
		DBHelper db = new DBHelper();
		String sql = "delete from ptypeinfo where pid=?";
		return db.update(sql, pt.getPid());
	}

	@Override
	public PTypeInfo findByPid(String pid) {
		DBHelper db = new DBHelper();
		String sql = "select pid, pname from ptypeinfo where pid=? and 1=1";
		return db.find(PTypeInfo.class, sql, pid);
	}
	
}
