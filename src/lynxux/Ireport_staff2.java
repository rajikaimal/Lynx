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
public class Ireport_staff2 {
    Connection conn;
    PreparedStatement pst;
    ResultSet rst;
    public Ireport_staff2(String status) {
		build(status);
    }
    private void build(String status) {
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
			  .title(cmp.text("Status Report").setStyle(boldCenteredStyle))//shows report title
			  .setDataSource(createDataSource(status))//set datasource
			  .show();//create and show report
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JRDataSource createDataSource(String status) {
            String sql = "Select * from DefaultLeaves where EmpId=? and Status=?";
            conn = MySqlConnect.ConnectDB();
            String date;
            String Type;
            String Status;
            DRDataSource dataSource = new DRDataSource("date", "type", "status");
            try{
                pst = conn.prepareStatement(sql);
                pst.setString(1,Login.loggedInUser);
                pst.setString(2,status);
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
}
