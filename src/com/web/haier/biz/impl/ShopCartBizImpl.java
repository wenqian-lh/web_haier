package com.web.haier.biz.impl;

import java.util.List;

import com.web.haier.bean.ShopCart;
import com.web.haier.biz.IShopCartBiz;
import com.web.haier.dao.IShopCartDao;
import com.web.haier.dao.impl.ShopCartDaoImpl;

public class ShopCartBizImpl implements IShopCartBiz {

	@Override
	public int add(ShopCart sc) {
		IShopCartDao shopCartDao = new ShopCartDaoImpl();
		return shopCartDao.add(sc);
	}

	@Override
	public int delete(String[] cartId) {
		IShopCartDao shopCartDao = new ShopCartDaoImpl();
		return shopCartDao.delete(cartId);
	}

	@Override
	public List<ShopCart> findAllByMid(String mid) {
		IShopCartDao shopCartDao = new ShopCartDaoImpl();
		return shopCartDao.findAllByMid(mid);
	}
	
	@Override
	public List<ShopCart> findAllByCartId(String[] cartId, String mid) {
		IShopCartDao shopCartDao = new ShopCartDaoImpl();
		return shopCartDao.findAllByCartId(cartId, mid);
	}

	@Override
	public int update(String cartId, String num) {
		IShopCartDao shopCartDao = new ShopCartDaoImpl();
		return shopCartDao.update(cartId, num);
	}

}
