package com.luolong.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.luolong.model.ExportDataVo;
import com.luolong.model.User;
import com.luolong.util.dialect.Page;

public interface ExportDataDao {

	void updateExportData(ExportDataVo exportDataVo);

	void updateDownloadTimes(ExportDataVo exportDataVo);
	
	List<ExportDataVo> selectExportDatalist(@Param("page") Page<ExportDataVo> page,@Param("exportDataVo") ExportDataVo exportDataVo);

	ExportDataVo selectExportData(ExportDataVo exportDataVo);

	void deleteExportData(ExportDataVo exportDataVo);

	int saveExportData(ExportDataVo exportDataVo);

	int queryCountByExportSql(@Param("searchSql") String searchSql);

	List<Map<String, Object>> queryDataByExportSql(@Param("searchSql") String searchSql);

}
