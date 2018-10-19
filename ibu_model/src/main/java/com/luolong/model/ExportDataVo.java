package com.luolong.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 导出报表
 */
public class ExportDataVo extends BaseVo implements Serializable{
	
	private static final long serialVersionUID = 8441709090511320520L;
	//private Long reportId;        //主键
	private String batchNo;       //批号
	private String fileName;      //文件名
	private String filePath;      //文件路径
	private Long fileSize;        //文件大小
	//private String reportType;    //报表类型
	private String reportProcess; //处理进度
	private String reportState;   //处理结果
	private String templatePath;  //模板路径
	private String searchSql;     //处理SQL
	private Long createdId;       //创建人ID
	private String createdName;   //创建人
	private Date createdDate;   //创建时间
	private Date updatedDate;   //更新时间
	private int downloadTimes;  //下载次数

	
	//public Long getReportId() {
	//	return reportId;
	//}
	//public void setReportId(Long reportId) {
	//	this.reportId = reportId;
	//}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	//public String getReportType() {
	//	return reportType;
	//}
	//public void setReportType(String reportType) {
	//	this.reportType = reportType;
	//}
	public String getReportProcess() {
		return reportProcess;
	}
	public void setReportProcess(String reportProcess) {
		this.reportProcess = reportProcess;
	}
	public String getReportState() {
		return reportState;
	}
	public void setReportState(String reportState) {
		this.reportState = reportState;
	}
	public Long getCreatedId() {
		return createdId;
	}
	public void setCreatedId(Long createdId) {
		this.createdId = createdId;
	}
	public String getCreatedName() {
		return createdName;
	}
	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}
	public String getTemplatePath() {
		return templatePath;
	}
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
	public String getSearchSql() {
		return searchSql;
	}
	public void setSearchSql(String searchSql) {
		this.searchSql = searchSql;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
    public int getDownloadTimes(){
        return downloadTimes;
    }
    
    public void setDownloadTimes(int downloadTimes){
        this.downloadTimes = downloadTimes;
    }
    
    public Date getUpdatedDate(){
        return updatedDate;
    }
    
    public void setUpdatedDate(Date updatedDate){
        this.updatedDate = updatedDate;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((batchNo == null) ? 0 : batchNo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ExportDataVo other = (ExportDataVo) obj;
		if (batchNo == null) {
			if (other.batchNo != null)
				return false;
		} else if (!batchNo.equals(other.batchNo))
			return false;
		return true;
	}
	
}
