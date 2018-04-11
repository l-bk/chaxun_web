/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.announcement.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.announcement.entity.CxAnnouncementInfo;

/**
 * 公告信息DAO接口
 * @author lbk
 * @version 2018-04-11
 */
@MyBatisDao
public interface CxAnnouncementInfoDao extends CrudDao<CxAnnouncementInfo> {
	public int updateStatus();
}