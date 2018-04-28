package com.zjz.code.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.zjz.code.entity.ColumnInfo;
import com.zjz.code.entity.TableInfo;

/**
 * 
 * <p>Title: TemplateConstant.java</p>  
 * <p>Description: </p>  
 * @author zhaojz
 * @date 2018年4月28日
 */
public class TemplateCommandHelper {
	public static String tableName="_{tableName}";
	public static String className="_{className}";
	public static String objectName="_{objectName}";
	public static String tableComments="_{tableComments}";
	public static String columnName="_{columnName}";
	public static String propertyName="_{propertyName}";
	public static String columnComments="_{columnComments}";
	public static String date="_{date}";
	public static String analyseTemplate(TableInfo tableInfo,ColumnInfo columnInfo,String line) {
		String newLine = line;
		newLine = StringUtils.replace(newLine, tableName, tableInfo.getTableName());
		newLine = StringUtils.replace(newLine, className, tableInfo.getClassName());
		newLine = StringUtils.replace(newLine, objectName, tableInfo.getObjectName());
		newLine = StringUtils.replace(newLine, tableComments, tableInfo.getComment());
		newLine = StringUtils.replace(newLine, date, getDate());
		return newLine;
	}
	public static String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
}
