package com.zjz.code.init;

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
import com.zjz.code.service.EntityService;
import com.zjz.code.service.LoadDBInfoService;
import com.zjz.code.util.DtoBuildUtil;
import com.zjz.code.util.TemplateBuildUtil;

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
	@Autowired
	private LoadDBInfoService loadDBInfoService;
	@Autowired
	private EntityService EntityService;
	
    @Override
    public void run(ApplicationArguments var1) throws Exception{
    	logger.info("开始读表。。。");
    	List<TableInfo> list = loadDBInfoService.reader();
    	logger.info("读到的数据："+new Gson().toJson(list));
    	Thread.sleep(1000);
    	logger.info("正在构建实体类。。。");
    	EntityService.createEntity(list,templatePath,srcPath);
    	Thread.sleep(1000);
    	logger.info("正在构建DTO。。。");
    	DtoBuildUtil.createDto(list);
    	Thread.sleep(1000);
    	logger.info("正在构建通用模板。。。");
    	TemplateBuildUtil.build(list);
    	Thread.sleep(1000);
    	logger.info("执行结束    ok");
    }
}
