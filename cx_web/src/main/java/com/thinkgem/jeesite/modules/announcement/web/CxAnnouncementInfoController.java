/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.announcement.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.announcement.entity.CxAnnouncementInfo;
import com.thinkgem.jeesite.modules.announcement.service.CxAnnouncementInfoService;

/**
 * 公告信息Controller
 * @author lbk
 * @version 2018-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/announcement/cxAnnouncementInfo")
public class CxAnnouncementInfoController extends BaseController {

	@Autowired
	private CxAnnouncementInfoService cxAnnouncementInfoService;
	
	@ModelAttribute
	public CxAnnouncementInfo get(@RequestParam(required=false) String id) {
		CxAnnouncementInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cxAnnouncementInfoService.get(id);
		}
		if (entity == null){
			entity = new CxAnnouncementInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("announcement:cxAnnouncementInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CxAnnouncementInfo cxAnnouncementInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CxAnnouncementInfo> page = cxAnnouncementInfoService.findPage(new Page<CxAnnouncementInfo>(request, response), cxAnnouncementInfo); 
		model.addAttribute("page", page);
		return "modules/announcement/cxAnnouncementInfoList";
	}

	@RequiresPermissions("announcement:cxAnnouncementInfo:view")
	@RequestMapping(value = "form")
	public String form(CxAnnouncementInfo cxAnnouncementInfo, Model model) {
		if(StringUtils.isNotBlank(cxAnnouncementInfo.getAnId())) {
			cxAnnouncementInfo = cxAnnouncementInfoService.get(cxAnnouncementInfo.getAnId());
		}
		model.addAttribute("cxAnnouncementInfo", cxAnnouncementInfo);
		return "modules/announcement/cxAnnouncementInfoForm";
	}

	@RequiresPermissions("announcement:cxAnnouncementInfo:edit")
	@RequestMapping(value = "save")
	public String save(CxAnnouncementInfo cxAnnouncementInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cxAnnouncementInfo)){
			return form(cxAnnouncementInfo, model);
		}
		cxAnnouncementInfoService.save(cxAnnouncementInfo);
		addMessage(redirectAttributes, "保存公告信息成功");
		return "redirect:"+Global.getAdminPath()+"/announcement/cxAnnouncementInfo/?repage";
	}
	
	@RequiresPermissions("announcement:cxAnnouncementInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CxAnnouncementInfo cxAnnouncementInfo, RedirectAttributes redirectAttributes) {
		cxAnnouncementInfoService.delete(cxAnnouncementInfo);
		addMessage(redirectAttributes, "删除公告信息成功");
		return "redirect:"+Global.getAdminPath()+"/announcement/cxAnnouncementInfo/?repage";
	}

}