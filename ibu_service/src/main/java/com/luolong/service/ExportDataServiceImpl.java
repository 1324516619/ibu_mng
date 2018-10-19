package com.luolong.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luolong.dao.ExportDataDao;
import com.luolong.model.ExportDataVo;
import com.luolong.page.Pagination;
import com.luolong.util.dialect.JDBCUtil;
import com.luolong.util.dialect.Page;

@Service("exportDataService")
public class ExportDataServiceImpl implements ExportDataService {

    @Autowired
    private ExportDataDao exportDataDao;

    @Override
    public void updateExportData(ExportDataVo exportDataVo){
        exportDataDao.updateExportData(exportDataVo);
    }
    
    @Override
    public void updateDownloadTimes(ExportDataVo exportDataVo){
        exportDataDao.updateDownloadTimes(exportDataVo);
    }
    
    @Override
    public Pagination getPage(ExportDataVo exportDataVo, int currentPage,
    		int pageSize) {
    	Page<ExportDataVo> page = new Page<ExportDataVo>(currentPage, pageSize);
        List<ExportDataVo> list = exportDataDao.selectExportDatalist(page, exportDataVo);
        Pagination p = new Pagination(currentPage, pageSize, page.getTotalCount());
        p.setList(list);
        return p;
    }

    @Override
    public ExportDataVo findExportDataTmp(ExportDataVo exportDataVo){
        return (ExportDataVo)exportDataDao.selectExportData(exportDataVo);
    }

   /* @Override
    public void deleteExportDataTmp(ExportDataVo exportDataVo){
        exportDataDao.deleteExportData(exportDataVo);
    }*/

    @Override
    public List<Map<String, Object>> findDataBySql(String searchSql) throws Exception{
        return JDBCUtil.findDataBySql(searchSql);
    }

    @Override
    public int findCountBySql(String countSql){
        return JDBCUtil.findCountBySql(countSql);
    }

    @Override
    public void batchRemoveExportdata(String[] splitReportids){
        if(splitReportids!=null && splitReportids.length>0){
            ExportDataVo vo = null;
            for(int i=0; i<splitReportids.length; i++){
                vo = new ExportDataVo();
                //vo.setReportId(Long.valueOf(splitReportids[i]));
                vo.setBatchNo(splitReportids[i]);
                exportDataDao.deleteExportData(vo);
            }
        }
    }

    @Override
    public ExportDataVo findExportdataById(String reportSelectId){
        ExportDataVo vo = new ExportDataVo();
        vo.setBatchNo(reportSelectId);
        return exportDataDao.selectExportData(vo);
    }

	@Override
	public List<Map<String, Object>> queryDataByExportSql(String searchSql)throws Exception {
		 
		return exportDataDao.queryDataByExportSql(searchSql);
	}

	@Override
	public int queryCountByExportSql(String searchSql)throws Exception {
		
		return exportDataDao.queryCountByExportSql(searchSql);
	}
}
