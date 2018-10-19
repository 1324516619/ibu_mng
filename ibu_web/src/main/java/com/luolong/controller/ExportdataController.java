package com.luolong.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.luolong.enums.FileAbsolutePathEnum;
import com.luolong.model.ExportDataVo;
import com.luolong.page.Pagination;
import com.luolong.service.ExportDataService;
import com.luolong.util.FileOperateUtil;
import com.luolong.util.SafetyUtil;

@Controller
@RequestMapping("/exportdata")
public class ExportdataController extends BaseController {
	
	@Resource
	private ExportDataService exportDataService;
	
	// 日志记录器
    public final Logger log = Logger.getLogger(this.getClass());
    
    @RequestMapping("/list.do")
	public ModelAndView userList(ExportDataVo exportDataVo) {
    	exportDataVo.setCreatedId(getSessionUser().getUserId().longValue());
		ModelAndView mav = new ModelAndView("exportData/exportDataList");
		Pagination page = exportDataService.getPage(exportDataVo, getCurrentPage(), getPageSize());
		setTotalSize(Long.valueOf(page.getTotalCount()).intValue());
		mav.addObject("page", page);
		mav.addObject("totalSize",getTotalSize());
		mav.addObject("pageSize", getPageSize());
		return mav;
	}
    
    /**
	 * 下载报表文件
	 * @return
	 */
    @RequestMapping("/downExportdata.do")
    @ResponseBody
	public void downExportdata(String reportSelectId)throws Exception{
		getResponse().setCharacterEncoding("gb2312");
		getResponse().setContentType(FileOperateUtil.getContentType(".xls"));
		
		ExportDataVo exportDataVo = exportDataService.findExportdataById(reportSelectId);
		if(null == exportDataVo){}
			//return null;
		String fileName = new String(exportDataVo.getFileName().getBytes("GB2312"), "ISO_8859_1");  
		getResponse().setContentType("application/vnd.ms-excel");
		getResponse().setHeader("Content-Disposition", "attachment; filename=" + SafetyUtil.dealHeaderValue(fileName));
		try {
			FileOperateUtil.readFile(getResponse().getOutputStream(), FileAbsolutePathEnum.RESOURCE_PATH, exportDataVo.getFilePath());
			//修改下载次数
			ExportDataVo vo = new ExportDataVo();
			vo.setBatchNo(reportSelectId);
			exportDataService.updateDownloadTimes(vo);
		} catch (IOException e) {
			log.error("下载报表异常!", e);
		} 
		//return null;
	}

    
}  