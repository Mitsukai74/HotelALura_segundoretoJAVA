
package Model;

import Controller.Huesped;
import Controller.Reserva;
import Controller.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultasSQL extends Conexion{
    Conexion newConexion = new Conexion();    
    
    public boolean loginUsuario(Usuarios usr){
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection con = newConexion.devolverConnection();
        
        String sql = "SELECT usuario,pass FROM usuarios WHERE usuario = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            rs=ps.executeQuery();
            
            if(rs.next()){
                if(usr.getPassUsuario().equals(rs.getString(2))){
                    usr.setUsuario(rs.getString(1));
                    return true;
                }else {
                    return false;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            return false;
        }
        
    }
    
    public boolean registroHuesped (Huesped huesped){
        PreparedStatement ps = null;
        Connection con = devolverConnection();
        
        String sql = "INSERT INTO ";
        
        return true;
    
    }
    public void guardarReserva(Reserva reserva){
        PreparedStatement ps=null;        
        Connection con = devolverConnection();
        String sql = "INSERT INTO reservas (fecha_entrada,fecha_salida,valor,formaPago) VALUES (?,?,?,?)";
        try {
           // ps=con.prepareStatement(sql);
           ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           //ps.setInt(1, reserva.getId());
           ps.setDate(1, reserva.getFechaInicial());
           ps.setDate(2, reserva.getFechaFinal());
           ps.setInt(3, reserva.getValor());
           ps.setString(4, reserva.getFormaPago());
           
           ps.executeUpdate();
           
            try (ResultSet rs = ps.getGeneratedKeys()){
                
                while (rs.next()) {
                    System.out.println("Insertado........");
                    reserva.setId(rs.getInt(1));                   
                }
                
            } catch (Exception e) {
                System.out.println(e);
            }
           
        } catch (SQLException e) {
            throw new RuntimeException(e);            
        }
    
    }
}
    
