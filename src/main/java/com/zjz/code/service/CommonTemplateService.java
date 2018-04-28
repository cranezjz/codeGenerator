package com.zjz.code.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zjz.code.entity.TableInfo;
import com.zjz.code.util.DaoBuildUtil;
import com.zjz.code.util.FileHelper;
import com.zjz.code.util.TemplateCommandHelper;

@Service
public class CommonTemplateService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	 * @param list
	 * @param templatePath
	 */
	public void createFileByTemplate(List<TableInfo> tableInfos, String templatePath,String srcOutPath,String templateName,String fileNameTail) {
		try {
			List<String> daoTemplateContent = FileHelper.readCommonTemplateContent(templatePath,templateName);
			String daoPath = DaoBuildUtil.createPathByPackage(daoTemplateContent,srcOutPath);
			logger.info("创建目录【"+daoPath+"】成功");
			for (TableInfo tableInfo : tableInfos) {
				List<String> daoFileContent = new ArrayList<String>();
				builderContent(tableInfo,daoTemplateContent,daoFileContent);
				logger.info("开始写文件【"+srcOutPath + daoPath + tableInfo.getClassName()+fileNameTail+"】");
				File file = new File(srcOutPath + daoPath + tableInfo.getClassName()+fileNameTail);
				FileHelper.writeFile(file,daoFileContent);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	/**
	 * 
	 * @param tableInfo
	 * @param daoTemplateContent
	 * @param daoFileContent
	 */
	private void builderContent(TableInfo tableInfo,List<String> daoTemplateContent, List<String> daoFileContent) {
		for (String line : daoTemplateContent) {
			String newLine = TemplateCommandHelper.analyseTemplate(tableInfo, null, line);
			daoFileContent.add(newLine);
		}
	}
}
