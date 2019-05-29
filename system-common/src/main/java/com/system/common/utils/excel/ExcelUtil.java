package com.system.common.utils.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @Author: 丰涌
 * @Date: 2018/10/26 21:37
 * @Description: excel导出通用类
 */
public class ExcelUtil {

    public static void exportExcel(List<?> list, String title, String sheetName,
                                   Class<?> pojoClass, String fileName,
                                   boolean isCreateHeader, HttpServletResponse response){
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);

    }
    public static void exportExcel(List<?> list, String title,
                                   String sheetName, Class<?> pojoClass,
                                   String fileName, HttpServletResponse response){
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    public static void exportExcel(List<?> list, String title,
                                   String sheetName, Class<?> pojoClass,
                                   String fileName, HttpServletResponse response, HttpServletRequest request) {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName),request);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response,
                                      ExportParams exportParams, HttpServletRequest request) {
        exportParams.setStyle(ExcelStyleUtil.class);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook,request);
        }
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook, HttpServletRequest request) {
        try {
            String agent = request.getHeader("User-Agent").toLowerCase();
            if(agent.contains("msie") || agent.contains("trident") || agent.contains("edge")){
                fileName = URLEncoder.encode(fileName,"UTF-8");
            }else{
                fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1"); //解决中文乱码问题
            }
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response){
        defaultExport(list, fileName, response);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass,
                                      String fileName, HttpServletResponse response,
                                      ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,pojoClass,list);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null){
            downLoadExcel(fileName, response, workbook);
        }
    }

    public static <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new RuntimeException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows,
                                          Integer headerRows, Class<T> pojoClass){
        if (file == null){
            return null;
        }
        String fileName = file.getOriginalFilename();
        if(!(fileName.toLowerCase().endsWith(".xls") || fileName.toLowerCase().endsWith(".xlsx"))){
            throw new RuntimeException("文件格式错误,请导入excel文件");
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        Field[] fields = pojoClass.getDeclaredFields();
        List<String> importFields = new ArrayList<String>();
        for (Field field : fields) {
            Excel excel = field.getAnnotation(Excel.class);
            if (excel != null && excel.isImportField().equals("true")) {
                importFields.add(excel.name());
            }
        }
        if(importFields.size()==0){
            params.setImportFields(null);
        }else{
            params.setImportFields(importFields.toArray(new String[importFields.size()]));
            params.setNeedCheckOrder(true);
        }
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new RuntimeException("excel文件不能为空");
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }
}
