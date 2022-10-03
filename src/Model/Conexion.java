
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;

public class Conexion {
    private static final String url= "jdbc:mysql://localhost:3306/hotelalura";
    private static final String usuario ="root";
    private static final String clave= "";
    Connection con = null;
    
        public Connection devolverConnection(){
        try {            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, clave);
            if (con != null) {
                System.out.println("Conexión ok");
            } else {
                System.out.println("Conexión fallida");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error ", JOptionPane.ERROR_MESSAGE);
        }        
        return con;
    }
        
    public void cerrarConexion(){
        try {
            if (con != null){
                con.close();
                System.out.println("Conexión cerrada exitosamente");
            }
        } catch (Exception e) {
        }
    }    
 }
    
 