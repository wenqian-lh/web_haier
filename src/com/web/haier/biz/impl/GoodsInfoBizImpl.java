/**
 * 
 */
package com.web.haier.biz.impl;

import java.util.List;

import com.web.haier.bean.GoodsInfo;
import com.web.haier.bean.GoodsNameAndGid;
import com.web.haier.biz.IGoodsInfoBiz;
import com.web.haier.dao.IGoodsInfoDao;
import com.web.haier.dao.impl.GoodsInfoDaoImpl;
import com.web.haier.dto.JsonObject;

/**
 * @author lh
 * @data 2020年11月27日
 * Email 2944862497@qq.com
 */
public class GoodsInfoBizImpl implements IGoodsInfoBiz{

	@Override
	public JsonObject findByPage(int page, int rows) {
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return new JsonObject(goodsInfoDao.total(true), goodsInfoDao.findByPage(page, rows));
	}

	@Override
	public List<GoodsInfo> finds(int page, int rows) {
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.finds(page, rows);
	}

	@Override
	public int total() {
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.total(false);
	}

	@Override
	public int add(GoodsInfo goodsInfo) {
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.add(goodsInfo);
	}

	@Override
	public int update(GoodsInfo goodsInfo) {
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.update(goodsInfo);
	}

	@Override
	public GoodsInfo findByGid(String gid) {
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.findByGid(gid);
	}

	@Override
	public JsonObject findByCondition(String tid, String pid, String sname, String status, int page, int rows) {
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return new JsonObject(goodsInfoDao.total(tid, pid, sname, status), goodsInfoDao.findByCondition(tid, pid, sname, status, page, rows));
	}

	@Override
	public List<GoodsNameAndGid> getGoodsName(String tid, String pid, String status) {
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return  goodsInfoDao.getGoodsName(tid, pid, status);
	}

	@Override
	public List<GoodsInfo> findByName(String name, int page, int rows) {
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.findByName(name, page, rows);
	}

	@Override
	public int total(String tid, String pid, String sname, String status) {
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.total(tid, pid, sname, status);
	}

	@Override
	public int total(String tid, String pid, String price) {
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.total(tid, pid, price);
	}

	@Override
	public List<GoodsInfo> findByConditioners(String tid, String pid, String price, String desc, int page, int rows) {
		IGoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.findByConditioners(tid, pid, price, desc, page, rows);
	}

}
