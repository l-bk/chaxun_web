/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pic.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pic.entity.CxPic;

/**
 * 图片表DAO接口
 * @author lbk
 * @version 2018-04-07
 */
@MyBatisDao
public interface CxPicDao extends CrudDao<CxPic> {
	
}