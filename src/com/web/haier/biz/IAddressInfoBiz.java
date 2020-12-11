/**
 * 
 */
package com.web.haier.biz;

import java.util.List;

import com.web.haier.bean.AddressInfo;

/**
 * 收货地址管理业务层接口
 * @author lh
 * @data 2020年12月6日
 * Email 2944862497@qq.com
 */
public interface IAddressInfoBiz {
	
	/**
	 * 添加地址信息
	 * @param addressInfo
	 * @return
	 */
	public int add(AddressInfo addressInfo);
	
	/**
	 * 查询所有地址信息
	 * @param addressInfo
	 * @return
	 */
	public List<AddressInfo> findAll(String mid);
	
	/**
	 * 修改地址信息
	 * @param addressInfo
	 * @return
	 */
	public int update(AddressInfo addressInfo);
	
	/**
	 * 删除地址信息
	 * @param addrId
	 * @return
	 */
	public int delete(String addrId);
	
	/**
	 * 设置 默认地址
	 * @param onaddrId
	 * @param addrId 
	 * @return
	 */
	public int setDefault(String onaddrId, String addrId);
}
