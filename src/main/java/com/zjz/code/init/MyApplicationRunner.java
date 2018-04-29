package com.zjz.code.init;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.zjz.code.entity.TableInfo;
import com.zjz.code.service.CommonTemplateService;
import com.zjz.code.service.DTOService;
import com.zjz.code.service.EntityService;
import com.zjz.code.service.LoadDBInfoService;
import com.zjz.code.util.CommonBuildUtil;
import com.zjz.code.util.FileHelper;

@Component
@Order(value = 1)
public class MyApplicationRunner implements ApplicationRunner {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value(value = "${code.package}")
	private String rootPackage;
	@Value(value = "${code.templatePath}")
	private String templatePath;
	@Value(value = "${code.srcPath}")
	private String srcPath;
	
	@Value(value = "${code.entityTemplateFileName}")
	private String entityTemplateFileName;
	@Value(value = "${code.dtoTemplateFileName}")
	private String dtoTemplateFileName;
	@Value(value = "${code.daoTemplateFileName}")
	private String daoTemplateFileName;
	@Value(value = "${code.serviceTemplateFileName}")
	private String serviceTemplateFileName;
	@Value(value = "${code.controllerTemplateFileName}")
	private String controllerTemplateFileName;	
	
	@Autowired
	private LoadDBInfoService loadDBInfoService;
	@Autowired
	private EntityService EntityService;
	@Autowired
	private DTOService dtoService;
	@Autowired
	private CommonTemplateService commonTemplateService;
    @Override
    public void run(ApplicationArguments var1) throws Exception{
    	logger.info("开始读表。。。");
    	List<TableInfo> tableInfos = loadDBInfoService.reader();
    	logger.info("读到的数据："+new Gson().toJson(tableInfos));
    	logger.info("清理输出目录："+srcPath);
    	FileHelper.deleteDir(new File(srcPath));
    	FileHelper.mkDir(new File(srcPath));
    	logger.info("初始化模板指令值。。。");
    	CommonBuildUtil.initTemplateCommandValue(templatePath,entityTemplateFileName,dtoTemplateFileName,daoTemplateFileName,serviceTemplateFileName);
    	logger.info("正在构建实体类。。。");
    	EntityService.createEntity(tableInfos,templatePath,entityTemplateFileName,srcPath);
    	logger.info("正在构建Dto。。。");
    	dtoService.createDto(tableInfos,templatePath,dtoTemplateFileName,entityTemplateFileName,srcPath);
    	logger.info("正在构建Dao。。。");
    	commonTemplateService.createFileByTemplate(tableInfos, templatePath, srcPath,daoTemplateFileName,"Dao.java");
    	logger.info("正在构建Service。。。");
    	commonTemplateService.createFileByTemplate(tableInfos, templatePath, srcPath,serviceTemplateFileName,"Service.java");
    	logger.info("正在构建Controller。。。");
    	commonTemplateService.createFileByTemplate(tableInfos, templatePath, srcPath,controllerTemplateFileName,"Controller.java");
    	logger.info("执行结束    ok");
    }
}
