package com.zjz.code.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileHepler {
	public static List<String> readEntityTemplateContent(String templatePath) throws IOException{
		File file = new File(templatePath+"EntityTemplate.java");
		return FileUtils.readLines(file, "UTF-8");
	}

	public static void writeFile(File file, List<String> entityFileContent) throws IOException {
		FileUtils.writeLines(file, "UTF-8", entityFileContent);
	}
	/**
	 * 递归创建文件夹
	 * @param file
	 */
	public static void mkDir(File file) {  
        if (file.getParentFile().exists()) {  
            file.mkdir();  
        } else {  
            mkDir(file.getParentFile());  
            file.mkdir();    
        }  
    } 
	public static void main(String[] args) {
		mkDir(new File("C:\\tmp\\code\\out\\com\\zjz\\meeting\\entity"));
	}
}
