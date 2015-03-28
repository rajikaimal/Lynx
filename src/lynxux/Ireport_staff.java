/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lynxux;
import java.awt.Color;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;
import java.sql.*;
import javax.swing.JOptionPane;

import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
/**
 *
 * @author rajika
 */
public class Ireport_staff {
    Connection conn;
    PreparedStatement pst;
    ResultSet rst;
    public Ireport_staff(String sql,String button) {
        if(button == "fromEmployee"){
            build1(sql);
        }
        if(button == "additionalReport"){
            build2(sql);
        }
        if(button == "employeeReport"){
            build3(sql);    
        }
        if(button == "defaultReport"){
            build4(sql);
        }
    }
    //employee report
    private void build1(String sql) {
		StyleBuilder boldStyle         = stl.style().bold();
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
		StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle)
		                                    .setBorder(stl.pen1Point())
		                                    .setBackgroundColor(Color.LIGHT_GRAY);
		try {
			report()//create new report design
			  .setColumnTitleStyle(columnTitleStyle)
			  .highlightDetailEvenRows()
			  .columns(//add columns
			  	//            title,     field name     data type
			  	col.column("Date",       "date",      type.stringType()),
			  	col.column("Type of Leave",   "type", type.stringType()),
			  	col.column("Status", "status", type.stringType()))
			  .title(cmp.text("Monthly Report").setStyle(boldCenteredStyle))//shows report title
			  .setDataSource(createDataSource1(sql))//set datasource
			  .show();//create and show report
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        //Aditional Report //isuru
        private void build2(String sql) {
		StyleBuilder boldStyle         = stl.style().bold();
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
		StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle)
		                                    .setBorder(stl.pen1Point())
		                                    .setBackgroundColor(Color.LIGHT_GRAY);
		try {
			report()//create new report design
			  .setColumnTitleStyle(columnTitleStyle)
			  .highlightDetailEvenRows()
			  .columns(//add columns
			  	//            title,     field name     data type
			  	col.column("EmpId",       "empid",      type.stringType()),
			  	col.column("Type of Leave",   "type", type.stringType()),
			  	col.column("Status", "status", type.stringType()),
                                col.column("Reason", "reason", type.stringType()),
                                col.column("Date", "date", type.stringType()))                               
			  .title(cmp.text("Monthly Report").setStyle(boldCenteredStyle))//shows report title
			  .setDataSource(createDataSource2(sql))//set datasource
			  .show();//create and show report
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        //EmployeeReport //chathra
	private void build3(String sql) {
		StyleBuilder boldStyle         = stl.style().bold();
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
		StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle)
		                                    .setBorder(stl.pen1Point())
		                                    .setBackgroundColor(Color.LIGHT_GRAY);
		try {
			report()//create new report design
			  .setColumnTitleStyle(columnTitleStyle)
			  .highlightDetailEvenRows()
			  .columns(//add columns
			  	//            title,     field name     data type
			  	col.column("EmpId",       "empid",      type.stringType()),
			  	col.column("Name",   "name", type.stringType()),
			  	col.column("Address", "address", type.stringType()),
                                col.column("Phone", "phone", type.stringType()),
                                col.column("Section", "section", type.stringType()),
                                col.column("Position", "position", type.stringType()),
                                col.column("Email", "email", type.stringType()),
                                col.column("Salary", "salary", type.stringType()),
                                col.column("Level", "level", type.stringType()))                               
			  .title(cmp.text("Employee Monthly Report").setStyle(boldCenteredStyle))//shows report title
			  .setDataSource(createDataSource3(sql))//set datasource
			  .show();//create and show report
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        //defaultleaves //losthith
        private void build4(String sql) {
		StyleBuilder boldStyle         = stl.style().bold();
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
		StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle)
		                                    .setBorder(stl.pen1Point())
		                                    .setBackgroundColor(Color.LIGHT_GRAY);
		try {
			report()//create new report design
			  .setColumnTitleStyle(columnTitleStyle)
			  .highlightDetailEvenRows()
			  .columns(//add columns
			  	//            title,     field name     data type
			  	col.column("EmpId",       "empid",      type.stringType()),
			  	col.column("Type of Leave",   "type", type.stringType()),
			  	col.column("Status", "status", type.stringType()),
                                col.column("Date", "date", type.stringType()))                               
			  .title(cmp.text("Report").setStyle(boldCenteredStyle))//shows report title
			  .setDataSource(createDataSource4(sql))//set datasource
			  .show();//create and show report
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private JRDataSource createDataSource1(String sql) {
            
            conn = MySqlConnect.ConnectDB();
            String date;
            String Type;
            String Status;
            DRDataSource dataSource = new DRDataSource("date", "type", "status");
            try{
                pst = conn.prepareStatement(sql);
                pst.setString(1,Login.loggedInUser);
                rst = pst.executeQuery();
                while(rst.next()){
                    date = rst.getString("Date");
                    Type = rst.getString("Type");
                    Status = rst.getString("Status");
             
                    dataSource.add(date,Type,Status);
                }
            }
            catch(Exception e){
                
            }
                return dataSource;
        }
	//Additional Leaves //isuru
        private JRDataSource createDataSource2(String sql) {
            
            conn = MySqlConnect.ConnectDB();
            String empId;
            String date;
            String Type;
            String Status;
            String Reason;
            DRDataSource dataSource = new DRDataSource("empid", "type", "status","reason","date");
            try{
                pst = conn.prepareStatement(sql);
                rst = pst.executeQuery();
                while(rst.next()){
                    empId = rst.getString("EmpId");
                    date = rst.getString("Date");
                    Type = rst.getString("Type");
                    Status = rst.getString("Status");
                    Reason = rst.getString("Reason");
             
                    dataSource.add(empId,Type,Status,Reason,date);
                }
            }
            catch(Exception e){
                
            }
                return dataSource;
        }
        //employee details //chathra
        private JRDataSource createDataSource3(String sql) {
            
            conn = MySqlConnect.ConnectDB();
            String empId;
            String name;
            String address;
            String phone;
            String section;
            String position;
            String email;
            String salary;
            String level;
            DRDataSource dataSource = new DRDataSource("empid", "name", "address","phone","section","position","email","salary","level");
            try{
                pst = conn.prepareStatement(sql);
                rst = pst.executeQuery();
                while(rst.next()){
                    empId = rst.getString("EmpId");
                    name = rst.getString("Name");
                    address = rst.getString("Address");
                    phone = rst.getString("Phone");
                    section = rst.getString("Section");
                    position = rst.getString("Position");
                    email = rst.getString("Email");
                    salary = rst.getString("Salary");
                    level = rst.getString("Level");
                    dataSource.add(empId,name,address,phone,section,position,email,salary,level);
                }
            }
            catch(Exception e){
                
            }
                return dataSource;
        }
        //default leaves //loshith
        private JRDataSource createDataSource4(String sql) {
            
            conn = MySqlConnect.ConnectDB();
            String empId;
            String date;
            String Type;
            String Status;
           
            DRDataSource dataSource = new DRDataSource("empid", "type", "status","date");
            try{
                pst = conn.prepareStatement(sql);
                rst = pst.executeQuery();
                while(rst.next()){
                    empId = rst.getString("EmpId");
                    date = rst.getString("Date");
                    Type = rst.getString("Type");
                    Status = rst.getString("Status");
             
                    dataSource.add(empId,Type,Status,date);
                }
            }
            catch(Exception e){
                
            }
                return dataSource;
        }
}
