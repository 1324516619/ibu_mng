package com.luolong.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExportExcel<T> {

    /**
     * 用poi方式
     * 生成book文件头部 (高性能)
     * @param title  标题
     * @param headers 表头数据
     */
    @SuppressWarnings("rawtypes")
    public static SXSSFWorkbook exportExcelHead(String title, LinkedHashMap<String, String> headers){
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        // 生成一个表格
        Sheet sheet = workbook.createSheet(title);
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_DASHED);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成一个字体
        Font font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short)12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        // 生成一个样式
        CellStyle style3 = workbook.createCellStyle();
        // 设置这些样式
        style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style3.setBorderTop(HSSFCellStyle.BORDER_DASHED);
        style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成一个字体
        Font fon3 = workbook.createFont();
        fon3.setColor(HSSFColor.BLACK.index);
        fon3.setFontHeightInPoints((short)12);
        fon3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style3.setFont(fon3);

        // 声明一个画图的顶级管理器
        Drawing patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        patriarch.createAnchor(0, 0, 0, 0, (short)4, 2, (short)6, 5);

        // 产生表格中间行
        Row center = sheet.createRow(0);
        center.setHeightInPoints(40);
        int k = 0;
        for(Iterator propertyIterator = headers.entrySet().iterator(); propertyIterator.hasNext();){
            Entry propertyEntry = (Entry)propertyIterator.next();
            Cell cell = center.createCell(k);
            cell.setCellStyle(style3);
            HSSFRichTextString text = new HSSFRichTextString(title);
            cell.setCellValue(text);
            sheet.setColumnWidth(k, 18 * 256);
            k++;
        }
        // 合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.size() - 1));
        // 产生表格标题行
        Row row = sheet.createRow(1);
        row.setHeightInPoints(25);
        int i = 0;
        for(Iterator propertyIterator = headers.entrySet().iterator(); propertyIterator.hasNext();){
            Entry propertyEntry = (Entry)propertyIterator.next();
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString((String)propertyEntry.getValue());
            cell.setCellValue(text);
            i++;
        }

        return workbook;
    }

    /**
     * 用poi方式
     * 生成book文件头部 (高性能) 针对客户渠道月统计 详情I 详情II 定制
     * @param title  标题
     * @param headers 表头数据
     * @param type (1:详情I  2：详情II)
     */
    @SuppressWarnings("rawtypes")
    public static SXSSFWorkbook exportExcelOtherHead(String title, LinkedHashMap<String, String> headers, int type, Map<String, Object> map){
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        // 生成一个表格
        Sheet sheet = workbook.createSheet(title);
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_DASHED);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成一个字体
        Font font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short)12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        CellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        Font font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font2.setColor(HSSFColor.BLUE.index);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 生成一个样式
        CellStyle style3 = workbook.createCellStyle();
        // 设置这些样式
        style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style3.setBorderTop(HSSFCellStyle.BORDER_DASHED);
        style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成一个字体
        Font fon3 = workbook.createFont();
        fon3.setColor(HSSFColor.BLACK.index);
        fon3.setFontHeightInPoints((short)12);
        fon3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style3.setFont(fon3);

        // 声明一个画图的顶级管理器
        Drawing patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        patriarch.createAnchor(0, 0, 0, 0, (short)4, 2, (short)6, 5);

        // 产生表格中间行
        Row center = sheet.createRow(0);
        center.setHeightInPoints(40);
        int k = 0;
        for(Iterator propertyIterator = headers.entrySet().iterator(); propertyIterator.hasNext();){
            Entry propertyEntry = (Entry)propertyIterator.next();
            Cell cell = center.createCell(k);
            cell.setCellStyle(style3);
            HSSFRichTextString text = new HSSFRichTextString(title);
            cell.setCellValue(text);
            sheet.setColumnWidth(k, 18 * 256);
            k++;
        }
        // 合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.size() - 1));

        // 产生表格标题行
        Row row1 = sheet.createRow(1);
        row1.setHeightInPoints(35);
        int m = 0;
        for(Iterator propertyIterator = headers.entrySet().iterator(); propertyIterator.hasNext();){
            Entry propertyEntry = (Entry)propertyIterator.next();
            Cell cell1 = row1.createCell(m);
            cell1.setCellStyle(style3);
            HSSFRichTextString text = null;
            if(type == 1){
                if(m < 5){
                    text = new HSSFRichTextString("基本信息");
                }
                if(m >= 5 && m < 8){
                    text = new HSSFRichTextString("总金额");
                }
                if(m >= 8 && m < 11){
                    text = new HSSFRichTextString("主流通道");
                }
                if(m >= 11 && m < 14){
                    text = new HSSFRichTextString("分流通道");
                }
            }
            if(type == 2){
                if(m < 7){
                    text = new HSSFRichTextString("基本信息");
                }
                if(m >= 7 && m < 10){
                    text = new HSSFRichTextString("总金额");
                }
                if(m >= 10 && m < 13){
                    text = new HSSFRichTextString("主流通道");
                }
                if(m >= 13 && m < 16){
                    text = new HSSFRichTextString("分流通道");
                }
            }

            cell1.setCellValue(text);
            m++;
        }
        if(type == 1){
            // 合并单元格
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 7));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 8, 10));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 11, 13));
        }
        if(type == 2){
            // 合并单元格
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 6));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 7, 9));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 10, 12));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 13, 15));
        }

        // 产生表格标题行
        Row row = sheet.createRow(2);
        row.setHeightInPoints(25);
        int i = 0;
        for(Iterator propertyIterator = headers.entrySet().iterator(); propertyIterator.hasNext();){
            Entry propertyEntry = (Entry)propertyIterator.next();
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString((String)propertyEntry.getValue());
            cell.setCellValue(text);
            i++;
        }
        // 写入文件内容
        int index = 3;
        row = sheet.createRow(index);
        row.setHeightInPoints(25);
        int j = 0;
        for(Iterator propertyIterator = headers.entrySet().iterator(); propertyIterator.hasNext();){
            Entry propertyEntry = (Entry)propertyIterator.next();
            Cell cell = row.createCell(j);
            cell.setCellStyle(style2);
            try{
                String textValue = (String)BeanUtils.getProperty(map, (String)propertyEntry.getKey());
                // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                if(textValue != null){
                    Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                    Matcher matcher = p.matcher(textValue);
                    if(matcher.matches()){
                        // 是数字当作double处理
                        cell.setCellValue(Double.parseDouble(textValue));
                    }else{
                        cell.setCellValue(textValue);
                    }
                }else{
                    cell.setCellValue("");
                }
            }catch(Exception e){}

            j++;
        }

        return workbook;
    }

    /**
     * 用poi方式
     * 生成book文件body (高性能)
     * @param workbook SXSSFWorkbook对象
     * @param dataset  数据集合
     * @param headers  标题map
     */
    public static void exportExcelBody(SXSSFWorkbook workbook, List<Map<String, Object>> dataset, LinkedHashMap<String, String> headers){
        // 生成并设置另一个样式
        CellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        Font font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font2.setColor(HSSFColor.BLUE.index);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);

        int index = sheet.getLastRowNum() + 1;// 在现有行号后追加数据

        for(int i = 0; i < dataset.size(); i++){
            row = sheet.createRow(index + i);
            row.setHeightInPoints(25);
            Map<String, Object> map = dataset.get(i);
            int j = 0;
            for(Iterator propertyIterator = headers.entrySet().iterator(); propertyIterator.hasNext();){
                Entry propertyEntry = (Entry)propertyIterator.next();
                Cell cell = row.createCell(j);
                cell.setCellStyle(style2);
                try{
                    String textValue = (String)BeanUtils.getProperty(map, (String)propertyEntry.getKey());
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    if(textValue != null){
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                        Matcher matcher = p.matcher(textValue);
                        if(matcher.matches()){
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        }else{
                            cell.setCellValue(textValue);

                        }
                    }else{
                        cell.setCellValue("");
                    }
                }catch(Exception e){}
                j++;
            }
        }
    }


}
