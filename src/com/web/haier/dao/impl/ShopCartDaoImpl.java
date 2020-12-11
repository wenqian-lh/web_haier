package com.web.haier.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.web.haier.bean.ShopCart;
import com.web.haier.dao.DBHelper;
import com.web.haier.dao.IShopCartDao;

public class ShopCartDaoImpl implements IShopCartDao {

	@Override
	public int add(ShopCart sc) {
		DBHelper db = new DBHelper();
		String sql = "insert into shopcart values(0, ?, ?, ?)";
		return db.update(sql, sc.getMid(), sc.getSid(), sc.getNum());
	}

	@Override
	public int delete(String[] cartId) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "delete from shopcart where cartId=?";
		
		params.add(cartId[0]);
		if (cartId.length > 1) {
			for (int i = 1, len = cartId.length; i < len; i++) {
				sql += " or cartId=?";
				params.add(cartId[i]);
			}
		}
		return db.update(sql, params);
	}

	@Override
	public List<ShopCart> findAllByMid(String mid) {
		DBHelper db = new DBHelper();
		String sql = "select cartId, mid, num, s.sid, attrname, attrvalue, s.price, s.pics, g.sname"+
		" from shopcart c, skuinfo s, goodsinfo g where c.sid=s.sid and s.gid=g.gid and mid=?";
		return db.finds(ShopCart.class, sql, mid);
	}
	
	@Override
	public List<ShopCart> findAllByCartId(String[] cartId, String mid) {
		DBHelper db = new DBHelper();
		List<ShopCart> params = new ArrayList<ShopCart>();
		String sql = "select cartId, mid, num, s.sid, attrname, attrvalue, s.price, s.pics, g.sname"+
				" from shopcart c, skuinfo s, goodsinfo g where c.sid=s.sid and s.gid=g.gid and c.mid=? and cartId=?";
		params.add(db.find(ShopCart.class, sql, mid, cartId[0]));
		if (cartId.length > 1) {
			for (int i = 1, len = cartId.length; i < len; i++) {
				params.add(db.find(ShopCart.class, sql, mid, cartId[i]));
			}
		}
		return params;
	}

	@Override
	public int update(String cartId, String num) {
		DBHelper db = new DBHelper();
		String sql = "update shopcart set num=? where cartId=?";
		return db.update(sql, num, cartId);
	}
}
