/**
 * 
 */
package com.web.haier.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.web.haier.bean.GoodsInfo;
import com.web.haier.bean.GoodsNameAndGid;
import com.web.haier.dao.DBHelper;
import com.web.haier.dao.IGoodsInfoDao;
import com.web.haier.util.StringUtil;

/**
 * @author lh
 * @data 2020年11月27日
 * Email 2944862497@qq.com
 */
public class GoodsInfoDaoImpl implements IGoodsInfoDao{

	@Override
	public List<GoodsInfo> findByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select gid, sname, tid, pid, price, status from goodsinfo order by gid desc limit ?, ?";
		return db.finds(GoodsInfo.class, sql, (page - 1) * rows, rows);
	}

	@Override
	public List<GoodsInfo> finds(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select gid, sname, g.pid, price, pics, intro from goodsinfo g, ptypeinfo p"
				+ " where g.pid = p.pid and g.status!=0 order by gid desc limit ?, ?";
		return db.finds(GoodsInfo.class, sql, (page - 1) * rows, rows);
	}

	@Override
	public int add(GoodsInfo goodsInfo) {
		DBHelper db = new DBHelper();
		String sql = "insert into goodsinfo values(0, ?, ?, ?, ?, ?, ?, ?)";
		return db.update(sql, goodsInfo.getSname(), goodsInfo.getTid(), goodsInfo.getPid(), 
				goodsInfo.getPrice(), goodsInfo.getPics(), goodsInfo.getIntro(), goodsInfo.getStatus());
	}

	@Override
	public int update(GoodsInfo goodsInfo) {
		DBHelper db = new DBHelper();
		// 图片和简介都没有修改的情况
		if(StringUtil.checkNull(goodsInfo.getPics()) && StringUtil.checkNull(goodsInfo.getIntro())) {
			String sql = "update goodsinfo set sname=?, tid=?, pid=?, price=?, status=? where gid=?";
			return db.update(sql, goodsInfo.getSname(), goodsInfo.getTid(), goodsInfo.getPid(), goodsInfo.getPrice(),
								 goodsInfo.getStatus(), goodsInfo.getGid());
		}	
		// 简介没有修改的情况
		if(StringUtil.checkNull(goodsInfo.getIntro())) {
			String sql = "update goodsinfo set sname=?, tid=?, pid=?, price=?, pics=?, status=? where gid=?";
			return db.update(sql, goodsInfo.getSname(), goodsInfo.getTid(), goodsInfo.getPid(), goodsInfo.getPrice(),
								 goodsInfo.getPics(), goodsInfo.getStatus(), goodsInfo.getGid());
		}
		
		// 图片没有修改的情况
		if(StringUtil.checkNull(goodsInfo.getPics())) {
			String sql = "update goodsinfo set sname=?, tid=?, pid=?, price=?, intro=?, status=? where gid=?";
			return db.update(sql, goodsInfo.getSname(), goodsInfo.getTid(), goodsInfo.getPid(), goodsInfo.getPrice(),
								 goodsInfo.getIntro(), goodsInfo.getStatus(), goodsInfo.getGid());
		}
		// 简介和图片都修改的情况
		String sql = "update goodsinfo set sname=?, tid=?, pid=?, price=?, pics=?, intro=?, status=? where gid=?";
		return db.update(sql, goodsInfo.getSname(), goodsInfo.getTid(), goodsInfo.getPid(), goodsInfo.getPrice(),
							 goodsInfo.getPics(),goodsInfo.getIntro(), goodsInfo.getStatus(), goodsInfo.getGid());
	}

	@Override
	public GoodsInfo findByGid(String gid) {
		DBHelper db = new DBHelper();
		String sql = "select gid, sname, tid, pid, price, pics, intro, status from goodsinfo where gid=? and 1=1";
		return db.find(GoodsInfo.class, sql, gid);
	}

	@Override
	public int total(boolean flag) {
		DBHelper db = new DBHelper();
		if (flag) {
			String sql = "select count(gid) from goodsinfo";
			return db.total(sql);
		}
		String sql = "select count(gid) from goodsinfo where status!=0";
		return db.total(sql);
	}
	

	@Override
	public int total(String tid, String pid, String sname, String status) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select count(gid) from goodsinfo where 1=1";
		if(!StringUtil.checkNull(tid)) {
			sql += " and tid = ?";
			params.add(tid);
		}
		if(!StringUtil.checkNull(pid)) {
			sql += " and pid = ?";
			params.add(pid);
		}
		if(!StringUtil.checkNull(sname)) {
			sql += " and sname like concat('%', ?, '%')";
			params.add(sname);
		}
		if(!StringUtil.checkNull(status)) {
			sql += " and status = ?";
			params.add(status);
		}
		return db.total(sql, params);
	}

	@Override
	public List<GoodsInfo> findByCondition(String tid, String pid, String sname, String status, int page, int rows) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select gid, sname, tid, pid, price, status from goodsinfo where 1=1";
		if(!StringUtil.checkNull(tid)) {
			sql += " and tid = ?";
			params.add(tid);
		}
		if(!StringUtil.checkNull(pid)) {
			sql += " and pid = ?";
			params.add(pid);
		}
		if(!StringUtil.checkNull(sname)) {
			sql += " and sname like concat('%', ?, '%')";
			params.add(sname);
		}
		if(!StringUtil.checkNull(status)) {
			sql += " and status = ?";
			params.add(status);
		}
		sql += " order by gid desc limit ?, ?";
		params.add((page - 1) * rows);
		params.add(rows);
		return db.finds(GoodsInfo.class, sql, params);
	}

	@Override
	public List<GoodsNameAndGid> getGoodsName(String tid, String pid, String status) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select gid, sname from goodsinfo where 1=1";
		if(!StringUtil.checkNull(tid)) {
			sql += " and tid = ?";
			params.add(tid);
		}
		if(!StringUtil.checkNull(pid)) {
			sql += " and pid = ?";
			params.add(pid);
		}
		if(!StringUtil.checkNull(status)) {
			sql += " and status = ?";
			params.add(status);
		}
		sql += " order by gid desc";
		return db.finds(GoodsNameAndGid.class, sql, params);
	}

	@Override
	public List<GoodsInfo> findByName(String name, int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select gid,sname, pics from goodsinfo where sname like concat('%', ?, '%') and status != 0 order by gid desc limit ?, ?";
		return db.finds(GoodsInfo.class, sql, name, (page - 1) * rows, rows);
	}

	@Override
	public int total(String tid, String pid, String price) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select count(gid) from goodsinfo where 1=1";
		if (!StringUtil.checkNull(tid)) {
			sql += " and tid = ?";
			params.add(tid);
		}
		if (!StringUtil.checkNull(pid)) {
			sql += " and pid = ?";
			params.add(pid);
		}
		switch(price) {
		case "": break;
		case "1": sql += "and price between 1000 and 3000"; break;
		case "2": sql += "and price between 3000 and 5000"; break;
		case "3": sql += "and price between 5000 and 7000"; break;
		case "4": sql += "and price between 7000 and 9000"; break;
		case "5": sql += "and price > 9000"; break;
		}
		return db.total(sql, params);
	}

	@Override
	public List<GoodsInfo> findByConditioners(String tid, String pid, String price, String desc, int page, int rows) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select gid, sname, pics, price from goodsinfo where 1=1";
		if (!StringUtil.checkNull(tid)) {
			sql += " and tid = ?";
			params.add(tid);
		}
		if (!StringUtil.checkNull(pid)) {
			sql += " and pid = ?";
			params.add(pid);
		}
		switch(price) {
		case "": break;
		case "1": sql += " and price between 1000 and 3000"; break;
		case "2": sql += " and price between 3000 and 5000"; break;
		case "3": sql += " and price between 5000 and 7000"; break;
		case "4": sql += " and price between 7000 and 9000"; break;
		case "5": sql += " and price > 9000"; break;
		}
		if ("1".equals(desc)) {
			sql += " order by price desc limit ?, ?";
		} else {
			sql += " order by price limit ?,?";
		}	
		params.add((page - 1) * rows);
		params.add(rows);
		return db.finds(GoodsInfo.class, sql, params) ;
	}

}
