package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resource_table")
public class Insertdb {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id")
	private String id;
	@Column(name = "type_nn")
	private String type_nn;
	@Column(name = "schema_nn")
	private String schema_nn;
	@Column(name = "table_nn")
	private String table_nn;
	@Column(name = "column_nn")
	private String column_nn;
	public String getId() {
		return id;
	}
	public String getType_nn() {
		return type_nn;
	}
	public String getSchema_nn() {
		return schema_nn;
	}
	public String getTable_nn() {
		return table_nn;
	}
	public String getColumn_nn() {
		return column_nn;
	}
	
}
