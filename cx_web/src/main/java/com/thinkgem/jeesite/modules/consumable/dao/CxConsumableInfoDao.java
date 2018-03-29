/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.consumable.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.consumable.entity.CxConsumableInfo;

/**
 * 耗材信息DAO接口
 * @author lbk
 * @version 2018-03-29
 */
@MyBatisDao
public interface CxConsumableInfoDao extends CrudDao<CxConsumableInfo> {
	
}