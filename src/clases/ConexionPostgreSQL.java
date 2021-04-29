package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConexionPostgreSQL {
    public Connection conexion;
    public Statement sentencia;
    public ResultSet resultado;
    public void ConectarPostgreSQL(){
        try{
            final String Controlador ="org.postgresql.Driver";
            Class.forName(Controlador);
            final String url_bd="jdbc:postgresql://localhost:57789/prueba";
            conexion = DriverManager.getConnection(url_bd, "postgres", "virus123");
            sentencia=conexion.createStatement();
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "\nError al conectar con BD MySQL", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void DesconectarPostgreSQL(){
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
