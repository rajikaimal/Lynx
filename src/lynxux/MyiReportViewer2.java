/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lynxux;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.io.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.view.*;
/**
 *
 * @author rajika
 */
public class MyiReportViewer2  extends JFrame{
    Connection conn;
    public MyiReportViewer2(String fileName){
        this(fileName,null);
    }
    public MyiReportViewer2(String fileName,HashMap parameter) {
        super("View Report");
        try{
            conn = MySqlConnect.ConnectDB();
//            JasperDesign jasperDesign = JasperManager.loadXmlDesign(fileName);
//            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, conn);
//            Container c=getContentPane();
//            JRViewer viewer=new JRViewer(print);
//            c.add(viewer);
//            JasperPrint print = JasperFillManager.fillReport(fileName, parameter, conn);
//            JRViewer viewer=new JRViewer(print);
//            Container c=getContentPane();
//            c.add(viewer);
            
        }
        catch(Exception e){
            
        }
        setBounds(10,10,600,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
        HashMap param=new HashMap();
        String par = "1";
        param.put("EmpId",par);
        MyiReportViewer2 viewer=new MyiReportViewer2("Lynxmonthly.jasper",param);
        viewer.setVisible(true);
    }
    
}
