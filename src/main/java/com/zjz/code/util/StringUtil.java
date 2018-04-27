package com.zjz.code.util;

public class StringUtil {
	public static String tableNameToClassName(String dbName) {
		if(dbName==null || "".equals(dbName)) {
			return dbName;
		}
		String tmpName = dbName.toLowerCase();
		String[] names = tmpName.split("_");
		StringBuffer sb = new StringBuffer();
		for (String name : names) {
			sb.append(name.substring(0, 1).toUpperCase() + name.substring(1));
		}
		return sb.toString();
	}
	public static String columnNameToPorpertyName(String dbName) {
		if(dbName==null || "".equals(dbName)) {
			return dbName;
		}
		String tmpName = dbName.toLowerCase();
		String[] names = tmpName.split("_");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < names.length; i++) {
			if(i==0) {
				sb.append(names[i]);
			}else {
				sb.append(names[i].substring(0, 1).toUpperCase() + names[i].substring(1));
			}
		}
		return sb.toString();
	}
}
