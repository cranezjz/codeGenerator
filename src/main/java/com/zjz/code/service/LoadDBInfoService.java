package com.zjz.code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjz.code.entity.TableInfo;
import com.zjz.code.oracle.dao.TableInfoDao;

@Service
public class LoadDBInfoService {
	@Autowired
	private TableInfoDao tableInfoDao;
	public List<TableInfo> reader(){
		return tableInfoDao.queryTables();
	}
}
