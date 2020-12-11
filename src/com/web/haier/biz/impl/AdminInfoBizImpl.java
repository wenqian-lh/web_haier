/**
 * 
 */
package com.web.haier.biz.impl;

import com.web.haier.bean.AdminInfo;
import com.web.haier.biz.IAdminInfoBiz;
import com.web.haier.dao.IAdminInfoDao;
import com.web.haier.dao.impl.AdminInfoDaoImpl;
import com.web.haier.dto.JsonObject;
import com.web.haier.util.StringUtil;

/**
 * 管理员业务层接口
 * @author lh
 * @data 2020年11月21日
 * Email 2944862497@qq.com
 */
public class AdminInfoBizImpl implements IAdminInfoBiz {

	@Override
	public AdminInfo login(String adminId, String pwd) {
		if (StringUtil.checkNull(adminId, pwd)) {
			return null;
		}
		IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
		return adminInfoDao.login(adminId, pwd);
	}

	@Override
	public JsonObject findByPage(int page, int rows) {
		IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
		return new JsonObject(adminInfoDao.total(), adminInfoDao.findByPage(page, rows));
	}

	@Override
	public int add(AdminInfo adminInfo) {
		IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
		return adminInfoDao.add(adminInfo);
	}

	@Override
	public int update(AdminInfo adminInfo) {
		IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
		return adminInfoDao.update(adminInfo);
	}

	@Override
	public int delete(Integer adminId) {
		IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
		return adminInfoDao.delete(adminId);
	}

	@Override
	public JsonObject findByCondition(String status, String val, String text, int page, int rows) {
		IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
		if("0".equals(status)) {
			return new JsonObject(adminInfoDao.totalByStatus(true), adminInfoDao.findByCondition(val, text, page, rows));
		} else if("1".equals(status)) {
			return new JsonObject(adminInfoDao.totalByStatus(false), adminInfoDao.findByCondition(val, text, page, rows));
		}
		
		return  new JsonObject(adminInfoDao.total(), adminInfoDao.findByCondition(val, text, page, rows));
	}

}
