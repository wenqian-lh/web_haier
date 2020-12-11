package com.web.haier.biz.impl;

import java.util.List;

import com.web.haier.bean.MemberInfo;
import com.web.haier.biz.IMemberInfoBiz;
import com.web.haier.dao.IMemberInfoDao;
import com.web.haier.dao.impl.MemberInfoDaoImpl;
import com.web.haier.dto.JsonObject;
import com.web.haier.util.StringUtil;

public class MemberInfoBizImpl implements IMemberInfoBiz {

	@Override
	public int add(MemberInfo mf) {
		if (StringUtil.checkNull(mf.getRealName())) {
			return -1;
		}
		IMemberInfoDao MemberInfoDao = new MemberInfoDaoImpl();
		return MemberInfoDao.add(mf);
	}

	@Override
	public int update(MemberInfo mf) {
		if (StringUtil.checkNull(mf.getRealName())) {
			return -1;
		}
		IMemberInfoDao MemberInfoDao = new MemberInfoDaoImpl();
		return MemberInfoDao.update(mf);
	}
	
	@Override
	public int delete(MemberInfo mf) {
		if (StringUtil.checkNull(mf.getRealName())) {
			return -1;
		}
		IMemberInfoDao MemberInfoDao = new MemberInfoDaoImpl();
		return MemberInfoDao.delete(mf);
	}
	
	@Override
	public List<MemberInfo> findAll() {
		IMemberInfoDao MemberInfoDao = new MemberInfoDaoImpl();
		return MemberInfoDao.findAll();
	}

	@Override
	public JsonObject findByPage(int page, int rows) {
		IMemberInfoDao MemberInfoDao = new MemberInfoDaoImpl();
		return new JsonObject(MemberInfoDao.total(), MemberInfoDao.findByPage(page, rows));
	}

	@Override
	public JsonObject findByCondition(String way, String zway, int page, int rows) {
		IMemberInfoDao MemberInfoDao = new MemberInfoDaoImpl();
		return new JsonObject(MemberInfoDao.total(way, zway), MemberInfoDao.findByCondition(way, zway, page, rows));
	}

	@Override
	public MemberInfo findByMid(String mid) {
		IMemberInfoDao MemberInfoDao = new MemberInfoDaoImpl();
		return MemberInfoDao.findByMid(mid);
	}

	@Override
	public int reg(MemberInfo mf) {
		if (StringUtil.checkNull(mf.getRealName())) {
			return -1;
		}
		IMemberInfoDao MemberInfoDao = new MemberInfoDaoImpl();
		return MemberInfoDao.reg(mf);
	}

	@Override
	public MemberInfo login(String email, String pwd) {
		if (StringUtil.checkNull(email, pwd)) {
			return null;
		}
		IMemberInfoDao MemberInfoDao = new MemberInfoDaoImpl();
		return MemberInfoDao.login(email, pwd);
	}

	@Override
	public int save(MemberInfo mf) {
		if (StringUtil.checkNull(mf.getRealName())) {
			return -1;
		}
		IMemberInfoDao MemberInfoDao = new MemberInfoDaoImpl();
		return MemberInfoDao.save(mf);
	}

	@Override
	public MemberInfo getPhoto(MemberInfo mf) {
		IMemberInfoDao MemberInfoDao = new MemberInfoDaoImpl();
		return MemberInfoDao.getPhoto(mf);
	}

}
