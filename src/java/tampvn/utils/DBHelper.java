/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampvn.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Polaris
 */
public class DBHelper implements Serializable {
    public static Connection makeConnection()
            throws /*ClassNotFoundException,*/ NamingException,   SQLException{
        
        //1. Get current system file
        Context context = new InitialContext();
        
        //2. get container context 
        Context tomcatContext =  (Context) context.lookup("java:comp/env");
        
        //3. get DataSource 
        DataSource ds = (DataSource)tomcatContext.lookup("DBLink");
        
        //4. get Connection 
        Connection con = ds.getConnection();
        
        return con;
//        // load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        
//        //tao connection String
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=StudentManagement";
//       
//        //mo ket noi
//        Connection con = DriverManager.getConnection(url,"sa", "123456");
//        
//        return con;
    }
    
}
