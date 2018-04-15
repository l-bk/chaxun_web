/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.consumable.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.consumable.entity.CxConsumableInfo;
import com.thinkgem.jeesite.modules.consumable.dao.CxConsumableInfoDao;

/**
 * 耗材信息Service
 * @author lbk
 * @version 2018-03-29
 */
@Service
@Transactional(readOnly = true)
public class CxConsumableInfoService extends CrudService<CxConsumableInfoDao, CxConsumableInfo> {

	@Autowired
	private CxConsumableInfoDao cxConsumableInfoDao;
	
	public CxConsumableInfo get(String id) {
		return super.get(id);
	}
	
	public List<CxConsumableInfo> findList(CxConsumableInfo cxConsumableInfo) {
		return super.findList(cxConsumableInfo);
	}
	
	public Page<CxConsumableInfo> findPage(Page<CxConsumableInfo> page, CxConsumableInfo cxConsumableInfo) {
		return super.findPage(page, cxConsumableInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(CxConsumableInfo cxConsumableInfo) {
		if(StringUtils.isNotBlank(cxConsumableInfo.getConId())) {
			cxConsumableInfo.setId(cxConsumableInfo.getConId());
		}
		super.save(cxConsumableInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(CxConsumableInfo cxConsumableInfo) {
		super.delete(cxConsumableInfo);
	}
	
	@Transactional(readOnly = false)
	public void cleanAll(){
		cxConsumableInfoDao.cleanAll();
	}
}