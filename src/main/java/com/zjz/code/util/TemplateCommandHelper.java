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
	public static String tableComments="_{tableComments}";
	public static String columnName="_{columnName}";
	public static String propertyName="_{propertyName}";
	public static String columnComments="_{columnComments}";
	public static String date="_{date}";
	public static String analyse(TableInfo tableInfo,ColumnInfo columnInfo,String line) {
		String newLine = line;
		newLine = StringUtils.replace(newLine, tableName, tableInfo.getTableName());
		newLine = StringUtils.replace(newLine, className, tableInfo.getClassName());
		/*newLine = newLine.replaceAll(tableName, tableInfo.getTableName());
		newLine = newLine.replaceAll(className, tableInfo.getClassName());
		newLine = newLine.replaceAll(tableComments, tableInfo.getComment());
		newLine = newLine.replaceAll(columnName, columnInfo.getColumnName());
		newLine = newLine.replaceAll(propertyName, columnInfo.getPropertyName());
		newLine = newLine.replaceAll(columnComments, columnInfo.getComment());
		newLine = newLine.replaceAll(date, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));*/
		return newLine;
	}
	public static String getTableName() {
		return tableName;
	}
	public static String getClassName() {
		return className;
	}
	public static String getTableComments() {
		return tableComments;
	}
	public static String getColumnName() {
		return columnName;
	}
	public static String getPropertyName() {
		return propertyName;
	}
	public static String getColumnComments() {
		return columnComments;
	}
	public static String getDate() {
		return date;
	}
	
}
