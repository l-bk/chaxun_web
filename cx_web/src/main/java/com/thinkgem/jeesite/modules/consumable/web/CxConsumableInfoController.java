/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.consumable.web;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.consumable.entity.CxConsumableInfo;
import com.thinkgem.jeesite.modules.consumable.service.CxConsumableInfoService;
import com.thinkgem.jeesite.modules.consumable.util.CxConsumableInfoUtil;

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
	@RequestMapping(value = {"goBatchAdd", ""})
	public String goBatchAdd(CxConsumableInfo cxConsumableInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/consumable/cxConsumableInfoBatchAdd";
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
		return "redirect:"+Global.getAdminPath()+"/consumable/cxConsumableInfo/list?repage";
	}

	@RequiresPermissions("consumable:cxConsumableInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CxConsumableInfo cxConsumableInfo, RedirectAttributes redirectAttributes) {
		cxConsumableInfoService.delete(cxConsumableInfo);
		addMessage(redirectAttributes, "删除耗材信息成功");
		return "redirect:"+Global.getAdminPath()+"/consumable/cxConsumableInfo/list?repage";
	}

	@RequiresPermissions("consumable:cxConsumableInfo:edit")
	@RequestMapping(value = "batchAdd", method = RequestMethod.POST)
	public String batchAdd(MultipartFile file,Model model,RedirectAttributes redirectAttributes, HttpServletRequest req,
			HttpServletResponse res) {
		Workbook book;
		try {
			book = CxConsumableInfoUtil.getWorkBook(file);
			Sheet sheet = book.getSheetAt(0);// 获得第一个工作表对象

			int rows = sheet.getPhysicalNumberOfRows();
			Row row = sheet.getRow(0);//获取第一行
			int columns = row.getPhysicalNumberOfCells();

			// 循环除了第一行的所有行
			for (int i =1; i < rows; i++) {
				boolean is = true;
				CxConsumableInfo info =new CxConsumableInfo();
				for (int j = 0; j <= columns; j++) {
					if (null != sheet.getRow(i).getCell(j)) {// 读取某行某列不为空
						sheet.getRow(i).getCell(j).setCellType(Cell.CELL_TYPE_STRING);// 设置读取的类型为String
						if(j == 0){
							info.setConBrand(sheet.getRow(i).getCell(j).toString());
						}else if(j == 1) {
							info.setConCompatible(sheet.getRow(i).getCell(j).toString());
						}else if(j == 2) {
							info.setConCode(sheet.getRow(i).getCell(j).toString());
						}else if(j == 3) {
							info.setConModel(sheet.getRow(i).getCell(j).toString());
						}else if(j == 4) {
							info.setConDetails(sheet.getRow(i).getCell(j).toString());
						}else if(j == 5) {
							info.setConNum(sheet.getRow(i).getCell(j).toString());
						}else if (j == 6) {
							if(null != sheet.getRow(i).getCell(j).toString() && !"".equals(sheet.getRow(i).getCell(j).toString())) {
								info.setConReferencePrice(new BigDecimal(sheet.getRow(i).getCell(j).toString()));
							}		
						}
				}
			}
			cxConsumableInfoService.save(info);
		}
	} catch (Exception e) {
		e.printStackTrace();

	}
	return "redirect:"+Global.getAdminPath()+"/consumable/cxConsumableInfo/list?repage";

}

/*
 * 下载execl文档
 */

@RequiresPermissions("consumable:cxConsumableInfo:edit")
@RequestMapping(value = "uploadExecl")
public void uploadExecl(HttpServletRequest request, HttpServletResponse response,
		Model model) throws Exception {

	String filename = request.getParameter("filename");  
	System.out.println(filename);  

	//设置文件MIME类型  
	response.setContentType(request.getSession().getServletContext().getMimeType(filename));  
	//设置Content-Disposition  
	response.setHeader("Content-Disposition", "attachment;filename="+filename);  
	//读取目标文件，通过response将目标文件写到客户端  
	//获取目标文件的绝对路径  
	String fullFileName = request.getSession().getServletContext().getRealPath("/userfiles/excel/" + filename);  
	//System.out.println(fullFileName);  
	//读取文件  
	InputStream in = new FileInputStream(fullFileName);  
	OutputStream out = response.getOutputStream();  

	//写文件  
	int b;  
	while((b=in.read())!= -1)  
	{  
		out.write(b);
	}  

	in.close();  
	out.close();  


}
}