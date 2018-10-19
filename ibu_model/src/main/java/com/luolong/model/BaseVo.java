package com.luolong.model;

public class BaseVo {
	
	private Integer CreateId;//创建人
	private String createName;//创建人姓名
	private String startTime;
	private String endTime;
	public Integer getCreateId() {
		return CreateId;
	}
	public void setCreateId(Integer createId) {
		CreateId = createId;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
	

}
