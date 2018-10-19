package com.luolong.service;

import java.util.List;
import java.util.Map;

import com.luolong.model.ExportDataVo;
import com.luolong.page.Pagination;

public interface ExportDataService {

    void updateExportData(ExportDataVo exportDataVo);
    
    void updateDownloadTimes(ExportDataVo exportDataVo);
    
    Pagination getPage(ExportDataVo exportDataVo, int currentPage, int pageSize);

    ExportDataVo findExportDataTmp(ExportDataVo exportDataVo);

    List<Map<String, Object>> findDataBySql(String searchSql) throws Exception;

    int findCountBySql(String countSql);

    void batchRemoveExportdata(String[] splitReportids);

    ExportDataVo findExportdataById(String reportSelectId);
    
    public int queryCountByExportSql(String searchSql) throws Exception ;
    
    public List<Map<String, Object>> queryDataByExportSql(String searchSql) throws Exception ;
   

}
