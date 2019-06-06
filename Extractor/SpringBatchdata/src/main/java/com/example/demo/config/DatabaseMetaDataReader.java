package com.example.demo.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.SpringBatchdataApplication;
import com.example.demo.model.Data;



@Component
public class DatabaseMetaDataReader implements ItemReader<Data> {
	//private DataSource datasource;
	JdbcTemplate jdbcTemplate;// = new JdbcTemplate(datasource);
	static Connection connection = null;
	static java.sql.DatabaseMetaData metadata = null;
	private int nextStudentIndex;
    private List<Data> studentData=new ArrayList<>();
	DatabaseMetaDataReader(DataSource datasource) throws SQLException {
        initialize(datasource);
    }
 
    private void initialize(DataSource datasource) throws SQLException {
    	
    	 try {
	           connection = SpringBatchdataApplication.getMySqlConnection();
	       } catch (SQLException e) {
	           System.err.println("There was an error getting the connection: "
	                   + e.getMessage());
	       }

	       try {
	           metadata = (DatabaseMetaData) connection.getMetaData();
	       } catch (SQLException e) {
	           System.err.println("There was an error getting the metadata: "
	                   + e.getMessage());
	       }
	        String catalog = null, schemaPattern = null, tableNamePattern = null;
	        String s1 = null;
	    	s1 = metadata.getDatabaseProductName();
	        String[] types = {"TABLE"};
	        ArrayList<String> al = new ArrayList<String>();
	        JdbcTemplate jdbcTemplate= new JdbcTemplate(datasource);
	    	al.addAll(jdbcTemplate.queryForList("Show databases",String.class));
	       
	       for(int i=0;i<al.size();i++) {
	    	   ResultSet rsTables = metadata.getTables(catalog, schemaPattern, tableNamePattern, types);
	    	   while (rsTables.next()) {
	    		   String table_nm = rsTables.getString(3);
	    		   String Columnpattern = null;
	    		   ResultSet rsColumns = metadata.getColumns(catalog, schemaPattern, table_nm, Columnpattern);
	    		   while(rsColumns.next()) {
	    			   String Column_nm=rsColumns.getString("COLUMN_NAME");
	    			   Data data = new Data(s1,al.get(i),table_nm,Column_nm);
	    			   studentData.add(data);
	    		   }
	    		   
	    	   }
	       }
 
        //studentData= Collections.unmodifiableList(Arrays.asList());
        nextStudentIndex = 0;
    }
	@Override
	public Data read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Data nextStudent = null;
		 
        if (nextStudentIndex < studentData.size()) {
            nextStudent = studentData.get(nextStudentIndex);
            nextStudentIndex++;
        }
		return nextStudent;
	}

}
