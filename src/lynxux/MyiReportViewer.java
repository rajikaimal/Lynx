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
public class MyiReportViewer extends JFrame{
    Connection conn;
    public MyiReportViewer(String fileName){
        this(fileName,null);
    }
    public MyiReportViewer(String fileName,HashMap parameter) {
        super("View Report");
        try{
            conn = MySqlConnect.ConnectDB();
            JasperPrint print = JasperFillManager.fillReport(fileName, parameter, conn);
            JRViewer viewer=new JRViewer(print);
            Container c=getContentPane();
            c.add(viewer);
        }
        catch(Exception e){
            
        }
        setBounds(10,10,600,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
        MyiReportViewer viewer=new MyiReportViewer("/home/rajika/NetBeansProjects/LynxUX/src/lynxux/Lynx/Lynx.jasper");
        viewer.setVisible(true);
    }
}
