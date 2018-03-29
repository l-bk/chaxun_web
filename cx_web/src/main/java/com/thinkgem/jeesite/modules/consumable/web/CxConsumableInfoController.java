/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.consumable.web;

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
import com.thinkgem.jeesite.modules.consumable.entity.CxConsumableInfo;
import com.thinkgem.jeesite.modules.consumable.service.CxConsumableInfoService;

/**
 * 耗材信息Controller
 * @author lbk
 * @version 2018-03-29
 */
@Controller
@RequestMapping(value = "${adminPath}/consumable/cxConsumableInfo")
public class CxConsumableInfoController extends BaseController {

	@Autowired
	private CxConsumableInfoService cxConsumableInfoService;
	
	@ModelAttribute
	public CxConsumableInfo get(@RequestParam(required=false) String id) {
		CxConsumableInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cxConsumableInfoService.get(id);
		}
		if (entity == null){
			entity = new CxConsumableInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("consumable:cxConsumableInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CxConsumableInfo cxConsumableInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CxConsumableInfo> page = cxConsumableInfoService.findPage(new Page<CxConsumableInfo>(request, response), cxConsumableInfo); 
		model.addAttribute("page", page);
		return "modules/consumable/cxConsumableInfoList";
	}

	@RequiresPermissions("consumable:cxConsumableInfo:view")
	@RequestMapping(value = "form")
	public String form(CxConsumableInfo cxConsumableInfo, Model model) {
		if(StringUtils.isNoneBlank(cxConsumableInfo.getConId())) {
			cxConsumableInfo=cxConsumableInfoService.get(cxConsumableInfo.getConId());
		}
		model.addAttribute("cxConsumableInfo", cxConsumableInfo);
		return "modules/consumable/cxConsumableInfoForm";
	}

	@RequiresPermissions("consumable:cxConsumableInfo:edit")
	@RequestMapping(value = "save")
	public String save(CxConsumableInfo cxConsumableInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cxConsumableInfo)){
			return form(cxConsumableInfo, model);
		}
		cxConsumableInfoService.save(cxConsumableInfo);
		addMessage(redirectAttributes, "保存耗材信息成功");
		return "redirect:"+Global.getAdminPath()+"/consumable/cxConsumableInfo/?repage";
	}
	
	@RequiresPermissions("consumable:cxConsumableInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CxConsumableInfo cxConsumableInfo, RedirectAttributes redirectAttributes) {
		cxConsumableInfoService.delete(cxConsumableInfo);
		addMessage(redirectAttributes, "删除耗材信息成功");
		return "redirect:"+Global.getAdminPath()+"/consumable/cxConsumableInfo/?repage";
	}

}