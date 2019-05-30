package com.example.demo.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.Extractor1Application;
import com.mysql.cj.jdbc.DatabaseMetaData;
@Repository
public class UserRepository { 


@Autowired
JdbcTemplate jdbcTemplate;
static Connection connection = null;
static Connection connection1 = null;
static DatabaseMetaData metadata = null;
List<String> l1 = new ArrayList<>();


static {
       try {
           connection = Extractor1Application.getMySqlConnection();
       } catch (SQLException e) {
           System.err.println("There was an error getting the connection: "
                   + e.getMessage());
       } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
       

       try {
           metadata = (DatabaseMetaData) connection.getMetaData();
       } catch (SQLException e) {
           System.err.println("There was an error getting the metadata: "
                   + e.getMessage());
       }
       
   }


public List<String> getAlltickers() throws SQLException {
        String catalog = null, schemaPattern = null, tableNamePattern = null;
        String[] types = {"TABLE"};

        ResultSet rsTables = metadata.getTables(catalog, schemaPattern, tableNamePattern, types);
        ResultSet Schema = connection.getMetaData().getCatalogs();
        while (rsTables.next()) {
      
        String tableSchema = rsTables.getString(1);
            String tableName = rsTables.getString(3);
           //  System.out.println("\n=== Schema: " + database);
            System.out.println("\n=== Schema: " + tableSchema);
            System.out.println("\n=== TABLE: " + tableName);

            String columnNamePattern = null;
            ResultSet rsColumns = metadata.getColumns(catalog, schemaPattern, tableName, columnNamePattern);

            ResultSet rsPK = metadata.getPrimaryKeys(catalog, schemaPattern, tableName);

            while (rsColumns.next()) {
                String columnName = rsColumns.getString("COLUMN_NAME");
                String columnType = rsColumns.getString("TYPE_NAME");
                int columnSize = rsColumns.getInt("COLUMN_SIZE");
                System.out.println("\t" + columnName + " - " + columnType + "(" + columnSize + ")");
            }

            while (rsPK.next()) {
                String primaryKeyColumn = rsPK.getString("COLUMN_NAME");
                System.out.println("\tPrimary Key Column: " + primaryKeyColumn);
            }

        }
return l1;
}

public String metaData() throws SQLException {
// TODO Auto-generated method stub
	
String s1 = null;
String s3 = "abc";
String s4 = "def";
System.err.println(s1);
s1 = metadata.getDatabaseProductName();
ArrayList<String> a1=new ArrayList<String>();
a1.addAll(jdbcTemplate.queryForList("show databases",String.class));
Iterator i = a1.iterator();
//System.out.println("The ArrayList elements are:");

System.err.println(s1);
for(int i1=0;i1<a1.size();i1++)
{
	if(a1.get(i1).equals("employee"))
	{
		Object params[]=new Object[] {s1,a1.get(i1),s3,s4};
				int types[]=new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
	//	jdbcTemplate.update("insert into `visualdb`.`insertdb` values('','s2','s3','s4');");
		jdbcTemplate.update("INSERT INTO visualdb.insertdb (type_nm,schema_nm,table_nm,column_nm) VALUES(?,?,?,?)" ,params,types);
		
	}
}
	System.out.println(a1);
return s1;
} 

}