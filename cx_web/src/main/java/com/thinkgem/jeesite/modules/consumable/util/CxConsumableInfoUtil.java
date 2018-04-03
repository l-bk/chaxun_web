package com.thinkgem.jeesite.modules.consumable.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class CxConsumableInfoUtil {
	 public static Workbook getWorkBook(MultipartFile file) throws IOException {  
	        //获得文件名  
	        String fileName = file.getOriginalFilename();  
	        //创建Workbook工作薄对象，表示整个excel  
	        Workbook workbook = null;  
	       
	            //获取excel文件的io流  
	            InputStream is = file.getInputStream();  
	            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象  
	            if(fileName.endsWith("xls")){  
	                //2003  
	                workbook = new HSSFWorkbook(is);  
	            }else if(fileName.endsWith("xlsx")){  
	                //2007  
	                workbook = new XSSFWorkbook(is);  
	            }  
	       
	        return workbook;  
	    }  
}
