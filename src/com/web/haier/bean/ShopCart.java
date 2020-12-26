package com.web.haier.bean;

import java.io.Serializable;

/**
 * 购物车信息
 * @author admin
 *
 */
public class ShopCart implements Serializable {
	private static final long serialVersionUID = 8431373435321651830L;
	private Integer cartId;
	private Integer mid;
	private Integer sid;
	private Integer num;
	
	private String attrname;
	private String attrvalue;
	private Double price;
	private Double cost;
	private String pics;
	
	private String sname;

	
	
	@Override
	public String toString() {
		return "ShopCart [cartId=" + cartId + ", mid=" + mid + ", sid=" + sid + ", num=" + num + ", attrname="
				+ attrname + ", attrvalue=" + attrvalue + ", price=" + price + ", cost=" + cost + ", pics=" + pics
				+ ", sname=" + sname + "]";
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getAttrname() {
		return attrname;
	}

	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}

	public String getAttrvalue() {
		return attrvalue;
	}

	public void setAttrvalue(String attrvalue) {
		this.attrvalue = attrvalue;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}
	
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attrname == null) ? 0 : attrname.hashCode());
		result = prime * result + ((attrvalue == null) ? 0 : attrvalue.hashCode());
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((mid == null) ? 0 : mid.hashCode());
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		result = prime * result + ((pics == null) ? 0 : pics.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
		result = prime * result + ((sname == null) ? 0 : sname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopCart other = (ShopCart) obj;
		if (attrname == null) {
			if (other.attrname != null)
				return false;
		} else if (!attrname.equals(other.attrname))
			return false;
		if (attrvalue == null) {
			if (other.attrvalue != null)
				return false;
		} else if (!attrvalue.equals(other.attrvalue))
			return false;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (mid == null) {
			if (other.mid != null)
				return false;
		} else if (!mid.equals(other.mid))
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		if (pics == null) {
			if (other.pics != null)
				return false;
		} else if (!pics.equals(other.pics))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		if (sname == null) {
			if (other.sname != null)
				return false;
		} else if (!sname.equals(other.sname))
			return false;
		return true;
	}
}
