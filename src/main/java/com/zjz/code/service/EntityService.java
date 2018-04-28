package com.zjz.code.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zjz.code.entity.TableInfo;
import com.zjz.code.util.EntityBuildUtil;
import com.zjz.code.util.FileHepler;
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
			List<String> entityTemplateContent = FileHepler.readEntityTemplateContent(templatePath);
			String entityPath = EntityBuildUtil.createPathByPackage(entityTemplateContent,srcOutPath);
			logger.info("创建目录【"+entityPath+"】成功");
			List<String> entityFileContent = new ArrayList<String>();
			for (TableInfo tableInfo : list) {
				builderContent(tableInfo,entityTemplateContent,entityFileContent);
				logger.info("开始写文件【"+srcOutPath + entityPath + tableInfo.getClassName()+".java"+"】");
				File file = new File(srcOutPath+entityPath+tableInfo.getClassName()+".java");
				FileHepler.writeFile(file,entityFileContent);
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
			String newLine = TemplateCommandHelper.analyse(tableInfo, null, line);
			entityFileContent.add(newLine);
		}
		//构建字段属性
		
		// 构建 set  get 方法
		
		//结束
	}
}
