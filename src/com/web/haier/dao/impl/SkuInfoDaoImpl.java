/**
 * 
 */
package com.web.haier.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.web.haier.bean.SkuInfo;
import com.web.haier.dao.DBHelper;
import com.web.haier.dao.ISkuInfoDao;
import com.web.haier.util.StringUtil;

/**
 * @author lh
 * @data 2020年11月29日
 * Email 2944862497@qq.com
 */
public class SkuInfoDaoImpl implements ISkuInfoDao{

	@Override
	public List<SkuInfo> findByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "SELECT s.sid, g.sname, attrname, attrvalue, s.price, stock, s.`status`  from skuinfo as s, goodsinfo as g where s.gid = g.gid limit ?,?";
		return db.finds(SkuInfo.class, sql, (page - 1) * rows, rows);
	}

	@Override
	public int total() {
		DBHelper db = new DBHelper();
		String sql = "select count(sid) from skuinfo";
		return db.total(sql);
	}

	@Override
	public int add(SkuInfo skuInfo) {
		DBHelper db = new DBHelper();
		String sql = "insert into skuinfo values(0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return db.update(sql, skuInfo.getGid(), skuInfo.getAttrname(), skuInfo.getAttrvalue(), skuInfo.getPrice(), skuInfo.getCost(),
				skuInfo.getPics(), skuInfo.getStock(), skuInfo.getMaxStock(), skuInfo.getMinStock(), skuInfo.getStatus() );
	}

	@Override
	public SkuInfo findBySid(String sid) {
		DBHelper db = new DBHelper();
		String sql = "SELECT s.sid, g.gid,p.tid, p.pname, g.sname, attrname, attrvalue, s.price, cost, s.pics, stock, maxStock, minStock, s.`status`  from skuinfo as s, goodsinfo as g, ptypeinfo as p where  s.sid=? and s.gid=g.gid and g.pid = p.pid";
		return db.find(SkuInfo.class, sql, sid);
	}

	@Override
	public int update(SkuInfo skuInfo) {
		DBHelper db = new DBHelper();		
		if(StringUtil.checkNull(skuInfo.getPics())) {
			String sql = "update skuinfo set attrname=?, attrvalue=?, price=?, cost=?, stock=?, maxStock=?, minStock=?, status=? where sid=?";
			return db.update(sql, skuInfo.getAttrname(), skuInfo.getAttrvalue(), skuInfo.getPrice(), skuInfo.getCost(),
					skuInfo.getStock(), skuInfo.getMaxStock(), skuInfo.getMinStock(), skuInfo.getStatus(), skuInfo.getSid());
		}
		String sql = "update skuinfo set attrname=?, attrvalue=?, price=?, cost=?, pics=?, stock=?, maxStock=?, minStock=?, status=? where sid=?";
		return db.update(sql, skuInfo.getAttrname(), skuInfo.getAttrvalue(), skuInfo.getPrice(), skuInfo.getCost(),
				skuInfo.getPics(), skuInfo.getStock(), skuInfo.getMaxStock(), skuInfo.getMinStock(), skuInfo.getStatus(), skuInfo.getSid());
	}

	@Override
	public int total(String tid, String pid, String sname, String status) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select count(sid) from goodsinfo as g, skuinfo as s where g.gid = s.gid";
		if(!StringUtil.checkNull(tid)) {
			sql += " and g.tid = ?";
			params.add(tid);
		}
		if(!StringUtil.checkNull(pid)) {
			sql += " and g.pid = ?";
			params.add(pid);
		}
		if(!StringUtil.checkNull(sname)) {
			sql += " and sname like concat('%', ?, '%')";
			params.add(sname);
		}
		if(!StringUtil.checkNull(status)) {
			sql += " and g.status = ?";
			params.add(status);
		}
		return db.total(sql, params);
	}

	@Override
	public List<SkuInfo> findCondition(String tid, String pid, String sname, String status, int page, int rows) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select s.sid, g.sname, attrname, attrvalue, s.price, stock, s.`status` from goodsinfo as g, skuinfo as s where g.gid = s.gid";
		if(!StringUtil.checkNull(tid)) {
			sql += " and g.tid = ?";
			params.add(tid);
		}
		if(!StringUtil.checkNull(pid)) {
			sql += " and g.pid = ?";
			params.add(pid);
		}
		if(!StringUtil.checkNull(sname)) {
			sql += " and sname like concat('%', ?, '%')";
			params.add(sname);
		}
		if(!StringUtil.checkNull(status)) {
			sql += " and g.status = ?";
			params.add(status);
		}
		sql += " order by s.sid desc limit ?,?";
		params.add((page - 1) * rows);
		params.add(rows);
		return db.finds(SkuInfo.class, sql, params);
	}

	@Override
	public List<SkuInfo> getAttributes(String gid) {
		DBHelper db = new DBHelper();
		String sql = "select sid, attrname, attrvalue, price, pics, stock from skuinfo where gid=? and status !=0";
		return db.finds(SkuInfo.class, sql, gid);
	}

}
