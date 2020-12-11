/**
 * 
 */
package com.web.haier.biz.impl;

import java.util.List;

import com.web.haier.bean.AddressInfo;
import com.web.haier.biz.IAddressInfoBiz;
import com.web.haier.dao.IAddressInfoDao;
import com.web.haier.dao.impl.AddressInfoDaoImpl;

/**
 * @author lh
 * @data 2020年12月6日
 * Email 2944862497@qq.com
 */
public class AddressInfoBizImpl implements IAddressInfoBiz{

	@Override
	public int add(AddressInfo addressInfo) {
		IAddressInfoDao addressInfoDao = new AddressInfoDaoImpl();
		return addressInfoDao.add(addressInfo);
	}

	@Override
	public List<AddressInfo> findAll(String mid) {
		IAddressInfoDao addressInfoDao = new AddressInfoDaoImpl();
		return addressInfoDao.findAll(mid);
	}

	@Override
	public int update(AddressInfo addressInfo) {
		IAddressInfoDao addressInfoDao = new AddressInfoDaoImpl();
		return addressInfoDao.update(addressInfo);
	}

	@Override
	public int delete(String addrId) {
		IAddressInfoDao addressInfoDao = new AddressInfoDaoImpl();
		return addressInfoDao.delete(addrId);
	}

	@Override
	public int setDefault(String onaddrId, String addrId) {
		IAddressInfoDao addressInfoDao = new AddressInfoDaoImpl();
		return addressInfoDao.setDefault(onaddrId, addrId);
	}
}
