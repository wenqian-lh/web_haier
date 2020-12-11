package com.web.haier.biz;

import java.util.List;

import com.web.haier.bean.ShopCart;

public interface IShopCartBiz {
	/**
	 * 添加 购物车
	 * @param sc
	 * @return
	 */
	public int add(ShopCart sc);
	
	/**
	 * 删除 购物车
	 * @param cartId
	 * @return
	 */
	public int delete(String[] cartId);
	
	/**
	 * 根据 mid 查询所有购物车商品
	 * @param mid
	 * @return
	 */
	public List<ShopCart> findAllByMid(String mid);
	
	/**
	 * 根据 cartId 查询 所选购物车商品信息
	 * @param cartId
	 * @param mid 
	 * @return
	 */
	public List<ShopCart> findAllByCartId(String[] cartId, String mid);
	
	/**
	 * 修改购物车商品数量
	 * @param cartId
	 * @return
	 */
	public int update(String cartId, String num);
}
