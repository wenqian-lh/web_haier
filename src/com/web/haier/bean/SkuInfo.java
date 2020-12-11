package com.web.haier.bean;

import java.io.Serializable;

/**
 * 库存信息
 * @author admin
 *
 */
public class SkuInfo implements Serializable {

	private static final long serialVersionUID = 6308686232275063356L;
	private Integer sid;
	private Integer gid;
	private Integer tid;	
	private String pname;
	private String sname;
	private String attrname;
	private String attrvalue;
	private Double price;
	private Double cost;
	private String pics;
	private Integer stock;
	private Integer maxStock;
	private Integer minStock;
	private Integer status;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attrname == null) ? 0 : attrname.hashCode());
		result = prime * result + ((attrvalue == null) ? 0 : attrvalue.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((gid == null) ? 0 : gid.hashCode());
		result = prime * result + ((maxStock == null) ? 0 : maxStock.hashCode());
		result = prime * result + ((minStock == null) ? 0 : minStock.hashCode());
		result = prime * result + ((pics == null) ? 0 : pics.hashCode());
		result = prime * result + ((pname == null) ? 0 : pname.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
		result = prime * result + ((sname == null) ? 0 : sname.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		result = prime * result + ((tid == null) ? 0 : tid.hashCode());
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
		SkuInfo other = (SkuInfo) obj;
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
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (gid == null) {
			if (other.gid != null)
				return false;
		} else if (!gid.equals(other.gid))
			return false;
		if (maxStock == null) {
			if (other.maxStock != null)
				return false;
		} else if (!maxStock.equals(other.maxStock))
			return false;
		if (minStock == null) {
			if (other.minStock != null)
				return false;
		} else if (!minStock.equals(other.minStock))
			return false;
		if (pics == null) {
			if (other.pics != null)
				return false;
		} else if (!pics.equals(other.pics))
			return false;
		if (pname == null) {
			if (other.pname != null)
				return false;
		} else if (!pname.equals(other.pname))
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		if (tid == null) {
			if (other.tid != null)
				return false;
		} else if (!tid.equals(other.tid))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "SkuInfo [sid=" + sid + ", gid=" + gid + ", tid=" + tid + ", pname=" + pname + ", sname=" + sname
				+ ", attrname=" + attrname + ", attrvalue=" + attrvalue + ", price=" + price + ", cost=" + cost
				+ ", pics=" + pics + ", stock=" + stock + ", maxStock=" + maxStock + ", minStock=" + minStock
				+ ", status=" + status + "]";
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
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
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getMaxStock() {
		return maxStock;
	}
	public void setMaxStock(Integer maxStock) {
		this.maxStock = maxStock;
	}
	public Integer getMinStock() {
		return minStock;
	}
	public void setMinStock(Integer minStock) {
		this.minStock = minStock;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	
	
}
