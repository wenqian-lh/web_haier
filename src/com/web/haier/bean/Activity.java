package com.web.haier.bean;

import java.io.Serializable;

/**
 * 活动信息
 * @author admin
 *
 */
public class Activity implements Serializable {
	private static final long serialVersionUID = -6663853648778967774L;
	private Integer aid;
	private String aname;
	private Integer gid;
	private Double rebate;
	private String sdate;
	private String edate;
	private String detail;
	private Integer status;
	
	@Override
	public String toString() {
		return "Activity [aid=" + aid + ", aname=" + aname + ", gid=" + gid + ", rebate=" + rebate + ", sdate=" + sdate
				+ ", edate=" + edate + ", detail=" + detail + ", status=" + status + "]";
	}
	
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Double getRebate() {
		return rebate;
	}
	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
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
		result = prime * result + ((aid == null) ? 0 : aid.hashCode());
		result = prime * result + ((aname == null) ? 0 : aname.hashCode());
		result = prime * result + ((detail == null) ? 0 : detail.hashCode());
		result = prime * result + ((edate == null) ? 0 : edate.hashCode());
		result = prime * result + ((gid == null) ? 0 : gid.hashCode());
		result = prime * result + ((rebate == null) ? 0 : rebate.hashCode());
		result = prime * result + ((sdate == null) ? 0 : sdate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Activity other = (Activity) obj;
		if (aid == null) {
			if (other.aid != null)
				return false;
		} else if (!aid.equals(other.aid))
			return false;
		if (aname == null) {
			if (other.aname != null)
				return false;
		} else if (!aname.equals(other.aname))
			return false;
		if (detail == null) {
			if (other.detail != null)
				return false;
		} else if (!detail.equals(other.detail))
			return false;
		if (edate == null) {
			if (other.edate != null)
				return false;
		} else if (!edate.equals(other.edate))
			return false;
		if (gid == null) {
			if (other.gid != null)
				return false;
		} else if (!gid.equals(other.gid))
			return false;
		if (rebate == null) {
			if (other.rebate != null)
				return false;
		} else if (!rebate.equals(other.rebate))
			return false;
		if (sdate == null) {
			if (other.sdate != null)
				return false;
		} else if (!sdate.equals(other.sdate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}
