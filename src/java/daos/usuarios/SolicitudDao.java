/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.usuarios;

import beans.usuarios.Documento;
import beans.usuarios.Solicitud;
import beans.usuarios.Usuario;
import daos.conexion.ConexionDBP;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.joda.time.LocalDate;

/**
 *
 * @author hugoeav
 */
public class SolicitudDao {
    public int nuevaSolicitud(Solicitud solicitud) throws SQLException, IOException{
        HttpSession session = ServletActionContext.getRequest().getSession();
       
        Usuario userLog = (Usuario) session.getAttribute("usuario");
        int idGenerado=-1;
        String sql="INSERT INTO padron_profesionales.solicitudes(tipo, observacion, status, id_usuario)VALUES (?, ?, ?, ?) RETURNING id_solicitud;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement ps = con.prepareStatement(sql)){
           ps.setString(1, solicitud.getTipo());
           ps.setString(2, solicitud.getObservacion());
           ps.setString(3, solicitud.getStatus());
           ps.setInt(4, userLog.getId_usuario());
           ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               idGenerado = rs.getInt("id_solicitud"); 
            }
           return idGenerado;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public List<Solicitud> solicitudesXUser(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        Usuario userLog = (Usuario) session.getAttribute("usuario");
        List<Solicitud> solicitudes = new ArrayList<>();
        String sql="SELECT id_solicitud,tipo,fecha_agregado,status from padron_profesionales.solicitudes where id_usuario=?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userLog.getId_usuario());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Solicitud solicitud=new Solicitud();
                solicitud.setId_solicitud(rs.getInt("id_solicitud"));
                solicitud.setTipo(rs.getString("tipo"));
                solicitud.setFecha_agregado(rs.getDate("fecha_agregado"));
                solicitud.setStatus(rs.getString("status"));
                solicitudes.add(solicitud);
            }
        }catch (Exception e) {
        e.printStackTrace(); // imprime el error para depuración
        }
        return solicitudes;
    }
    
    public List<Solicitud> solicitudes(){
        List<Solicitud> solicitudes = new ArrayList<>();
        String sql="SELECT s.id_solicitud,s.id_usuario,s.tipo,s.status,s.fecha_agregado, u.nombre,u.primer_apellido,u.segundo_apellido,u.curp from padron_profesionales.solicitudes s left join padron_profesionales.usuarios u on s.id_usuario= u.id_usuario order by s.id_solicitud desc";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuario usuarioRes =new Usuario();
                Solicitud solicitud=new Solicitud();
                solicitud.setId_solicitud(rs.getInt("id_solicitud"));
                solicitud.setTipo(rs.getString("tipo"));
                solicitud.setFecha_agregado(rs.getDate("fecha_agregado"));
                solicitud.setStatus(rs.getString("status"));
                usuarioRes.setId_usuario(rs.getInt("id_usuario"));
                usuarioRes.setNombre(rs.getString("nombre"));
                usuarioRes.setPrimerApellido(rs.getString("primer_apellido"));
                usuarioRes.setSegundoApellido(rs.getString("segundo_apellido"));
                usuarioRes.setCurp(rs.getString("curp"));
                solicitud.setUsuario(usuarioRes);
                
                solicitudes.add(solicitud);
            }
        }catch (Exception e) {
        e.printStackTrace(); 
        }
        return solicitudes;
    }
    
    public Solicitud detalleSolicitud(Solicitud solicitud){
         Solicitud infoSolicitud=new Solicitud();
         String sql="SELECT s.*, u.id_usuario,u.nombre,u.primer_apellido,u.segundo_apellido,u.curp,u.rfc,u.telefono,u.telefonolab, u.correo from padron_profesionales.solicitudes s left join padron_profesionales.usuarios u on s.id_usuario=u.id_usuario where id_solicitud=?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, solicitud.getId_solicitud());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Usuario usuarioRes=new Usuario();
                usuarioRes.setId_usuario(rs.getInt("id_usuario"));
                usuarioRes.setNombre(rs.getString("nombre"));
                usuarioRes.setPrimerApellido(rs.getString("primer_apellido"));
                usuarioRes.setSegundoApellido(rs.getString("segundo_apellido"));
                usuarioRes.setCurp(rs.getString("curp"));
                usuarioRes.setRfc(rs.getString("rfc"));
                usuarioRes.setTelefono(rs.getString("telefono"));
                usuarioRes.setTelefonoLab(rs.getString("telefonolab"));
                usuarioRes.setCorreo(rs.getString("correo"));
                infoSolicitud.setId_solicitud(rs.getInt("id_solicitud"));
                infoSolicitud.setTipo(rs.getString("tipo"));
                infoSolicitud.setObservacion(rs.getString("observacion"));
               infoSolicitud.setId_usuario(rs.getInt("id_usuario"));
                infoSolicitud.setFecha_agregado(rs.getDate("fecha_agregado"));
                infoSolicitud.setStatus(rs.getString("status"));
                infoSolicitud.setUsuario(usuarioRes);
            }
        }catch (Exception e) {
        e.printStackTrace(); // imprime el error para depuración
        }
        return infoSolicitud;
    }
    
    public boolean registraComentarios(Solicitud solicitud){
        String sql="UPDATE padron_profesionales.solicitudes SET  observacion=?,fecha_modificado=now() WHERE id_solicitud=?;";
         try (Connection con = ConexionDBP.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
             ps.setString(1, solicitud.getObservacion());
             ps.setInt(2, solicitud.getId_solicitud());
             int filasAfectadas = ps.executeUpdate();
             return filasAfectadas > 0;
         }catch (Exception e) {
            e.printStackTrace(); 
        }
         return false;
    }
    
    public boolean cambiarStatusDoc(Documento documento,Solicitud solicitud){
        String sql="UPDATE padron_profesionales.solicitud_documentos SET  valido=? WHERE id_documento=? and id_solicitud=?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
             if(documento.getValido() ==null){
                ps.setNull(1, java.sql.Types.BOOLEAN);
            }
             else{
                 ps.setBoolean(1, documento.getValido());
             }
             
             ps.setInt(2, documento.getId_documento());
             ps.setInt(3, solicitud.getId_solicitud());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
         }catch (Exception e) {
            e.printStackTrace(); 
        }
        return false;
    }
    
    public boolean actualizarStatusSolicitud(Solicitud solicitud){
        LocalDate fechaMod = LocalDate.now();
          Date sqlFechaMod = new Date(fechaMod.toDate().getTime());
          String sql="UPDATE padron_profesionales.solicitudes SET status=?, fecha_modificado=? WHERE id_solicitud=?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setString(1, solicitud.getStatus());
            ps.setDate(2,sqlFechaMod );
            ps.setInt(3, solicitud.getId_solicitud());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        }catch (Exception e) {
            e.printStackTrace(); 
        }
        return true;
    }
    public boolean actualizarObservacionSolicitud(Solicitud solicitud){
        LocalDate fechaMod = LocalDate.now();
          Date sqlFechaMod = new Date(fechaMod.toDate().getTime());
         String sql="UPDATE padron_profesionales.solicitudes SET observacion=?, fecha_modificado=? WHERE id_solicitud=?;";
        try (Connection con = ConexionDBP.getConnection();PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setString(1, solicitud.getObservacion());
            ps.setDate(2,sqlFechaMod );
            ps.setInt(3, solicitud.getId_solicitud());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        }catch (Exception e) {
            e.printStackTrace(); 
        }
        return true;
    }
}
