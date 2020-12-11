package com.web.haier.biz.impl;

import java.util.List;

import com.web.haier.bean.PTypeInfo;
import com.web.haier.biz.IPTypeInfoBiz;
import com.web.haier.dao.IPTypeInfoDao;
import com.web.haier.dao.impl.PTypeInfoDaoImpl;
import com.web.haier.dto.JsonObject;
import com.web.haier.util.StringUtil;

/**
 * 子分类 业务模型层 实现
 * @author admin
 *
 */
public class PTypeInfoBizImpl implements IPTypeInfoBiz {

	@Override
	public int add(PTypeInfo pt) {
		if (StringUtil.checkNull(pt.getPname())) {
			return -1;
		}
		IPTypeInfoDao ptypeInfoDao = new PTypeInfoDaoImpl();
		return ptypeInfoDao.add(pt);
	}

	@Override
	public int update(PTypeInfo pt) {
		if (StringUtil.checkNull(pt.getPname())) {
			return -1;
		}
		IPTypeInfoDao ptypeInfoDao = new PTypeInfoDaoImpl();
		return ptypeInfoDao.update(pt);
	}

	@Override
	public List<PTypeInfo> findAll() {
		IPTypeInfoDao ptypeInfoDao = new PTypeInfoDaoImpl();
		return ptypeInfoDao.findAll();
	}
	
	/**
	 * 针对easyui中分页查询的,easyui有分页组件,但是必须按照这个分页组件中 需要的 数据格式 返回数据
	 * {total:"总记录数", rows:[{}, {}]}
	 */
	@Override
	public JsonObject findByPage(int page, int rows) {
		IPTypeInfoDao ptypeInfoDao = new PTypeInfoDaoImpl();
		return new JsonObject(ptypeInfoDao.total(), ptypeInfoDao.findByPage(page, rows));
	}

	@Override
	public JsonObject findCondition(String tid, String pname, int page, int rows) {
		IPTypeInfoDao ptypeInfoDao = new PTypeInfoDaoImpl();
		return new JsonObject(ptypeInfoDao.total(tid, pname), ptypeInfoDao.findCondition(tid, pname, page, rows));
	}

	@Override
	public int delete(PTypeInfo pt) {
		if (StringUtil.checkNull(pt.getPname())) {
			return -1;
		}
		IPTypeInfoDao ptypeInfoDao = new PTypeInfoDaoImpl();
		return ptypeInfoDao.delete(pt);
	}

	@Override
	public PTypeInfo findByPid(String pid) {
		IPTypeInfoDao ptypeInfoDao = new PTypeInfoDaoImpl();
		return ptypeInfoDao.findByPid(pid);
	}
	
}
