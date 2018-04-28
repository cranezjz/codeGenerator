package com.zjz.code.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zjz.code.entity.ColumnInfo;
import com.zjz.code.entity.TableInfo;
import com.zjz.code.util.EntityBuildUtil;
import com.zjz.code.util.FileHelper;
import com.zjz.code.util.TemplateCommandHelper;

@Service
public class EntityService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	 * @param list
	 * @param templatePath
	 */
	public void createEntity(List<TableInfo> list, String templatePath,String srcOutPath) {
		try {
			List<String> entityTemplateContent = FileHelper.readEntityTemplateContent(templatePath);
			String entityPath = EntityBuildUtil.createPathByPackage(entityTemplateContent,srcOutPath);
			logger.info("创建目录【"+entityPath+"】成功");
			for (TableInfo tableInfo : list) {
				List<String> entityFileContent = new ArrayList<String>();
				builderContent(tableInfo,entityTemplateContent,entityFileContent);
				logger.info("开始写文件【"+srcOutPath + entityPath + tableInfo.getClassName()+".java"+"】");
				File file = new File(srcOutPath+entityPath+tableInfo.getClassName()+".java");
				FileHelper.writeFile(file,entityFileContent);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	/**
	 * 
	 * @param tableInfo
	 * @param entityTemplateContent
	 * @param entityFileContent
	 */
	private void builderContent(TableInfo tableInfo, List<String> entityTemplateContent,List<String> entityFileContent) {
		logger.info("开始创建类:"+tableInfo.getClassName());
		//构建头部 -- 含class
		for (String line : entityTemplateContent) {
			String newLine = TemplateCommandHelper.analyseTemplate(tableInfo, null, line);
			entityFileContent.add(newLine);
		}
		List<ColumnInfo>cols = tableInfo.getList();
		//生成字段属性
		appendAttribute(cols,entityFileContent);
		//生成构造函数
		appendStructMethod(tableInfo.getClassName(),entityFileContent);
		//生成 set  get 方法
		appendSetAndGetMethods(cols,entityFileContent);
		//生成 toString 方法
		appendToStringMethods(tableInfo.getClassName(),cols,entityFileContent);
		//结束
		entityFileContent.add("}");
	}
	/**
	 * 
	 * @param cols
	 * @param entityFileContent
	 */
	private void appendToStringMethods(String className,List<ColumnInfo> cols, List<String> entityFileContent) {
		entityFileContent.add("");
		entityFileContent.add("\t@Override");
		entityFileContent.add("\tpublic String toString() {");
		StringBuffer sb= new StringBuffer();
		sb.append("\t\treturn \""+className+" [");
		for (int i = 0; i < cols.size(); i++) {
			ColumnInfo columnInfo = cols.get(i);
			String propertyName = columnInfo.getPropertyName();
			sb.append(propertyName+"=\"");
			sb.append("+"+propertyName);
			if(i==cols.size()-1) {
				sb.append("+\"");
			}else {
				sb.append("+\", ");
			}
			if(i%3==2&&i<cols.size()) {
				sb.append("\n\t\t\t");
			}
		}
		sb.append("]\";");
		entityFileContent.add(sb.toString());
		entityFileContent.add("\t}");
	}
	/**
	 * 
	 * @param className
	 * @param entityFileContent
	 */
	private void appendStructMethod(String className,List<String> entityFileContent) {
		entityFileContent.add("");
		entityFileContent.add("\tpublic "+className+"() {");
		entityFileContent.add("\t}");
	}
	/**
	 * 
	 * @param cols
	 * @param entityFileContent
	 */
	private void appendSetAndGetMethods(List<ColumnInfo> cols, List<String> entityFileContent) {
		for (ColumnInfo columnInfo : cols) {
			String setMethodName = "set"+StringUtils.capitalize(columnInfo.getPropertyName());
			String getMethodName = "get"+StringUtils.capitalize(columnInfo.getPropertyName());
			String javaFieldType = columnInfo.getJavaFieldType();
			String propertyName = columnInfo.getPropertyName();
			
			entityFileContent.add("");
			entityFileContent.add("\tpublic "+javaFieldType+" "+getMethodName+"() {");
			entityFileContent.add("\t\treturn this."+propertyName+";");
			entityFileContent.add("\t}");
			
			entityFileContent.add("");
			entityFileContent.add("\tpublic void "+setMethodName+"("+javaFieldType+" "+propertyName+") {");
			entityFileContent.add("\t\tthis."+propertyName+" = "+propertyName+";");
			entityFileContent.add("\t}");
		}
	}
	/**
	 * 
	 * @param cols
	 * @param entityFileContent
	 */
	private void appendAttribute(List<ColumnInfo> cols, List<String> entityFileContent) {
		for (ColumnInfo columnInfo : cols) {
			entityFileContent.add("");
			if(columnInfo.getColumnName().toLowerCase().equals("id")) {
				entityFileContent.add("\t@Id");
				entityFileContent.add("\tprivate String id;");
			}else {
				entityFileContent.add("\t@Column(name=\""+columnInfo.getColumnName()+"\")");
				entityFileContent.add("\tprivate "+columnInfo.getJavaFieldType()+" "+columnInfo.getPropertyName()+";");
				insertImportLine(columnInfo.getImportSentence(),entityFileContent);
			}
		}
		
	}
	/**
	 * 
	 * @param importSentence
	 * @param entityFileContent
	 */
	private void insertImportLine(String importSentence, List<String> entityFileContent) {
		if("".equals(importSentence)) {
			return;
		}
		for (String line : entityFileContent) {
			if(line.contains("importSentence")) {
				return;
			}
		}
		for (int i = 0; i < entityFileContent.size(); i++) {
			if(entityFileContent.get(i).startsWith("import")) {
				entityFileContent.add(i, importSentence);
				break;
			}
		}
	}
}
