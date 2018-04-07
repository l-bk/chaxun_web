/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pic.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 图片表Entity
 * @author lbk
 * @version 2018-04-07
 */
public class CxPic extends DataEntity<CxPic> {
	
	private static final long serialVersionUID = 1L;
	private Integer picId;		// pic_id
	private String picName;		// 图片名称
	private String picPath;		// 图片路径
	
	public CxPic() {
		super();
	}

	public CxPic(String id){
		super(id);
	}
	
	public Integer getPicId() {
		return picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}
	
	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	
}