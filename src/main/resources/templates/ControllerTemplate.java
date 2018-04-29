/**
 * 
 */
package com.xhyj.meeting.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xhyj.meeting.dto.ReturnData;
import com.xhyj.meeting.util.MyBeanUtil;
import com.xhyj.util.annotation.Label;

import com.xhyj.meeting.dto._{className}Dto;
import _{servicePackageName}._{className}Service;
import _{entityPackageName}._{className};

/**
 * 
 * <p>Title: _{tableComments}</p>  
 * <p>Description: </p>  
 * @author zhaojz
 * @date _{date}
 */
@RestController
@RequestMapping("/_{objectName}/")
public class _{className}Controller {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private _{className}Service _{objectName}Service;
	@RequestMapping("queryAll")
	@Label("查询所有_{tableComments}")
	public ReturnData queryAll() {
		List<_{className}> all = _{objectName}Service.findAll();
		return MyBeanUtil.getSuccReturnData(all, new _{className}Dto());
	}
	@RequestMapping("queryOnePage")
	@Label("分页查询_{tableComments}")
	public ReturnData queryOnePage(_{className} user,int pageNum) {
		Page<_{className}> all = _{objectName}Service.findPage(user, pageNum);
		return MyBeanUtil.getSuccReturnData(all, new _{className}Dto());
	}
	@RequestMapping("queryOneById")
	@Label("查询单条_{tableComments}")
	public ReturnData queryOneById(_{className} _{objectName}) {
		_{className} one = _{objectName}Service.findOneById(_{objectName});
		return MyBeanUtil.getSuccReturnData(one, new _{className}Dto());
	}
	@RequestMapping("add")
	@Label("添加_{tableComments}")
	public ReturnData add(_{className} _{objectName}) {
		_{className} result = _{objectName}Service.add(_{objectName});
		return MyBeanUtil.getSuccReturnData(result,new _{className}Dto());
	}
	@RequestMapping("update")
	@Label("修改_{tableComments}")
	public ReturnData update(_{className} _{objectName}) {
		_{className} result = _{objectName}Service.update(_{objectName});
		return MyBeanUtil.getSuccReturnData(result,new _{className}Dto());
	}
	@RequestMapping("delete")
	@Label("删除_{tableComments}")
	public ReturnData delete(_{className} _{objectName}) {
		try {
			_{objectName}Service.delete(_{objectName});
			return MyBeanUtil.getSuccReturnData();
		}catch(Exception e) {
    		logger.warn("id为"+_{objectName}.getId()+"记录不存在");
    		return MyBeanUtil.getBaseReturnData("1","操作失败");
    	}
	}
}
