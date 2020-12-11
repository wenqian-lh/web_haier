package com.web.haier.biz.impl;

import java.util.List;

import com.web.haier.bean.TypeInfo;
import com.web.haier.biz.ITypeInfoBiz;
import com.web.haier.dao.ITypeInfoDao;
import com.web.haier.dao.impl.TypeInfoDaoImpl;
import com.web.haier.dto.JsonObject;
import com.web.haier.util.StringUtil;

/**
 * 父分类 业务模型层 实现
 * @author admin
 *
 */
public class TypeInfoBizImpl implements ITypeInfoBiz {

	@Override
	public int add(TypeInfo tf) {
		if (StringUtil.checkNull(tf.getTname())) {
			return -1;
		}
		ITypeInfoDao typeInfoDao = new TypeInfoDaoImpl();
		return typeInfoDao.add(tf);
	}

	@Override
	public int update(TypeInfo tf) {
		if (StringUtil.checkNull(tf.getTname())) {
			return -1;
		}
		ITypeInfoDao typeInfoDao = new TypeInfoDaoImpl();
		return typeInfoDao.update(tf);
	}

	@Override
	public List<TypeInfo> findAll() {
		ITypeInfoDao typeInfoDao = new TypeInfoDaoImpl();
		return typeInfoDao.findAll();
	}
	
	/**
	 * 针对easyui中分页查询的,easyui有分页组件,但是必须按照这个分页组件中 需要的 数据格式 返回数据
	 * {total:"总记录数", rows:[{}, {}]}
	 */
	@Override
	public JsonObject findByPage(int page, int rows) {
		ITypeInfoDao typeInfoDao = new TypeInfoDaoImpl();
		return new JsonObject(typeInfoDao.total(), typeInfoDao.findByPage(page, rows));
	}

	@Override
	public int delete(TypeInfo tf) {
		if (StringUtil.checkNull(tf.getTname())) {
			return -1;
		}
		ITypeInfoDao typeInfoDao = new TypeInfoDaoImpl();
		return typeInfoDao.delete(tf);
	}
	
}
