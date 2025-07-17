/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.grados;

import beans.usuarios.Grado;
import beans.usuarios.Solicitud;
import beans.usuarios.Usuario;
import daos.conexion.ConexionDBP;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author hugoeav
 */
public class GradoDao {
    public boolean registraGrado(Grado grado) throws SQLException, IOException{
        HttpSession session = ServletActionContext.getRequest().getSession();
        Usuario userLog = (Usuario) session.getAttribute("usuario");
         String sql="INSERT INTO padron_profesionales.grados(tipo_grado, nombre, institucion, cedula, id_usuario, id_solicitud)VALUES (?,?, ?, ?, ?, ?);";
        try(Connection con= ConexionDBP.getConnection(); PreparedStatement pst=con.prepareStatement(sql)){
            pst.setString(1, grado.getTipo_grado());
            pst.setString(2, grado.getNombre());
            pst.setString(3, grado.getInstitucion());
            pst.setString(4, grado.getCedula());
            pst.setInt(5, userLog.getId_usuario());
            pst.setInt(6, grado.getId_solicitud());
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Grado> obtenerGrados(Solicitud solicitud) throws SQLException, IOException{
        List <Grado> grados=new ArrayList<>();
         String sql="SELECT * FROM padron_profesionales.grados where id_solicitud=?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement pst=con.prepareStatement(sql)) {
            pst.setInt(1, solicitud.getId_solicitud());
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                Grado grado= new Grado();
                grado.setId_grado(rs.getInt("id_grado"));
                grado.setTipo_grado(rs.getString("tipo_grado"));
                grado.setNombre(rs.getString("nombre"));
                grado.setInstitucion(rs.getString("institucion"));
                grado.setCedula(rs.getString("cedula"));
                grado.setId_usuario(rs.getInt("id_usuario"));
                grado.setId_solicitud(rs.getInt("id_solicitud"));
                grado.setFecha_agregado(rs.getDate("fecha_agregado"));
                grados.add(grado);
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        return grados;
    }
    
    public List<Grado> obtenerGrados(Usuario usuario) throws SQLException, IOException{
        List <Grado> grados=new ArrayList<>();
        String sql="SELECT * FROM padron_profesionales.grados where id_usuario=?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement pst=con.prepareStatement(sql)) {
            pst.setInt(1, usuario.getId_usuario());
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                Grado grado= new Grado();
                grado.setId_grado(rs.getInt("id_grado"));
                grado.setTipo_grado(rs.getString("tipo_grado"));
                grado.setNombre(rs.getString("nombre"));
                grado.setInstitucion(rs.getString("institucion"));
                grado.setCedula(rs.getString("cedula"));
                grado.setId_usuario(rs.getInt("id_usuario"));
                grado.setId_solicitud(rs.getInt("id_solicitud"));
                grado.setFecha_agregado(rs.getDate("fecha_agregado"));
                grados.add(grado);
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        return grados;
    }
}
