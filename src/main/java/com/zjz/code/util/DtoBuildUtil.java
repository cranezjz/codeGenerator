package com.zjz.code.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DtoBuildUtil {

	public static String createPathByPackage(List<String> dtoTemplateContent,String outPath) throws IOException {
		String packageName="";
		for (String line : dtoTemplateContent) {
			if(line.startsWith("package")){
				packageName=line.replace("package", "").replace(";","").trim();
				break;
			}
		}
		String path = outPath+packageName.replace(".", "/");
		FileHelper.mkDir(new File(path));
		return packageName.replace(".", "/")+"/";
	}
	public static String getPackageSentence(List<String> entityTemplateContent) {
		for (String line : entityTemplateContent) {
			if(line.startsWith("package")){
				return line;
			}
		}
		return "";
	}

}
