package com.zjz.code.entity;

import java.util.List;

import com.zjz.code.util.StringUtil;

public class TableInfo {
	private String tableName;
	private String className;
	private String comment;
	private List<ColumnInfo> list;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String tableName) {
		this.className = StringUtil.tableNameToClassName(tableName);
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<ColumnInfo> getList() {
		return list;
	}
	public void setList(List<ColumnInfo> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "TableInfo [tableName=" + tableName + ", className=" + className + ", comment=" + comment + ", list="
				+ list + "]";
	}
	
}
