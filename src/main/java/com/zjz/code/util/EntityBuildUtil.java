package com.zjz.code.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.zjz.code.entity.TableInfo;

public class EntityBuildUtil {

	public static void createEntity(List<TableInfo> list) {
		
	}
	public static String createPathByPackage(List<String> entityTemplateContent,String outPath) throws IOException {
		String packageName="";
		for (String line : entityTemplateContent) {
			if(line.startsWith("package")){
				packageName=line.replace("package", "").replace(";","").trim();
				break;
			}
		}
		String path = outPath+packageName.replace(".", "/");
		FileHelper.mkDir(new File(path));
		return packageName.replace(".", "/")+"/";
	}

}
