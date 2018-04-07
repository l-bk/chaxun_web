/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pic.entity.CxPic;
import com.thinkgem.jeesite.modules.pic.dao.CxPicDao;

/**
 * 图片表Service
 * @author lbk
 * @version 2018-04-07
 */
@Service
@Transactional(readOnly = true)
public class CxPicService extends CrudService<CxPicDao, CxPic> {

	public CxPic get(String id) {
		return super.get(id);
	}
	
	public List<CxPic> findList(CxPic cxPic) {
		return super.findList(cxPic);
	}
	
	public Page<CxPic> findPage(Page<CxPic> page, CxPic cxPic) {
		return super.findPage(page, cxPic);
	}
	
	@Transactional(readOnly = false)
	public void save(CxPic cxPic) {
		if(cxPic.getPicId() != null && !"".equals(cxPic.getPicId())){
			cxPic.setId(cxPic.getPicId().toString());
		}
		super.save(cxPic);
	}
	
	@Transactional(readOnly = false)
	public void delete(CxPic cxPic) {
		super.delete(cxPic);
	}
	
}