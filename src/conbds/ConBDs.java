package conbds;

import clases.ConexionMySQL;
import clases.ConexionPostgreSQL;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConBDs extends JFrame{
    
    public void componentes(JPanel panel){
        panel.setLayout(null);
        JLabel etiqueta = new JLabel("Datos: ");
        etiqueta.setBounds(10, 20, 60, 25);
        panel.add(etiqueta);
        JTextField nombre = new JTextField(20);
        nombre.setBounds(80, 20, 250, 25);
        panel.add(nombre);
        JButton enviar = new JButton("Ingresar");
        enviar.setBounds(80, 80, 100, 25);
        panel.add(enviar);
        JButton limpiar = new JButton("Limpiar");
        limpiar.setBounds(new Rectangle(210, 80, 100, 25));
        panel.add(limpiar);
        JButton conecmysql = new JButton("MySql");
        conecmysql.setBounds(80, 120, 100, 25);
        panel.add(conecmysql);
        JButton conecposql = new JButton("Postgre");
        conecposql.setBounds(new Rectangle(210, 120, 100, 25));
        panel.add(conecposql);
        JButton eliminar = new JButton("Eliminar");
        eliminar.setBounds(80, 160, 100, 25);
        panel.add(eliminar);
        JButton insertar = new JButton("Insertar");
        insertar.setBounds(new Rectangle(210, 160, 100, 25));
        panel.add(insertar);
        JLabel etiqueta1 = new JLabel("");
        etiqueta1.setBounds(50, 210, 300, 50);
        etiqueta1.setBorder (BorderFactory.createLineBorder(Color.red, 1));
        panel.add(etiqueta1);
/*        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }

            private void enviarActionPerformed(ActionEvent evt) {
                String texto=nombre.getText();
                etiqueta1.setText(texto);
                System.out.println("El texto '"+texto+"' fue escrito correctamente.");
            }
        });*/
        enviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String texto=nombre.getText();
                etiqueta1.setText(texto);
                System.out.println("El texto '"+texto+"' fue escrito correctamente.");
            }
        });
/*        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }

            private void limpiarActionPerformed(ActionEvent evt) {
                String texto=etiqueta1.getText();
                etiqueta1.setText("");
                System.out.println("El texto '"+texto+"' fue limpiado correctamente.");
            }
        });*/
        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String texto=etiqueta1.getText();
                etiqueta1.setText("");
                nombre.setText("");
                System.out.println("El texto '"+texto+"' fue limpiado correctamente.");
            }
        });
        conecmysql.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try{
                    ConexionMySQL conn=new ConexionMySQL();
                    conn.ConectarMySQL();
                    String SQL="SELECT * FROM `mensaje`";
                    conn.sentencia.execute(SQL);
                    etiqueta1.setText("Conexión con MySQL establecida.");
                    conn.DesconectarMySQL();
                    
                }catch(SQLException ex){
                    etiqueta1.setText("Error al conectarse con MySQL.");
                    JOptionPane.showMessageDialog(null, ex.toString()+" Error");
                }
            }
        });
        
        conecposql.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try{
                    ConexionPostgreSQL conn=new ConexionPostgreSQL();
                    conn.ConectarPostgreSQL();
                    String SQL = "SELECT * FROM `mensaje`";
                    conn.sentencia.execute(SQL);
                    etiqueta1.setText("Conexión con PostgreSQL establecida.");
                    conn.DesconectarPostgreSQL();
                    
                }catch(SQLException ex){
                    etiqueta1.setText("Error al conectarse con MySQL.");
                    JOptionPane.showMessageDialog(null, ex.toString()+" Error");
                }
            }
        });
        
        insertar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String valor=nombre.getText();
                if(!valor.isEmpty()){
                    try{
                        ConexionPostgreSQL conn=new ConexionPostgreSQL();
                        conn.ConectarPostgreSQL();
                        String SQL =  "INSERT INTO mensaje (nombre) VALUES ('"+valor+"')";
                        conn.sentencia.execute(SQL);
                        JOptionPane.showMessageDialog(null,"'"+valor+"' gregado a bd PostgreSQL de manera exitosa.");
                        System.out.println("El nombre '"+valor+"' fue agregado a la PostgreSQL de forma exitosa.");
                        conn.DesconectarPostgreSQL();
                    }catch(SQLException ex){
                        etiqueta1.setText("Error al agregar a bd PostgreSQL.");
                        JOptionPane.showMessageDialog(null, ex.toString()+" Error");
                    }
                    try{
                        ConexionMySQL conn=new ConexionMySQL();
                        conn.ConectarMySQL();
                        String SQL = "INSERT INTO mensaje (nombre) VALUES ('"+valor+"')";
                        conn.sentencia.execute(SQL);
                        JOptionPane.showMessageDialog(null,"'"+valor+"' agregado a bd MySQL de manera exitosa.");
                        System.out.println("El nombre '"+valor+"' fue agregado a la bd MySQL de forma exitosa.");
                        conn.DesconectarMySQL();
                    }catch(SQLException ex){
                        etiqueta1.setText("Error al agregar a bd MySQL.");
                        JOptionPane.showMessageDialog(null, ex.toString()+" Error");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Coloque un nombre antes de ejecutar esta opción");
                }
            }
        });
        eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String valor=nombre.getText();
                if(!valor.isEmpty()){
                    try{
                        ConexionPostgreSQL conn=new ConexionPostgreSQL();
                        conn.ConectarPostgreSQL();
                        String QUERY = "DELETE FROM mensaje WHERE nombre='"+valor+"'";
                        conn.sentencia.execute(QUERY);
                        JOptionPane.showMessageDialog(null,"Eliminado de la bd PostgreSQL de manera exitosa.");
                        System.out.println("El nombre '"+valor+"' fue eliminado de la bd PostgreSQL de forma exitosa.");
                        conn.DesconectarPostgreSQL();
                    }catch(SQLException ex){
                        etiqueta1.setText("Error al eliminar de la bd PostgreSQL.");
                        JOptionPane.showMessageDialog(null, ex.toString()+" Error");
                    }
                    try{
                        ConexionMySQL conn=new ConexionMySQL();
                        conn.ConectarMySQL();
                        String QUERY = "DELETE FROM mensaje WHERE nombre='"+valor+"'";
                        conn.sentencia.execute(QUERY);
                        JOptionPane.showMessageDialog(null,"Eliminado de la bd MySQL de manera exitosa.");
                        System.out.println("El nombre '"+valor+"' fue eliminado de la bd MySQL de forma exitosa.");
                        conn.DesconectarMySQL();
                    }catch(SQLException ex){
                        etiqueta1.setText("Error al eliminar de la bd MySQL.");
                        JOptionPane.showMessageDialog(null, ex.toString()+" Error");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Coloque un nombre antes de ejecutar esta opción");
                }
            }
        });
    }
    
    public static void main(String[] args) {
        JFrame ventana = new JFrame("My Ventana No1");
        ventana.setSize(400, 300);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contenedor = new JPanel();
        ventana.add(contenedor);
        ConBDs objeto = new ConBDs();
        objeto.componentes(contenedor);
        ventana.setVisible(true);
    }
    
}
