package com.anatolyguz.uproxip;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;


public class Access {

	 public static void hello() {
		 
		 File file = new File("/home/user/test.mdb");
		  try {
			Database db = new DatabaseBuilder(file)
			    .setFileFormat(Database.FileFormat.V2000)
			    .create();
			
			Table table = new TableBuilder("Test")
				    .addColumn(new ColumnBuilder("ID", DataType.LONG)
				               .setAutoNumber(true))
				    .addColumn(new ColumnBuilder("Name", DataType.TEXT))
				    .addColumn(new ColumnBuilder("Salary", DataType.MONEY))
				    .addColumn(new ColumnBuilder("StartDate", DataType.SHORT_DATE_TIME))
				    .toTable(db);
			
			
			
			 String name = "bob";
			  BigDecimal salary = new BigDecimal("1000.00");
			  Date startDate = new Date();
			 
			  table.addRow(Column.AUTO_NUMBER, name, salary, startDate);
			
			
			db.flush();
			db.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 System.out.println( "Hello access!" );
	 }

	 public static Database opendb(String namefile) throws IOException {
		 
		Database db = DatabaseBuilder.open(new File(namefile)   );
		return db;
		 
	 }

	 

	 public static ArrayList<Department> getlistdepartment() throws IOException  {
		 
		 ArrayList <Department> deplist = new ArrayList <Department>();
		 Database db = DatabaseBuilder.open(new File(App.namefileaccess)  );
		 String  name;
		 
		 Table table = db.getTable("Groups");
			 for(Row row : table) {
				 Object value = row.get("GroupName");
				 
				 name = value.toString();
				 deplist.add( new Department(name) );
				 //
				 //System.out.println(value);
				 //System.out.println("Look ma, a row: " + row);
				  }
			 
			Access.closedb(db);
			return deplist;
			

		 
	 }

	 
	 public static void closedb(Database db) throws IOException  {
		 
		db.flush();
		db.close();
		
		 
	 }

	 
}
