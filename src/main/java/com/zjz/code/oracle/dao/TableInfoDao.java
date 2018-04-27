package com.zjz.code.oracle.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zjz.code.entity.ColumnInfo;
import com.zjz.code.entity.TableInfo;

@Repository
public class TableInfoDao {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Value(value = "${code.tables.sql}")
	private String sql;
	
	public List<TableInfo> queryTables() {
		List<TableInfo> list = new ArrayList<TableInfo>();
		jdbcTemplate.query(sql, (ResultSet rs) -> {
			TableInfo tableInfo = new TableInfo();
			String tableName = rs.getString("tableName");
			tableInfo.setTableName(tableName);
			tableInfo.setClassName(tableName);
			tableInfo.setComment(rs.getString("comments"));
			tableInfo.setList(queryColumn(tableName));
			list.add(tableInfo);
		});
		logger.debug("sql执行结果："+list);
		return list;
	}

	private List<ColumnInfo> queryColumn(String tableName) {
		String columnSql = "select  tc.column_name,tc.DATA_TYPE,tc.DATA_LENGTH,cc.comments from user_tab_columns tc left join user_col_comments cc on tc.TABLE_NAME=cc.table_name and tc.COLUMN_NAME=cc.column_name  where  tc.Table_Name='"+tableName+"'";
		List<ColumnInfo> list = new ArrayList<ColumnInfo>();
		jdbcTemplate.query(columnSql, (ResultSet rs) -> {
			ColumnInfo columnInfo = new ColumnInfo();
			String column_name = rs.getString("column_name");
			columnInfo.setColumnName(column_name);
			columnInfo.setPropertyName(column_name);
			columnInfo.setDataType(rs.getString("DATA_TYPE"));
			columnInfo.setLength(rs.getString("DATA_LENGTH"));
			columnInfo.setComment(rs.getString("comments"));
			list.add(columnInfo);
		});
		return list;
	}
}
