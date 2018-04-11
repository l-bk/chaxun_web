/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.announcement.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.announcement.entity.CxAnnouncementInfo;
import com.thinkgem.jeesite.modules.announcement.dao.CxAnnouncementInfoDao;

/**
 * 公告信息Service
 * @author lbk
 * @version 2018-04-11
 */
@Service
@Transactional(readOnly = true)
public class CxAnnouncementInfoService extends CrudService<CxAnnouncementInfoDao, CxAnnouncementInfo> {
	
	@Autowired
	private CxAnnouncementInfoDao cxAnnouncementInfoDao;

	public CxAnnouncementInfo get(String id) {
		return super.get(id);
	}
	
	public List<CxAnnouncementInfo> findList(CxAnnouncementInfo cxAnnouncementInfo) {
		return super.findList(cxAnnouncementInfo);
	}
	
	public Page<CxAnnouncementInfo> findPage(Page<CxAnnouncementInfo> page, CxAnnouncementInfo cxAnnouncementInfo) {
		return super.findPage(page, cxAnnouncementInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(CxAnnouncementInfo cxAnnouncementInfo) {
		if(StringUtils.isNotBlank(cxAnnouncementInfo.getAnId())) {
			cxAnnouncementInfo.setId(cxAnnouncementInfo.getAnId());
		}
		if(StringUtils.isNotBlank(cxAnnouncementInfo.getAnStatus()) && "1".equals(cxAnnouncementInfo.getAnStatus())) {
			CxAnnouncementInfo info = new CxAnnouncementInfo();
			info.setAnStatus("1");
			if(super.findList(info).size() >0) {
				cxAnnouncementInfoDao.updateStatus();
			}
		}
		super.save(cxAnnouncementInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(CxAnnouncementInfo cxAnnouncementInfo) {
		super.delete(cxAnnouncementInfo);
	}
	
}