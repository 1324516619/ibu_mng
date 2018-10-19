package com.luolong.service.async;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.luolong.enums.FileAbsolutePathEnum;
import com.luolong.model.ExportDataVo;
import com.luolong.page.Pagination;
import com.luolong.service.ExportDataService;
import com.luolong.util.ExportExcel;
import com.luolong.util.FileOperateUtil;
import com.luolong.util.dialect.SystemPropertiesUtil;

@Service("exportDataServiceAsync")
public class ExportDataServiceAsync {

    private static Logger logger = LoggerFactory.getLogger(ExportDataServiceAsync.class);
    
    @Autowired
    private ExportDataService exportDataService;
    
    /**
     * 导出csv文件
     * @param title 标题
     * @param exportDataVo 导出实体类
     * @param headers 表头信息
     */
    @Async("execDataExportTaskExecutor")
    public void exportReportCsv(String title, ExportDataVo exportDataVo, LinkedHashMap<String, String> headers){
        try{
            // 判断是否已处理
            if(exportDataVo.getReportProcess() != null && exportDataVo.getReportProcess().equals("3")){ return; }
            // 修改处理状态，为处理中
            exportDataVo.setReportProcess("2");
            exportDataService.updateExportData(exportDataVo);

            long startTime = System.currentTimeMillis();
            // 创建excel文件
            String absoluteName = FileOperateUtil.createExportFileAbsoluteName(null, FileAbsolutePathEnum.RESOURCE_PATH, FileOperateUtil.getFileExt(exportDataVo.getFileName()),
                    String.valueOf(exportDataVo.getCreatedId()));// 绝对路径文件夹
            String randomName = UUID.randomUUID().toString() + FileOperateUtil.getFileExtOther(exportDataVo.getFileName());// 随机生成文件名
            File absoluteFile = new File(absoluteName);
            if(!absoluteFile.exists()){
                absoluteFile.mkdirs();
            }
            File file = new File(absoluteFile, randomName);
            if(!file.exists()){
                file.createNewFile();
            }
            String relativeName = FileOperateUtil.createExportFileRelativeName(absoluteName, FileAbsolutePathEnum.RESOURCE_PATH, randomName);// 文件相对路径，保存入数据库
            OutputStream out = new FileOutputStream(file);
            
            //由于poi会产生临时文件，占用硬盘空间，要清除
            String filePath = System.getProperty("java.io.tmpdir");
            deleteTempFile(filePath);
            
            //生成workbook文件头部
            SXSSFWorkbook workbook = ExportExcel.exportExcelHead(title, headers);
            
            //分页写入workbook文件body
            String countSql = "select count(1) as rowCount from ( " + exportDataVo.getSearchSql() + " ) t";
            int count = exportDataService.queryCountByExportSql(countSql);
            
            boolean result = true;
            int offset = 0;
            int rows = 10000;
            while(result){
                String sql = exportDataVo.getSearchSql() + " LIMIT " +  rows+ " offset " + offset;
                List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
                dataList = exportDataService.queryDataByExportSql(sql);
                if(dataList != null && dataList.size() > 0){
                    Pagination page = new Pagination(offset / rows + 1, rows, count);
                    page.setList(dataList);
                    dataList = (List<Map<String, Object>>)page.getList();
                    if(dataList.size() < rows || page.getPageNo() * page.getPageSize() >= count){
                        result = false;
                    }
                    logger.debug("导出数据大小:" + dataList.size());
                    ExportExcel.exportExcelBody(workbook, dataList, headers);
                }else{
                    result = false;
                }
                offset += rows;
            }
            workbook.write(out);
            out.flush();
            out.close();

            long endTime = System.currentTimeMillis();
            logger.debug("生成excel文件完成,用时=" + ((endTime - startTime) / 1000) + "秒");

            // 更新数据状态
            exportDataVo.setFileSize(file.length());
            exportDataVo.setFilePath(relativeName);
            exportDataVo.setReportProcess("3");// 已处理
            exportDataVo.setReportState("1"); // 成功!
            exportDataService.updateExportData(exportDataVo);

        }catch(Exception ex){
            logger.error("导出报表,批次号:" + exportDataVo.getBatchNo() + "异常!", ex);
            exportDataVo.setReportProcess("3");
            exportDataVo.setReportState("3");
            exportDataService.updateExportData(exportDataVo);
        }
    }
    
    /**
     * 删除包含poi-sxssf-sheet并且超过指定时间的文件
     * @param filePath
     */
    private void deleteTempFile(String filePath){
        File folder = new File(filePath);
        File[] files = folder.listFiles();
        if(ArrayUtils.isEmpty(files)){
            for(File file:files){
                if(file.getName().indexOf("poi-sxssf-sheet")!=-1 && ((System.currentTimeMillis()-file.lastModified()) > Long.parseLong(SystemPropertiesUtil.get("tmp_file_overtime")) )){
                    file.delete();
                }
            }
        }
    }
}
