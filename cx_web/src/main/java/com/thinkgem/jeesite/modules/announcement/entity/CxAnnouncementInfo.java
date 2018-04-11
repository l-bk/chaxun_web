/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.announcement.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 公告信息Entity
 * @author lbk
 * @version 2018-04-11
 */
public class CxAnnouncementInfo extends DataEntity<CxAnnouncementInfo> {
	
	private static final long serialVersionUID = 1L;
	private String anId;		// 唯一标识
	private String anDetails;		// 公告内容
	private String anStatus;		// 公告状态， 0.下架  1.上架（上架指在小程序显示，只有一条可以）
	private Date createDate;
	
	public CxAnnouncementInfo() {
		super();
	}

	public CxAnnouncementInfo(String id){
		super(id);
	}

	public String getAnId() {
		return anId;
	}

	public void setAnId(String anId) {
		this.anId = anId;
	}
	
	public String getAnDetails() {
		return anDetails;
	}

	public void setAnDetails(String anDetails) {
		this.anDetails = anDetails;
	}
	
	public String getAnStatus() {
		return anStatus;
	}

	public void setAnStatus(String anStatus) {
		this.anStatus = anStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
}