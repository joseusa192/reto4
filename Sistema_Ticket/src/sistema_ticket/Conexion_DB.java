package sistema_ticket;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class Conexion_DB {
    
    private static Connection con;
    private static final String driver ="com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/SistemaLTI_SAS";
    private static final String JDBC_USER="root";
    private static final String JDBC_PASSWORD="JaUs192@.com";
    
    public Connection conectar(){
        Connection conectar=null;
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");       
           conectar=DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
           //JOptionPane.showMessageDialog(null," Conexion Establecida Correctamente");
           
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Sin Conexion "+e,"Conexion",JOptionPane.ERROR_MESSAGE);
            
        }
        return conectar;
    }   
}

