package com.luolong.enums;
/**
 */
public enum FileAbsolutePathEnum{
	/** 图片*/
	IMAGE_PATH("image_path"),
	/** 头像，缩略图*/
	ICON_PATH("icon_path"),
	/** 资源 */
	RESOURCE_PATH("resource_path"),
	/** cache_address */
	CACHE_ADDRESS("cache_address"),
	/** 软件升级包 */
	SOFT_PACK("soft_pack"),
	/** 文件名 */
	IBU_FILENAME("ibu_filename"),
	/** 批量充值上传文件保存路径 */
	BATCH_RECHARGE_UPLOAD_PATH("batch_recharge_upload_path"),
	/** 对账文件上传保存路径 */
	RECONCILIATION_UPLOAD_PATH("reconciliation_upload_path"),
	/** 对账结果文件保存路径 */
	RECONCILIATION_RESULT_PATH("reconciliation_result_path");
	
	private String value;
	private FileAbsolutePathEnum(String value) {
		this.value = value;
	}
	public String getValue(){
		return this.value;
	}
}
