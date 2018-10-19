package com.luolong.model;

import java.io.Serializable;

/**
 * 权限
 * 
 * @author 罗龙
 *
 */
public class Permission implements Serializable {
	private static final long serialVersionUID = -346485361060987033L;
	private int id;// 主键
	private String modelName;// 模块名称
	private String permiDesc;// 权限描述
	private String permiValue;// 权限标识
	private int sortOrder;// 权限顺序
	private String pageId;// t_system_menu.page_id关联
	private String requestUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getPermiDesc() {
		return permiDesc;
	}

	public void setPermiDesc(String permiDesc) {
		this.permiDesc = permiDesc;
	}

	public String getPermiValue() {
		return permiValue;
	}

	public void setPermiValue(String permiValue) {
		this.permiValue = permiValue;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

}
