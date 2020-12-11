package com.web.haier.bean;

import java.io.Serializable;

/**
 * 商品信息
 * @author admin
 *
 */
public class GoodsInfo implements Serializable {
	private static final long serialVersionUID = 1697286517485866125L;
	private Integer gid;
	private String sname;
	private Integer tid;
	private Integer pid;
	private Double price;
	private String pics;
	private String intro;
	private Integer status;
	
	@Override
	public String toString() {
		return "GoodsInfo [gid=" + gid + ", sname=" + sname + ", tid=" + tid + ", pid=" + pid + ", price=" + price
				+ ", pics=" + pics + ", intro=" + intro + ", status=" + status + "]";
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
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gid == null) ? 0 : gid.hashCode());
		result = prime * result + ((intro == null) ? 0 : intro.hashCode());
		result = prime * result + ((pics == null) ? 0 : pics.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((sname == null) ? 0 : sname.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		GoodsInfo other = (GoodsInfo) obj;
		if (gid == null) {
			if (other.gid != null)
				return false;
		} else if (!gid.equals(other.gid))
			return false;
		if (intro == null) {
			if (other.intro != null)
				return false;
		} else if (!intro.equals(other.intro))
			return false;
		if (pics == null) {
			if (other.pics != null)
				return false;
		} else if (!pics.equals(other.pics))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
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
		if (tid == null) {
			if (other.tid != null)
				return false;
		} else if (!tid.equals(other.tid))
			return false;
		return true;
	}
}
