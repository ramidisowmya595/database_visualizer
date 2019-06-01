package com.example.demo.model;

public class Data {
	private Integer id;
	private String type_nm;
	private String schema_nm;
	private String table_nm;
	private String column_nm;
	
	public Data() {
		super();
	}
	public Data(Integer id, String type_nm, String schema_nm, String table_nm, String column_nm) {
		super();
		this.id = id;
		this.type_nm = type_nm;
		this.schema_nm = schema_nm;
		this.table_nm = table_nm;
		this.column_nm = column_nm;
	}
	public Data(String type_nm, String schema_nm, String table_nm, String column_nm) {
		super();
		this.type_nm = type_nm;
		this.schema_nm = schema_nm;
		this.table_nm = table_nm;
		this.column_nm = column_nm;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType_nm() {
		return type_nm;
	}
	public void setType_nm(String type_nm) {
		this.type_nm = type_nm;
	}
	public String getSchema_nm() {
		return schema_nm;
	}
	public void setSchema_nm(String schema_nm) {
		this.schema_nm = schema_nm;
	}
	public String getTable_nm() {
		return table_nm;
	}
	public void setTable_nm(String table_nm) {
		this.table_nm = table_nm;
	}
	public String getColumn_nm() {
		return column_nm;
	}
	public void setColumn_nm(String column_nm) {
		this.column_nm = column_nm;
	}
	@Override
	public String toString() {
		return "Data [id=" + id + ", type_nm=" + type_nm + ", schema_nm=" + schema_nm + ", table_nm=" + table_nm
				+ ", column_nm=" + column_nm + "]";
	}
	
}
