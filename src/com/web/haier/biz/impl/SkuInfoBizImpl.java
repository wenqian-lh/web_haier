/**
 * 
 */
package com.web.haier.biz.impl;

import java.util.List;

import com.web.haier.bean.SkuInfo;
import com.web.haier.biz.ISkuInfoBiz;
import com.web.haier.dao.ISkuInfoDao;
import com.web.haier.dao.impl.SkuInfoDaoImpl;
import com.web.haier.dto.JsonObject;

/**
 * @author lh
 * @data 2020年11月29日
 * Email 2944862497@qq.com
 */
public class SkuInfoBizImpl implements ISkuInfoBiz{

	@Override
	public JsonObject findByPage(int page, int rows) {
		ISkuInfoDao skuInfoDao = new SkuInfoDaoImpl();
		return new JsonObject(skuInfoDao.total(), skuInfoDao.findByPage(page, rows));
	}

	@Override
	public int add(SkuInfo skuInfo) {
		ISkuInfoDao skuInfoDao = new SkuInfoDaoImpl();
		return skuInfoDao.add(skuInfo);
	}

	@Override
	public SkuInfo findBySid(String sid) {
		ISkuInfoDao skuInfoDao = new SkuInfoDaoImpl();
		return skuInfoDao.findBySid(sid);
	}

	@Override
	public int update(SkuInfo skuInfo) {
		ISkuInfoDao skuInfoDao = new SkuInfoDaoImpl();
		return skuInfoDao.update(skuInfo);
	}

	@Override
	public JsonObject findCondition(String tid, String pid, String sname, String status, int page, int rows) {
		ISkuInfoDao skuInfoDao = new SkuInfoDaoImpl();
		return new JsonObject(skuInfoDao.total(tid, pid, sname, status), skuInfoDao.findCondition(tid, pid, sname, status, page, rows));
	}

	@Override
	public List<SkuInfo> getAttributes(String gid) {
		ISkuInfoDao skuInfoDao = new SkuInfoDaoImpl();
		return skuInfoDao.getAttributes(gid);
	}
	
	

}
