/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.consumable.entity;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 耗材信息Entity
 * @author lbk
 * @version 2018-03-29
 */
public class CxConsumableInfo extends DataEntity<CxConsumableInfo> {
	
	private static final long serialVersionUID = 1L;
	private String conId;		// 耗材id
	private String conBrand;		// 品牌
	private String conCompatible;		// 适用机型
	private String conCode;		// 耗材编码
	private String conModel;		// 耗材型号
	private String conDetails;		// 耗材描述
	private String conNum;		// 打印页数和寿命
	private BigDecimal conReferencePrice;		// 参考价格
	
	public CxConsumableInfo() {
		super();
	}

	public CxConsumableInfo(String id){
		super(id);
	}

	public String getConId() {
		return conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}
	
	public String getConBrand() {
		return conBrand;
	}

	public void setConBrand(String conBrand) {
		this.conBrand = conBrand;
	}
	
	public String getConCompatible() {
		return conCompatible;
	}

	public void setConCompatible(String conCompatible) {
		this.conCompatible = conCompatible;
	}
	
	public String getConCode() {
		return conCode;
	}

	public void setConCode(String conCode) {
		this.conCode = conCode;
	}
	
	public String getConModel() {
		return conModel;
	}

	public void setConModel(String conModel) {
		this.conModel = conModel;
	}
	
	public String getConDetails() {
		return conDetails;
	}

	public void setConDetails(String conDetails) {
		this.conDetails = conDetails;
	}
	
	public String getConNum() {
		return conNum;
	}

	public void setConNum(String conNum) {
		this.conNum = conNum;
	}

	public BigDecimal getConReferencePrice() {
		return conReferencePrice;
	}

	public void setConReferencePrice(BigDecimal conReferencePrice) {
		this.conReferencePrice = conReferencePrice;
	}
	
	
}