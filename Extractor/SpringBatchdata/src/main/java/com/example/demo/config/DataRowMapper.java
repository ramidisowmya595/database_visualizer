package com.example.demo.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.model.Data;

public class DataRowMapper implements RowMapper<Data> {

	@Override
	public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
		Data d = new Data();
		d.setId(rs.getInt("id"));
		d.setType_nm(rs.getString("type_nm"));
		d.setSchema_nm(rs.getString("schema_nm"));
		d.setTable_nm(rs.getString("table_nm"));
		d.setColumn_nm(rs.getString("column_nm"));
		return d;
	}

}
