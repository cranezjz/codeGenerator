package com.zjz.code.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zjz.code.entity.TableInfo;
import com.zjz.code.util.DtoBuildUtil;
import com.zjz.code.util.FileHelper;

@Service
public class DTOService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	 * @param list
	 * @param templatePath
	 */
	public void createDto(List<TableInfo> list, String templatePath,String srcOutPath) {
		try {
			//读实体目标
			List<String> dtoTemplateContent = FileHelper.readDtoTemplateContent(templatePath);
			String dtoPath = DtoBuildUtil.createPathByPackage(dtoTemplateContent,srcOutPath);
			logger.info("创建目录【"+dtoPath+"】成功");
			String entityClassPath = getEntityClassPath(templatePath,srcOutPath);
			Collection<File> listFiles = FileUtils.listFiles(new File(entityClassPath), FileFilterUtils.suffixFileFilter("java"), null);
			for (Iterator<File> iterator = listFiles.iterator(); iterator.hasNext();) {
				File entityFile = iterator.next();
				List<String> lines = FileUtils.readLines(entityFile);
				List<String> newLines = new ArrayList<String>();
				for(String line : lines) {//过滤注解
					if(line!=null && line.trim().startsWith("@")) {
						continue;
					}
					if(line!=null && line.trim().startsWith("package")) {
						newLines.add(DtoBuildUtil.getPackageSentence(dtoTemplateContent));
						continue;
					}
					//修改类名
					String newLine = line;
					String entityClassName = StringUtils.replace(entityFile.getName(), ".java", "");
					newLine = StringUtils.replace(newLine, entityClassName, entityClassName+"Dto");
					newLines.add(newLine);
				}
				String fileName = StringUtils.replace(entityFile.getName(), ".java", "Dto.java");
				logger.info("开始写文件【"+srcOutPath + dtoPath +fileName+"】");
				File dtoFile = new File(srcOutPath + dtoPath +fileName);
				FileHelper.writeFile(dtoFile,newLines);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	private String getEntityClassPath(String templatePath, String srcOutPath) throws IOException {
		List<String> entityTemplateContent = FileHelper.readEntityTemplateContent(templatePath);
		String packageName="";
		for (String line : entityTemplateContent) {
			if(line.startsWith("package")){
				packageName=line.replace("package", "").replace(";","").trim();
				break;
			}
		}
		return srcOutPath+packageName.replace(".", "/");
	}
}
