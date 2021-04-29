package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConexionMySQL {
    public Connection conexion;
    public Statement sentencia;
    public ResultSet resultado;
    public void ConectarMySQL(){
        try{
            final String Controlador ="com.mysql.jdbc.Driver";
            Class.forName(Controlador);
            final String url_bd="jdbc:mysql://localhost:3306/ejemplo";
            conexion = DriverManager.getConnection(url_bd, "root", "");
            sentencia=conexion.createStatement();
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "\nError al conectar con BD MySQL", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void DesconectarMySQL(){
        try{
            if(conexion!=null){
                if(sentencia != null){
                    sentencia.close();
                }
                conexion.close();
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "\nError al desconectar la BD MySQL", JOptionPane.ERROR_MESSAGE);
            System.exit(1);    
        }
    }
}
