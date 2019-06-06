package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "insertdb" ,schema="visualdb")
public class Insertdb {
	@Override
	public String toString() {
		return "Insertdb [id=" + id + ", type_nm=" + type_nm + ", schema_nm=" + schema_nm + ", table_nm=" + table_nm
				+ ", column_nm=" + column_nm + "]";
	}
	@Id
	@JsonInclude
	@Column(name = "id")
	private long id;
	@Column(name = "type_nm")
	private String type_nm;
	@Column(name = "schema_nm")
	private String schema_nm;
	@Column(name = "table_nm")
	private String table_nm;
	@Column(name = "column_nm")
	private String column_nm;
	
	
	public Insertdb() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Insertdb(long id, String type_nm, String schema_nm, String table_nm, String column_nm) {
		super();
		this.id = id;
		this.type_nm = type_nm;
		this.schema_nm = schema_nm;
		this.table_nm = table_nm;
		this.column_nm = column_nm;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((column_nm == null) ? 0 : column_nm.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((schema_nm == null) ? 0 : schema_nm.hashCode());
		result = prime * result + ((table_nm == null) ? 0 : table_nm.hashCode());
		result = prime * result + ((type_nm == null) ? 0 : type_nm.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Insertdb other = (Insertdb) obj;
		if (column_nm == null) {
			if (other.column_nm != null)
				return false;
		} else if (!column_nm.equals(other.column_nm))
			return false;
		if (id != other.id)
			return false;
		if (schema_nm == null) {
			if (other.schema_nm != null)
				return false;
		} else if (!schema_nm.equals(other.schema_nm))
			return false;
		if (table_nm == null) {
			if (other.table_nm != null)
				return false;
		} else if (!table_nm.equals(other.table_nm))
			return false;
		if (type_nm == null) {
			if (other.type_nm != null)
				return false;
		} else if (!type_nm.equals(other.type_nm))
			return false;
		return true;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setType_nm(String type_nm) {
		this.type_nm = type_nm;
	}
	public void setSchema_nm(String schema_nm) {
		this.schema_nm = schema_nm;
	}
	public void setTable_nm(String table_nm) {
		this.table_nm = table_nm;
	}
	public void setColumn_nm(String column_nm) {
		this.column_nm = column_nm;
	}
	public String getType_nm() {
		return type_nm;
	}
	public String getSchema_nm() {
		return schema_nm;
	}
	public String getTable_nm() {
		return table_nm;
	}
	public String getColumn_nm() {
		return column_nm;
	}
	
}
