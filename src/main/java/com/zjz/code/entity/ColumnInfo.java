package com.zjz.code.entity;

import com.zjz.code.util.StringUtil;

public class ColumnInfo {
	private String columnName;
	private String propertyName;
	private String comment;
	private String dataType;
	private String length;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String columnName) {
		this.propertyName = StringUtil.columnNameToPorpertyName(columnName);
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	@Override
	public String toString() {
		return "ColumnInfo [columnName=" + columnName + ", propertyName=" + propertyName + ", comment=" + comment
				+ ", dataType=" + dataType + ", length=" + length + "]";
	}
	
	
	
}
