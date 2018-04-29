package com.zjz.code.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileHelper {
	public static List<String> readCommonTemplateContent(String templatePath,String templateName) throws IOException {
		File file = new File(templatePath+templateName);
		return FileUtils.readLines(file, "UTF-8");
	}
/*	public static List<String> readTemplateContent(String templatePath,String templateFileName) throws IOException{
		File file = new File(templatePath+templateFileName);
		return FileUtils.readLines(file, "UTF-8");
	}*/
	/*public static List<String> readDtoTemplateContent(String templatePath) throws IOException{
		File file = new File(templatePath+"DtoTemplate.java");
		return FileUtils.readLines(file, "UTF-8");
	}*/
	public static void writeFile(File file, List<String> entityFileContent) throws IOException {
		FileUtils.writeLines(file, "UTF-8", entityFileContent);
	}
	/**
	 * @param file
	 * @throws IOException 
	 */
	public static void mkDir(File file) throws IOException {  
		FileUtils.forceMkdir(file);
    } 
	public static void deleteDir(File file) {  
		if(file.exists()) {
			try {
				FileUtils.forceDelete(file);
			} catch (IOException e) {
			}
		}
    } 
	public static void main(String[] args) {
		try {
			FileUtils.forceDelete(new File("C:\\tmp\\code\\out\\com"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
