package com.zjz.code.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.zjz.code.entity.TableInfo;

public class CommonBuildUtil {

	public static void createEntity(List<TableInfo> list) {
		
	}
	public static String createPathByPackage(List<String> daoTemplateContent,String outPath) throws IOException {
		String packageName="";
		for (String line : daoTemplateContent) {
			if(line.startsWith("package")){
				packageName=line.replace("package", "").replace(";","").trim();
				break;
			}
		}
		String path = outPath+packageName.replace(".", "/");
		FileHelper.mkDir(new File(path));
		return packageName.replace(".", "/")+"/";
	}
	public static String getPackageName(String templatePath,String templateFileName) throws IOException {
		List<String> templateContent = FileHelper.readCommonTemplateContent(templatePath,templateFileName);
		for (String line : templateContent) {
			if(line.startsWith("package")){
				return line.replace("package", "").replace(";","").trim();
			}
		}
		return "";
	}
	public static void initTemplateCommandValue(String templatePath,String entityTemplateFileName,
			String dtoTemplateFileName,String daoTemplateFileName,String serviceTemplateFileName) throws IOException {
		TemplateCommandHelper.entityPackageNameValue = getPackageName(templatePath, entityTemplateFileName);
		TemplateCommandHelper.dtoPackageNameValue = getPackageName(templatePath, dtoTemplateFileName);
		TemplateCommandHelper.daoPackageNameValue = getPackageName(templatePath, daoTemplateFileName);
		TemplateCommandHelper.servicePackageNameValue = getPackageName(templatePath, serviceTemplateFileName);
		System.out.println(TemplateCommandHelper.servicePackageNameValue);
	}
}
